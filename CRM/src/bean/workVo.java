package bean;

import java.io.Serializable;

public class workVo implements Serializable{
	public int id;
	public String weekDate;
	public String workContent;
	public String workReview;
	public String question;
	public String warning;
	public String weekPlan;
	public int userid;
	public String oprtime;
	public String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWeekDate() {
		return weekDate;
	}
	public void setWeekDate(String weekDate) {
		this.weekDate = weekDate;
	}
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	public String getWorkReview() {
		return workReview;
	}
	public void setWorkReview(String workReview) {
		this.workReview = workReview;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getWeekPlan() {
		return weekPlan;
	}
	public void setWeekPlan(String weekPlan) {
		this.weekPlan = weekPlan;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getOprtime() {
		return oprtime;
	}
	public void setOprtime(String oprtime) {
		this.oprtime = oprtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
