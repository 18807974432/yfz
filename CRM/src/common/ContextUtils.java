package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContextUtils {
	//double����ת��Ϊ�ַ���
	public static String doubleToStr(double d){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance(); 
		nf.setGroupingUsed(false); 
		return nf.format(d);
	}
	//�ϴ��ļ�����������
	public static String getFilename(){
		Date d = new Date();
		int year = d.getYear()+1900;
		int month = d.getMonth()+1;
		int day = d.getDate();
		int hour = d.getHours();
		int minute = d.getMinutes();
		int second = d.getSeconds();
		String sdate = year+"";
		if(month<10)
			sdate += "0"+month;
		else
			sdate += month;
		if(day<10){
			sdate += "0"+day;
		}else{
			sdate += day;
		}
		if(hour<10){
			sdate += "0"+hour;
		}else{
			sdate += hour;
		}
		if(minute<10){
			sdate += "0"+minute;
		}else{
			sdate += minute;
		}
		if(second<10){
			sdate += "0"+second;
		}else{
			sdate += second;
		}
		return sdate;
	}

	//date����ת��Ϊ��ʽ������ַ�������
	public static String dateToStrLong(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	} 
	//date����ת��Ϊ��ʽ������ַ�������
	public static String dateToStrShort(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	} 
	//get��ʽ�ύ���ݣ��������봦��
	public String toStr(String str){
		try{
			str = new String(str.getBytes("iso-8859-1"),"utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
}
