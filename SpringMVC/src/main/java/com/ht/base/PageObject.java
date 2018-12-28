package com.ht.base;

import java.util.List;

public class PageObject {
    //当前页
    private int cur_page=1;
    //每页显示的记录数
    public  int  pageRow=5;
    //记录总数
    private int totalRows;
    //总页数
    private int totalPages;
    //开始位置
    private int startRow;
    //数据
    private List datas;



    public int getTotalRows() {
        return totalRows;
    }
    public void setTotalRows(int totalRows) {
        this.totalPages=(int)Math.ceil((double)totalRows/(double)this.pageRow);
        this.totalRows = totalRows;
    }
    public int getCur_page() {
        return cur_page;
    }
    public void setCur_page(int cur_page) {
        this.cur_page = cur_page;
    }
    public int getPageRow() {
        return pageRow;
    }
    public void setPageRow(int pageRow) {
        this.pageRow = pageRow;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public List getDatas() {
        return datas;
    }
    public void setDatas(List datas) {
        this.datas = datas;
    }
    public int getStartRow() {

        this.startRow = (this.cur_page-1)*this.pageRow;
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

}
