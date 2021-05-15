package Web;

import java.util.ArrayList;
import java.util.List;

import Entities.*;

public class MOther {
	private List<Filiere> listfili = new ArrayList<Filiere>();
	private List<Module> listModule = new ArrayList<Module>();
	private List<Semestre> listSemestre = new ArrayList<Semestre>();
	private List<Salle> listSalle = new ArrayList<Salle>();
	private List<Inscription> listInsc = new ArrayList<Inscription>();
	private List<Equipement> listEquipement = new ArrayList<Equipement>();
	private List<PresProf> listPresProf = new ArrayList<PresProf>();

	public List<Filiere> getListfili() {
		return listfili;
	}
	public void setListfili(List<Filiere> listfili) {
		this.listfili = listfili;
	}
	public List<Module> getListModule() {
		return listModule;
	}
	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}
	public List<Semestre> getListSemestre() {
		return listSemestre;
	}
	public void setListSemestre(List<Semestre> listSemestre) {
		this.listSemestre = listSemestre;
	}
	public List<Salle> getListSalle() {
		return listSalle;
	}
	public void setListSalle(List<Salle> listSalle) {
		this.listSalle = listSalle;
	}
	public List<Inscription> getListInsc() {
		return listInsc;
	}
	public void setListInsc(List<Inscription> listInsc) {
		this.listInsc = listInsc;
	}
	public List<Equipement> getListEquipement() {
		return listEquipement;
	}
	public void setListEquipement(List<Equipement> listEquipement) {
		this.listEquipement = listEquipement;
	}
	
	public List<PresProf> getListPresProf() {
		return listPresProf;
	}
	public void setListPresProf(List<PresProf> listPresProf) {
		this.listPresProf = listPresProf;
	}
	@Override
	public String toString() {
		return "MOther [listfili=" + listfili + ", listModule=" + listModule + ", listSemestre=" + listSemestre
				+ ", listSalle=" + listSalle + ", listInsc=" + listInsc + ", listEquipement=" + listEquipement
				+ ", listPresProf=" + listPresProf + "]";
	}
	

}
