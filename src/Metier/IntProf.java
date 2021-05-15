package Metier;

import java.util.List;

import Entities.Module;
import Entities.Prof;

public interface IntProf {
	
	public void SupprimerProf(String CNI);
	public void ModifierInfoProf(Prof p);
	public void addProf(Prof p);
	public Prof RechercheProf(String CNI);
	
	public Prof getProfN(String NomPr) ;
	public Prof getProf(String CNI);
	public String getCniPr(String Nom);
	public List <Prof>getAllProf();
	public List<Module> getModuleParFS(String f,int S);
	
	public void SupprimerPresProfParCNIPr(String CNIPr);
	public void SupprimerModuleParProf(String CNIProf);
	public void SupprimerAbsSuppParProf(String CniPr);
	public void SupprimerAbsParProf(String CniPr);

}
