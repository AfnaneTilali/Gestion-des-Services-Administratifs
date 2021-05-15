package Entities;

public class Parent {

	private String nomP ;
	private String prenomP ;
	private String cniP ;
	private String emailP ;
	private int phone ;
	
	
	public String getNomP() {
		return nomP;
	}
	public void setNomP(String nomP) {
		this.nomP = nomP;
	}
	public String getPrenomP() {
		return prenomP;
	}
	public void setPrenomP(String prenomP) {
		this.prenomP = prenomP;
	}
	public String getCniP() {
		return cniP;
	}
	public void setCniP(String cniP) {
		this.cniP = cniP;
	}
	public String getEmailP() {
		return emailP;
	}
	public void setEmailP(String emailP) {
		this.emailP = emailP;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Parent [nomP=" + nomP + ", prenomP=" + prenomP + ", cniP=" + cniP + ", emailP=" + emailP + ", phone="
				+ phone + "]";
	}
	
}
