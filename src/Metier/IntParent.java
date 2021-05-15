package Metier;

import java.util.List;

import Entities.Parent;

public interface IntParent {

	public void SupprimerParent(String CNI);
	public void ModifierInfoParent(Parent e);
	public void addParent(Parent e);
	public Parent RechercheParent(String CNI);
	
	public Parent getParent(String CNI);
	public List <Parent>getAllParent();
	public void UpPdf();

}
