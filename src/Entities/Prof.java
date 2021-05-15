package Entities;

public class Prof {
	private String nomPr ;
	private String prenomPr ;
	private String cniPr ;
	private String emailPr ;
	private int phonePr ;
	public String getNomPr() {
		return nomPr;
	}
	public void setNomPr(String nomPr) {
		this.nomPr = nomPr;
	}
	public String getPrenomPr() {
		return prenomPr;
	}
	public void setPrenomPr(String prenomPr) {
		this.prenomPr = prenomPr;
	}
	public String getCniPr() {
		return cniPr;
	}
	public void setCniPr(String cniPr) {
		this.cniPr = cniPr;
	}
	public String getEmailPr() {
		return emailPr;
	}
	public void setEmailPr(String emailPr) {
		this.emailPr = emailPr;
	}
	public int getPhonePr() {
		return phonePr;
	}
	public void setPhonePr(int phonePr) {
		this.phonePr = phonePr;
	}
	@Override
	public String toString() {
		return "Prof [nomPr=" + nomPr + ", prenomPr=" + prenomPr + ", cniPr=" + cniPr + ", emailPr=" + emailPr
				+ ", phonePr=" + phonePr + "]";
	}
	
	
}
