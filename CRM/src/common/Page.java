package common;

import java.util.List;

public class Page {
	//��ǰҳ
	private int cur_page=1;
	//ÿҳ��ʾ�ļ�¼��
	public static int  pageRow=3;
	//��¼����
	private int totalRows;
	//��ʼλ��
	private int startRow;
	//����
	private List datas;
	
	
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
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
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
