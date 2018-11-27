package com.ht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ht.dao.ContextUtils;
import com.ht.dao.IBaseDAO;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.AccModule;
import com.ht.vo.Module;
import com.ht.vo.Users;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


public class AccModuleAction
        extends ActionSupport
{
    private IBaseDAO base = ((IBaseDAO)WebApplicationContextUtil.createService("dao"));
    private List<Module> moduleInfo;
    private List<AccModule> accInfo;
    private List<Users> userInfo;
    private int username;
    private int[] moduleids;
    private int[] sysid;

    public List<Users> getUserInfo()
    {
        return this.userInfo;
    }

    public void setUserInfo(List<Users> userInfo)
    {
        this.userInfo = userInfo;
    }

    public List<Module> getModuleInfo()
    {
        return this.moduleInfo;
    }

    public void setModuleInfo(List<Module> moduleInfo)
    {
        this.moduleInfo = moduleInfo;
    }

    public List<AccModule> getAccInfo()
    {
        return this.accInfo;
    }

    public void setAccInfo(List<AccModule> accInfo)
    {
        this.accInfo = accInfo;
    }


    public String list()
            throws Exception
    {
        DetachedCriteria dc = DetachedCriteria.forClass(Module.class);
        this.moduleInfo = this.base.findByDetach(dc);

        DetachedCriteria dc1 = DetachedCriteria.forClass(AccModule.class);
        if (this.username == 0) {
            this.username = ContextUtils.getUserInfo().getUserid();
        }
        dc1.add(Restrictions.eq("userid", Integer.valueOf(this.username)));
        this.accInfo = this.base.findByDetach(dc1);

        DetachedCriteria dc2 = DetachedCriteria.forClass(Users.class);

        this.userInfo = this.base.findByDetach(dc2);
        return "success";
    }

    public String revoke()
            throws Exception {
        Users u = (Users)this.base.findObjById(Users.class, Integer.valueOf(this.username));
        if (u.getUsername().equals("admin")) {
            return list();
        }
        int len = this.moduleids.length;
        for (int i = 0; i < len; i++)
        {
            AccModule a = new AccModule();
            a.setId(this.moduleids[i]);
            this.base.delete(a);
        }
        return list();
    }

    public String grant()
            throws Exception
    {
        Users u = (Users)this.base.findObjById(Users.class, Integer.valueOf(this.username));
        if (u.getUsername().equals("admin")) {
            return list();
        }
        int len = this.sysid.length;
        for (int i = 0; i < len; i++)
        {
            Module m = new Module();
            m.setModuleid(this.sysid[i]);
            AccModule a = new AccModule();
            a.setUserid(this.username);
            a.setModule(m);
            DetachedCriteria dc1 = DetachedCriteria.forClass(AccModule.class);
            dc1.add(Restrictions.eq("userid", Integer.valueOf(this.username)));
            dc1.add(Restrictions.eq("module.moduleid", Integer.valueOf(this.sysid[i])));
            List list1 = this.base.findByDetach(dc1);
            if (list1.size() == 0) {
                this.base.saveOrUpdate(a);
            }
        }
        return list();
    }

    public int getUsername()
    {
        return this.username;
    }

    public void setUsername(int username)
    {
        this.username = username;
    }

    public int[] getModuleids()
    {
        return this.moduleids;
    }

    public void setModuleids(int[] moduleids)
    {
        this.moduleids = moduleids;
    }

    public int[] getSysid()
    {
        return this.sysid;
    }

    public void setSysid(int[] sysid)
    {
        this.sysid = sysid;
    }
}
