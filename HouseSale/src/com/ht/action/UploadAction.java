package com.ht.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ht.dao.IBaseDAO;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.HourseInfo;
import com.ht.vo.Housemodel;
import com.ht.vo.SaleState;
import com.ht.vo.TermInfo;
import com.ht.vo.TermTypeInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class UploadAction
        extends ActionSupport
{
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String savePath;
    private String errinfo;
    private IBaseDAO base = (IBaseDAO)WebApplicationContextUtil.createService("dao");
    private List<TermTypeInfo> ttInfoList;
    private List<SaleState> stateList;
    private List<Housemodel> houseModelList;
    private int termId;

    public int getTermId()
    {
        return this.termId;
    }

    public void setTermId(int termId)
    {
        this.termId = termId;
    }

    public UploadAction()
    {
        DetachedCriteria dc = DetachedCriteria.forClass(TermTypeInfo.class);
        this.ttInfoList = this.base.findByDetach(dc);
        DetachedCriteria dc2 = DetachedCriteria.forClass(SaleState.class);
        this.stateList = this.base.findByDetach(dc2);
        DetachedCriteria dc3 = DetachedCriteria.forClass(Housemodel.class);
        this.houseModelList = this.base.findByDetach(dc3);
    }

    public boolean checkTermType(String termtype)
    {
        boolean bok = false;
        int size = this.ttInfoList.size();
        for (int i = 0; i < size; i++)
        {
            TermTypeInfo p = (TermTypeInfo)this.ttInfoList.get(i);
            if (p.getTermTypeName().equals(termtype))
            {
                bok = true;
                break;
            }
        }
        return bok;
    }

    public boolean checkSaleStatus(String saleState)
    {
        boolean bok = false;
        int size = this.stateList.size();
        for (int i = 0; i < size; i++)
        {
            SaleState c = (SaleState)this.stateList.get(i);
            if (c.getSaleName().equals(saleState))
            {
                bok = true;
                break;
            }
        }
        return bok;
    }

    public boolean checkHouseModel(String houseModel)
    {
        boolean bok = false;
        int size = this.houseModelList.size();
        for (int i = 0; i < size; i++)
        {
            Housemodel c = (Housemodel)this.houseModelList.get(i);
            if (c.getModelName().equals(houseModel))
            {
                bok = true;
                break;
            }
        }
        return bok;
    }

//    public boolean checkHouseType(String houseType)
//    {
//        boolean bok = false;
//        int size = ServiceConstants.HOURSETYPE.length;
//        for (int i = 0; i < size; i++) {
//            if (ServiceConstants.HOURSETYPE[i].equals(houseType))
//            {
//                bok = true;
//                break;
//            }
//        }
//        return bok;
//    }
//
//    public boolean checkDirection(String direct)
//    {
//        boolean bok = false;
//        int size = ServiceConstants.DIRECTION.length;
//        for (int i = 0; i < size; i++) {
//            if (ServiceConstants.DIRECTION[i].equals(direct))
//            {
//                bok = true;
//                break;
//            }
//        }
//        return bok;
//    }
//
//    public boolean checkStruts(String struts)
//    {
//        boolean bok = false;
//        int size = ServiceConstants.STRUTS.length;
//        for (int i = 0; i < size; i++) {
//            if (ServiceConstants.STRUTS[i].equals(struts))
//            {
//                bok = true;
//                break;
//            }
//        }
//        return bok;
//    }
//
//    public boolean checkBuildStyle(String buildStyle)
//    {
//        boolean bok = false;
//        int size = ServiceConstants.BUILDSTYLE.length;
//        for (int i = 0; i < size; i++) {
//            if (ServiceConstants.BUILDSTYLE[i].equals(buildStyle))
//            {
//                bok = true;
//                break;
//            }
//        }
//        return bok;
//    }

    public boolean checkHouseInfo(String houseName)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(HourseInfo.class);
        dc.add(Restrictions.eq("hourseName", houseName));
        List<HourseInfo> hlist = this.base.findByDetach(dc);
        if (hlist.size() > 0) {
            return true;
        }
        return false;
    }

    public File getUpload()
    {
        return this.upload;
    }

    public void setUpload(File upload)
    {
        this.upload = upload;
    }

    public String getUploadContentType()
    {
        return this.uploadContentType;
    }

    public void setUploadContentType(String uploadContentType)
    {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName()
    {
        return this.uploadFileName;
    }

    public void setUploadFileName(String uploadFileName)
    {
        this.uploadFileName = uploadFileName;
    }

    public String getSavePath()
    {
        return ServletActionContext.getRequest().getRealPath(this.savePath);
    }

    public void setSavePath(String savePath)
    {
        this.savePath = savePath;
    }

    public String execute()
            throws Exception
    {
        String filename = getUploadFileName();
        int pos = filename.lastIndexOf('.');
//        String extname = filename.substring(pos, filename.length());
//        filename = ContextUtils.getFilename() + extname;
        setUploadFileName(filename);
        filename = getSavePath() + "\\" + filename;
        FileInputStream fis = new FileInputStream(this.upload);
        FileOutputStream fos = new FileOutputStream(filename);
        byte[] b = new byte['Ѐ'];
        while (fis.read(b) != -1) {
            fos.write(b);
        }
        fos.close();
        fis.close();
        parsefile(filename);
        return "success";
    }

    public void parsefile(String filename)
    {
        POIFSFileSystem fs = null;
        HSSFWorkbook wb = null;
        try
        {
            fs = new POIFSFileSystem(new FileInputStream(filename));
            wb = new HSSFWorkbook(fs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheetAt(0);
        Object[][] data = (Object[][])null;
        int r = sheet.getLastRowNum() + 1;
        int c = sheet.getRow(0).getLastCellNum();
        int headRow = 3;
        data = new Object[r - headRow][c];
        for (int i = headRow; i <= r; i++)
        {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < c;)
            {
                HSSFCell cell = null;
                try
                {
                    cell = row.getCell(j);
                    if (j == 36) {
                        try
                        {
                            data[(i - headRow)][j] = cell.getDateCellValue();
                        }
                        catch (Exception e)
                        {
                            data[(i - headRow)][j] = cell.getStringCellValue();
                        }
                    } else if ((j == 3) || (j == 4) || ((j >= 13) && (j <= 35)) || ((j >= 38) && (j <= 40))) {
                        try
                        {
                            data[(i - headRow)][j] = Double.valueOf(cell.getNumericCellValue());
                        }
                        catch (Exception e)
                        {
                            data[(i - headRow)][j] = cell.getStringCellValue();
                        }
                    } else {
                        try
                        {
                            data[(i - headRow)][j] = cell.getStringCellValue();
                        }
                        catch (Exception e)
                        {
                            data[(i - headRow)][j] = Double.valueOf(cell.getNumericCellValue());
                        }
                    }
                    j++;
                }
                catch (Exception e)
                {
                    System.out.println("i=" + i + ";j=" + j + ":" + e.getMessage());
                }
            }
        }
        System.out.println(data);
        addorUpdate(data);
        ActionContext.getContext().put("errinfo", this.errinfo);
    }

    public void addorUpdate(Object[][] data)
    {
        int row = data.length;
        int col = 0;
        this.errinfo = "";
        String[] stitle = { "房产类型", "业态类型", "装修标准", "单元", "楼层", "房号", "合同房产号", "销售状态", "朝向", "结构", "建筑形式", "户型名称", "几梯几户", "销售面积", "套内面积", "建筑面积", "花园面积", "地下室面积", "公摊面积", "阳台面积", "露台面积", "使用率(%)", "实测平台", "实测套内", "实测建筑", "实测花园", "实测地下室", "实测公摊", "房产单价", "套内单价", "建筑单价", "房产总价", "原始总价", "原始单价", "房产底价", "房产底价", "推出时间", "门牌号", "折扣(%)", "折后价(元)", "佣金比例", "描述" };
        String colName = "";
        if (this.termId == 0)
        {
            this.errinfo += "楼栋资料不存在";
            return;
        }
        TermInfo tInfo = (TermInfo)this.base.findObjById(TermInfo.class, Integer.valueOf(this.termId));
        if (tInfo == null)
        {
            this.errinfo += "楼栋资料不存在";
            return;
        }
        for (int i = 0; i < row; i++)
        {
            col = 0;
            try
            {
                HourseInfo h = new HourseInfo();
                colName = data[i][(col++)].toString().trim();
//                if (checkHouseType(colName))
//                {
//                    h.setHourseType(colName);
//                }
//                else
//                {
//                    this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
//                    continue;
//                }
                colName = data[i][(col++)].toString().trim();
                if (checkTermType(colName))
                {
                    h.setTermType(colName);
                }
                else
                {
                    this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
                    continue;
                }
                h.setFitment(data[i][(col++)].toString());
                int t = 0;
                try
                {
                    t = new Double(data[i][(col++)].toString()).intValue();
                    h.setUnitid(t);
                }
                catch (Exception e)
                {
                    this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
                    continue;
                }
                try
                {
                    t = new Double(data[i][(col++)].toString()).intValue();
                    h.setFloor(t);
                }
                catch (Exception e)
                {
                    this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
                    continue;
                }
                h.setHourseno(data[i][(col++)].toString().trim());
                h.setTerm(tInfo);
                colName = tInfo.getProject().getGardenName() + "-" + tInfo.getTermName() + "-" + h.getUnitid() + "-" + h.getFloor() + h.getHourseno();
                h.setHourseName(colName);
                System.out.println("名称：" + colName);
                if (checkHouseInfo(colName))
                {
                    this.errinfo = (this.errinfo + "第" + (i + 1) + "行，房产名称：" + colName + "的房子已经存在<br/>");
                }
                else
                {
                    h.setContractno(data[i][(col++)].toString().trim());
                    colName = data[i][(col++)].toString().trim();
                    if (checkSaleStatus(colName))
                    {
                        h.setSaleState(colName);
                    }
                    else
                    {
                        this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
                        continue;
                    }
                    colName = data[i][(col++)].toString().trim();
//                    if (checkDirection(colName))
//                    {
//                        h.setDirection(colName);
//                    }
//                    else
//                    {
//                        this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
//                        continue;
//                    }
//                    colName = data[i][(col++)].toString().trim();
//                    if (checkStruts(colName))
//                    {
//                        h.setStruts(colName);
//                    }
//                    else
//                    {
//                        this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
//                        continue;
//                    }
//                    colName = data[i][(col++)].toString().trim();
//                    if (checkBuildStyle(colName))
//                    {
//                        h.setBuildstyle(colName);
//                    }
//                    else
//                    {
//                        this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
//                        continue;
//                    }
                    colName = data[i][(col++)].toString().trim();
                    if (checkHouseModel(colName))
                    {
                        h.setModelname(colName);
                    }
                    else
                    {
                        this.errinfo = (this.errinfo + "第" + (i + 1) + "行的【" + stitle[(col - 1)] + "】错误;");
                        continue;
                    }
                    colName = data[i][(col++)].toString().trim();
                    h.setLift(colName);
                    h.setIsbalance("否");
                    h.setSaleArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setInArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setBuildArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setViewArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setDownArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setPublicArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setTableArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setOutArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setUsePercent(toDouble(data[i][(col++)]).doubleValue());
                    h.setFacttableArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setFactinArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setFactbuildArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setFactviewArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setFactdownArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setFactpublicArea(toDouble(data[i][(col++)]).doubleValue());
                    h.setUnitPrice(toDouble(data[i][(col++)]).doubleValue());
                    h.setInunitPrice(toDouble(data[i][(col++)]).doubleValue());
                    h.setBuildunitPrice(toDouble(data[i][(col++)]).doubleValue());

                    col++;
                    h.setTotalPrice(h.getUnitPrice() * h.getSaleArea());
                    h.setOldtotalPrice(toDouble(data[i][(col++)]).doubleValue());
                    h.setOldunitPrice(toDouble(data[i][(col++)]).doubleValue());
                    h.setUnitlowPrice(toDouble(data[i][(col++)]).doubleValue());
                    h.setTotallowPrice(toDouble(data[i][(col++)]).doubleValue());
                    h.setSaleTime(toDate(data[i][(col++)]));
                    h.setDoorno(data[i][(col++)].toString());
                    h.setDiscount(toDouble(data[i][(col++)]).doubleValue());
                    h.setDiscountPrice(toDouble(data[i][(col++)]).doubleValue());
                    h.setCommisionPercent(toDouble(data[i][(col++)]).doubleValue());
                    h.setCommisionMoney(h.getCommisionPercent() * h.getTotalPrice());
                    h.setCommisionpaid(0.0D);
                    h.setCustid(0);
                    h.setDescription(data[i][(col++)].toString());
                    this.base.saveOrUpdate(h);
                }
            }
            catch (Exception e)
            {
                this.errinfo = (this.errinfo + "第" + (i + 1) + "行，" + stitle[(--col)] + "列数据有误；");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        this.errinfo += ",导入完毕,请在房产资料中查看导入数据！";
    }

    public Date toDate(Object o)
    {
        Date date = null;
        System.out.println("===========" + o);
        if ((o == null) || (o.toString().equals(""))) {
            return null;
        }
        try
        {
            date = new Date(o.toString().trim().replace("-", "/"));
        }
        catch (Exception e)
        {
            date = new Date(new Integer(o.toString().trim()).longValue());
        }
        return date;
    }

    public Double toDouble(Object o)
    {
        Double b = Double.valueOf(0.0D);
        if ((o != null) && (!o.toString().trim().equals(""))) {
            b = Double.valueOf(Double.parseDouble(o.toString().trim()));
        }
        return b;
    }

    public String getErrinfo()
    {
        return this.errinfo;
    }

    public void setErrinfo(String errinfo)
    {
        this.errinfo = errinfo;
    }

    public String DoubletoStr(double d)
    {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(d);
    }

    public String toStr(Object obj)
    {
        String str = "";
        if (obj == null) {
            str = "";
        } else {
            try
            {
                str = DoubletoStr(new Double(obj.toString()).doubleValue());
            }
            catch (Exception e)
            {
                str = obj.toString();
            }
        }
        return str;
    }
}
