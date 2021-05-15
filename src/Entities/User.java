package Entities;

public class User {


    private String EmailU;
    private String Password;
    private String RoleU;
	public String getEmailU() {
		return EmailU;
	}
	public void setEmailU(String emailU) {
		EmailU = emailU;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRoleU() {
		return RoleU;
	}
	public void setRoleU(String roleU) {
		RoleU = roleU;
	}
	@Override
	public String toString() {
		return "User [EmailU=" + EmailU + ", Password=" + Password + ", RoleU=" + RoleU + ", getEmailU()=" + getEmailU()
				+ ", getPassword()=" + getPassword() + ", getRoleU()=" + getRoleU() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
}

