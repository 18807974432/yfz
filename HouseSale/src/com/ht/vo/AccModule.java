package com.ht.vo;

public class AccModule
{
    private int id;
    private int userid;
    private int moduleid;
    private Module module;


    public String toString()
    {
        String str = "(" + this.id + "," + this.userid + "," + this.module.getModuleid() + ")";
        return str;
    }

    public Module getModule()
    {
        return this.module;
    }

    public void setModule(Module module)
    {
        this.module = module;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserid()
    {
        return this.userid;
    }

    public void setUserid(int userid)
    {
        this.userid = userid;
    }

    public int getModuleid()
    {
        return this.moduleid;
    }

    public void setModuleid(int moduleid)
    {
        this.moduleid = moduleid;
    }
}
