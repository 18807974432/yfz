package bean;

import java.io.Serializable;

public class contractAttachVo implements Serializable{
	public int contractAttach_id;
	public int contract_id;
	public int Seq;
	public String AttachFile;
	public String UploadTime;
	public String user_name;
	public String attachURL;
	public int getContractAttach_id() {
		return contractAttach_id;
	}
	public void setContractAttach_id(int contractAttach_id) {
		this.contractAttach_id = contractAttach_id;
	}
	public int getContract_id() {
		return contract_id;
	}
	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}
	public int getSeq() {
		return Seq;
	}
	public void setSeq(int seq) {
		Seq = seq;
	}
	public String getAttachFile() {
		return AttachFile;
	}
	public void setAttachFile(String attachFile) {
		AttachFile = attachFile;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAttachURL() {
		return attachURL;
	}
	public void setAttachURL(String attachURL) {
		this.attachURL = attachURL;
	}

}
