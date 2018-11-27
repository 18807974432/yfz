package bean;

import java.io.Serializable;

public class degreesVo implements Serializable{
	public int degreeid;
	public String degreename;
	public int getDegreeid() {
		return degreeid;
	}
	public void setDegreeid(int degreeid) {
		this.degreeid = degreeid;
	}
	public String getDegreename() {
		return degreename;
	}
	public void setDegreename(String degreename) {
		this.degreename = degreename;
	}
}
