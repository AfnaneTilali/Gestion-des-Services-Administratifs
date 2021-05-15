package Metier;

import java.util.List;

import Entities.*;

public interface IntOther {
	
	public void SupprimerFiliere(String NomF);
	public void ModifierInfoFiliere(Filiere f);
	public void addFiliere(Filiere f);
	public List <Filiere>getAllFiliere();
	
	public void SupprimerModule(String NomM , String NomF);
	public void ModifierInfoModule(Module m);
	public void addModule(Module m);
	public List <Module>getAllModule();
	public Module getModule(String Mod ,String fil );
	public Module getModuleParFS(String f,int S);

	public void SupprimerSemestre(int NumS,String NomF);
	public void ModifierInfoSemestre(Semestre s);
	public void addSemestre(Semestre s);
	public List <Semestre>getAllSemestre();
	
	public void SupprimerSalle(int NumS);
	public void ModifierInfoSalle(Salle s);
	public void addSalle(Salle s);
	public List <Salle>getAllSalle();
	
	public void SupprimerInscription(int id);
	public void ModifierInfoInscription(Inscription insc);
	public void addInscription(Inscription insc);
	public List <Inscription>getAllInscription();	
	
	public void SupprimerEquipement(int id);
	public void ModifierInfoEquipement(Equipement equipement);
	public void addEquipement(Equipement equipement);
	public List <Equipement>getAllEquipement();
	
	public List<PresProf> getAllPresProf();
	public void addPresProf(PresProf pres);
	
	public void SupprimerEquipementParSalle(int NumS);
	public void SupprimerModuleParProf(String CNIProf);
	public void SupprimerModuleParFiliere(String NomF);
	public void SupprimerModuleParSemestre(int NumS);
	public void SupprimerPresProfParSemestre(int NumS);
	public void SupprimerInscParSemestre(int NumS);
	public void SupprimerInscParFiliere(String NomF);
	public void SupprimerPresProfParFiliere(String NomF);
	public void SupprimerPresProfParModule(String NomM);
	public void SupprimerSemestreParFiliere(String NomF);
	public void SupprimerAbsSuppParModule(String NomM);
	public void SupprimerAbsParModule(String NomM);

	
}
