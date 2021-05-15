package Entities;

public class Inscription {
	private int idInscription;
	private String dateInscrtiption;
	private String cniE ;
	private String nomF ;
	private int numS;
	
	public int getIdInscription() {
		return idInscription;
	}
	public void setIdInscription(int idInscription) {
		this.idInscription = idInscription;
	}
	public String getDateInscrtiption() {
		return dateInscrtiption;
	}
	public void setDateInscrtiption(String dateInscrtiption) {
		this.dateInscrtiption = dateInscrtiption;
	}
	public String getCniE() {
		return cniE;
	}
	public void setCniE(String cniE) {
		this.cniE = cniE;
	}
	public String getNomF() {
		return nomF;
	}
	public void setNomF(String nomF) {
		this.nomF = nomF;
	}
	
	public int getNumS() {
		return numS;
	}
	public void setNumS(int numS) {
		this.numS = numS;
	}
	@Override
	public String toString() {
		return "Inscription [idInscription=" + idInscription + ", dateInscrtiption=" + dateInscrtiption + ", cniE="
				+ cniE + ", nomF=" + nomF + ", numS=" + numS + "]";
	}
	
	
	
}
