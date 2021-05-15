package Entities;

public class Filiere {
		
	private String nomF ;
	private String chef ;
	private int nbrModules ;
	private int nbrSemestre;

	public String getNomF() {
		return nomF;
	}
	public void setNomF(String nomF) {
		this.nomF = nomF;
	}
	public String getChef() {
		return chef;
	}
	public void setChef(String chef) {
		this.chef = chef;
	}
	public int getNbrModules() {
		return nbrModules;
	}
	public void setNbrModules(int nbrModules) {
		this.nbrModules = nbrModules;
	}
	public int getNbrSemestre() {
		return nbrSemestre;
	}
	public void setNbrSemestre(int nbrSemestre) {
		this.nbrSemestre = nbrSemestre;
	}
	@Override
	public String toString() {
		return "Filiere [nomF=" + nomF + ", chef=" + chef + ", nbrModules=" + nbrModules + ", nbrSemestre="
				+ nbrSemestre + "]";
	}
	
	
	
	
}
