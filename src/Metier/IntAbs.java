package Metier;

import java.sql.Date;
import java.util.List;

import Entities.*;

public interface IntAbs {
	List<Module> getModule();
	public void addAbsSupp(Absence a);
	public void addAbs(Absence a);
	public Module getModuleParFS(String f,int S);
	public List <Absence>getAllAbs();
	public void SupprimerAbs(int id);
	public void ModifierAbs(int id);
	public Absence RechercheAbs(String CniE , Date d);
	public Absence getAbsId(int id);
	void addPrs(Module m);
	
	int getNbrPr(String NomF, int NumS, String CNIPr);
	int CountParModuleEtCNIE(String Module , String CNI);
	
	public List<AbsSupp> getAllAbsSupp();


}
