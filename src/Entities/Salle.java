package Entities;

public class Salle {
	private int numS;
	private int etageS ;
	public int getNumS() {
		return numS;
	}
	public void setNumS(int numS) {
		this.numS = numS;
	}
	public int getEtageS() {
		return etageS;
	}
	public void setEtageS(int etageS) {
		this.etageS = etageS;
	}
	@Override
	public String toString() {
		return "Salle [numS=" + numS + ", etageS=" + etageS + "]";
	}
	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salle(int numS, int etageS) {
		super();
		this.numS = numS;
		this.etageS = etageS;
	}
	
}
