package Entities;

public class Semestre {
	private int numS;
	private String nomF;
	private int nbrModule;
	public int getNumS() {
		return numS;
	}
	public void setNumS(int numS) {
		this.numS = numS;
	}
	public String getNomF() {
		return nomF;
	}
	public void setNomF(String nomF) {
		this.nomF = nomF;
	}
	public int getNbrModule() {
		return nbrModule;
	}
	public void setNbrModule(int nbrModule) {
		this.nbrModule = nbrModule;
	}
	@Override
	public String toString() {
		return "Semestre [numS=" + numS + ", nomF=" + nomF + ", nbrModule=" + nbrModule + "]";
	}
	
}
