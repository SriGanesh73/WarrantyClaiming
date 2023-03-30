package warrantyClaimController;

public class User {
	protected String uName;
	protected String pwd;
	protected String uType;
	public String getuType() {
		return uType;
	}
	public void setuType(String uType) {
		this.uType = uType;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	

}
