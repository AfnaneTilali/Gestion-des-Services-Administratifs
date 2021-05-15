package Entities;

public class Equipement {
	private int idEq ;
	private String nomEq ;
	private int numEq ;
	private int numS ;
	
	public int getNumS() {
		return numS;
	}
	public void setNumS(int numS) {
		this.numS = numS;
	}
	public int getIdEq() {
		return idEq;
	}
	public void setIdEq(int idEq) {
		this.idEq = idEq;
	}
	public String getNomEq() {
		return nomEq;
	}
	public void setNomEq(String nomEq) {
		this.nomEq = nomEq;
	}
	public int getNumEq() {
		return numEq;
	}
	public void setNumEq(int numEq) {
		this.numEq = numEq;
	}
	@Override
	public String toString() {
		return "Equipement [idEq=" + idEq + ", nomEq=" + nomEq + ", numEq=" + numEq + ", numS=" + numS + "]";
	}
	
	
	
}
