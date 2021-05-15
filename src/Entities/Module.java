package Entities;

public class Module {
	private String nomM ;
	private String nomF;
	private int numS ;
	private String cniPr;
	private int nbrPres;
	private int seuille ;
	
	public int getSeuille() {
		return seuille;
	}
	public void setSeuille(int seuille) {
		this.seuille = seuille;
	}
	public String getNomM() {
		return nomM;
	}
	public void setNomM(String nomM) {
		this.nomM = nomM;
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
	public String getCniPr() {
		return cniPr;
	}
	public void setCniPr(String cniPr) {
		this.cniPr = cniPr;
	}
	public int getNbrPres() {
		return nbrPres;
	}
	public void setNbrPres(int nbrPres) {
		this.nbrPres = nbrPres;
	}
	@Override
	public String toString() {
		return "Module [nomM=" + nomM + ", nomF=" + nomF + ", numS=" + numS + ", cniPr=" + cniPr + ", nbrPres="
				+ nbrPres + ", seuille=" + seuille + "]";
	} 
	
}
