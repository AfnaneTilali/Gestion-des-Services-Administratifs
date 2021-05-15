package Metier;

import java.util.List;

import Entities.Etudiant;
import Entities.Inscription;

public interface IntEtudiant {
	
	public void SupprimerEtudiant(String CNI);
	public void ModifierInfoEtudiant(Etudiant e);
	public void addEtudiant(Etudiant e,Inscription Ins);
	public Etudiant RechercheEtudiant(String CNI);
	
	public Etudiant getEtudiant(String CNI);
	public Etudiant getEtudiantParParent(String CNIP);
	public List <Etudiant>getAllEtudiant();
	
	public List <Etudiant>getEtudiantParFiliere(String f);
	public List <Etudiant>getEtudiantParNbrAbs(int NbrAbs);
	public List <Etudiant>getEtudiantParSemestre(int s);
	public List <Etudiant>getEtudiantParAnnUniv(String AnnUniv);

	List<Etudiant> getEtudiantParFiliereSemestre(String filiere,int Semestre);
	List<Etudiant> getEtudiantParFiliereNbrAbs(String filiere,int NbrAbs);
	List<Etudiant> getEtudiantParFiliereAnnUniv(String filiere,String AnnUniv);
	List<Etudiant> getEtudiantParFiliereSemestreAnnUniv(String filiere,int Semestre,String AnnUniv);
	List<Etudiant> getEtudiantParFiliereSemestreNbrAbs(String filiere,int Semestre,int NbrAbs);
	List<Etudiant> getEtudiantParFiliereAnnUnivNbrAbs(String filiere,int NbrAbs,String AnneUniv);
	List<Etudiant> getEtudiantParSemestreAnneUniv(int Semestre, String AnnUniv);
	List<Etudiant> getEtudiantParSemestreNbrAbs(int Semestre,int NbrAbs);
	List<Etudiant> getEtudiantParSemestreAnnUnivNbrAbs(int Semestre,String AnnUniv, int NbrAbs);
	List<Etudiant> getEtudiantParAnnUnivNbrAbs(int NbrAbs,String AnneUniv);

	List<Etudiant> getEtudiantParFiliereSemestreAnnUnivNbrAbs(String filiere,int Semestre,String AnnUniv,int NbrAbs);
	
	public void SupprimerInscParCNIE(String CNIE);
	public void SupprimerAbsSuppParE(String CniE);

}
