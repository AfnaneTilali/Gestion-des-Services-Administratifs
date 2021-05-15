package Web;

import java.util.ArrayList;
import java.util.List;

import Entities.*;

public class MEtudiant {
	
	private Etudiant etudiant;
	private List<Etudiant> listEtud = new ArrayList<Etudiant>();
	private String filiere;
	private int numS;
	
	
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public int getNumS() {
		return numS;
	}
	public void setNumS(int numS) {
		this.numS = numS;
	}
	@Override
	public String toString() {
		return "MEtudiant [etudiant=" + etudiant + ", listEtud=" + listEtud + ", filiere=" + filiere + ", numS=" + numS
				+ "]";
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public List<Etudiant> getListEtud() {
		return listEtud;
	} 
	public void setListEtud(List<Etudiant> listEtud) {
		this.listEtud = listEtud;
	}

	
}
