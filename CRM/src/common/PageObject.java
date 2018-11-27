package common;

import sun.applet.Main;


/*
 * ��ҳ����
 * 1.rowCount;��¼����;
 * 2.pageSize;ÿҳ��ʾ�ļ�¼��
 * 3.pageCount;��ҳ��
 * 4.curPage;��ǰҳ
 * 5.opr;�������ͣ�first,next,piror,last��
 * 
 * */

public class PageObject {

	private int rowCount;//��¼����
	private int pageSize=3;//ÿҳ��ʾ�ļ�¼��
	private int pageCount;//��ҳ��
	private int curPage=1;//��ǰҳ
	private String opr="first";//�������ͣ�first,next,piror,last��
	
	public String getOpr() {
		return opr;
	}
	public void setOpr(String opr) {
		if(opr==null||opr.equals("")){
			opr="first";
		}
		if(opr.equals("first")){
			curPage=1;
		}else if(opr.equals("next")){
			curPage+=1;
			if(curPage>pageCount){
				curPage=pageCount;
			}
		}else if(opr.equals("piror")){
			curPage-=1;
			if(curPage<1){
				curPage=1;
			}
		}else if(opr.equals("last")){
			curPage=pageCount;
		}
		this.opr = opr;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		//������ҳ��
		pageCount=(int)Math.ceil((double)rowCount/(double)pageSize);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	
}
