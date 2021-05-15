package Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.AbsSupp;
import Entities.Equipement;
import Entities.Filiere;
import Entities.Inscription;
import Entities.Module;
import Entities.PresProf;
import Entities.Salle;
import Entities.Semestre;

public class COther implements IntOther{
	Connection conn = SingletonLogin.getConnection();

	@Override
	public void SupprimerFiliere(String NomF) {
		try {
			SupprimerPresProfParFiliere(NomF);
			SupprimerInscParFiliere(NomF);
			SupprimerModuleParFiliere(NomF);
			SupprimerSemestreParFiliere(NomF);
			PreparedStatement ps ;
			ps= conn.prepareStatement("delete from filieres where NomF=?");
			ps.setString(1,NomF);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 	

	}

	@Override
	public void ModifierInfoFiliere(Filiere f) {
	
	}

	@Override
	public void addFiliere(Filiere f) {
		 
		try {
			PreparedStatement ps = conn.prepareStatement("insert into filieres(NomF,Chef,NbrModules,NbrSemestre) values(?,?,?,?)");
			ps.setString(1, f.getNomF());
			ps.setString(2, f.getChef());
			ps.setInt(3, f.getNbrModules());
			ps.setInt(4, f.getNbrSemestre());
			ps.executeUpdate();
			ps.close();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public List<Filiere> getAllFiliere() {

		List<Filiere> filieres = new ArrayList<Filiere>();
		Filiere fil = null;
		try { 
 
			PreparedStatement ps =conn.prepareStatement("select * from filieres") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				
				fil= new Filiere();
				fil.setNbrModules(rest.getInt("NbrModules"));
				fil.setChef(rest.getString("chef"));
				fil.setNomF(rest.getString("NomF"));
				fil.setNbrSemestre(rest.getInt("NbrSemestre"));
				filieres.add(fil); 				
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filieres;
	}

	@Override
	public void SupprimerModule( String NomF,String NomM) {
		PreparedStatement ps ;
		try {
			SupprimerPresProfParModule(NomM);
			System.out.println("SupprimerModule");
			ps= conn.prepareStatement("delete from modules where NomF=? and NomM=?");
			ps.setString(1,NomF);
			ps.setString(2,NomM);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void ModifierInfoModule(Module m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addModule(Module m) {
		 
		try {
			PreparedStatement ps = conn.prepareStatement("insert into modules(NomM,NomF,NumS,CNIPr,Seuille) values(?,?,?,?,?)");
			ps.setString(1, m.getNomM());
			ps.setString(2, m.getNomF());
			ps.setInt(3, m.getNumS());
			ps.setString(4, m.getCniPr());
			ps.setInt(5, m.getSeuille());
			ps.executeUpdate();
			ps.close();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public List<Module> getAllModule() {

		List<Module> modules = new ArrayList<Module>();
		Module mod = null;
		try { 
 
			PreparedStatement ps =conn.prepareStatement("select * from modules") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				
				mod= new Module();
				mod.setCniPr(rest.getString("CNIPr"));
				mod.setNbrPres(rest.getInt("NbrPres"));
				mod.setNomM(rest.getString("NomM"));
				mod.setNumS(rest.getInt("NumS"));
				mod.setNomF(rest.getString("NomF"));
				mod.setSeuille(rest.getInt("Seuille"));
				modules.add(mod); 				
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return modules;
	
	}

	@Override
	public void SupprimerSemestre(int NumS, String NomF) {
		PreparedStatement ps ;
		try {
			SupprimerPresProfParSemestre(NumS);
			SupprimerModuleParSemestre(NumS);
			SupprimerInscParSemestre(NumS);
			SupprimerModuleParFiliere(NomF);
			
		//	SupprimerPresProfParFiliere(NomF);
		//	SupprimerInscParFiliere(NomF);
		//	SupprimerSemestreParFiliere(NomF);

			ps= conn.prepareStatement("delete from semestres where NumS=? and NomF=?");
			ps.setInt(1,NumS);
			ps.setString(2,NomF);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void ModifierInfoSemestre(Semestre s) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addSemestre(Semestre s) {
		 
		try {
			PreparedStatement ps = conn.prepareStatement("insert into semestres(NumS,NomF,NbrModules) values(?,?,?)");
			ps.setString(2, s.getNomF());
			ps.setInt(1, s.getNumS());
			ps.setInt(3, s.getNbrModule());
			ps.executeUpdate();
			ps.close();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	}

	@Override
	public List<Semestre> getAllSemestre() {
		List<Semestre> semestres = new ArrayList<Semestre>();
		Semestre Semestre = null;
		try { 
 
			PreparedStatement ps =conn.prepareStatement("select * from semestres") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {	
				Semestre= new Semestre();
				Semestre.setNumS(rest.getInt("NumS"));
				Semestre.setNomF(rest.getString("NomF"));
				Semestre.setNbrModule(rest.getInt("NbrModules"));
				semestres.add(Semestre); 				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return semestres;
	}

	@Override
	public void SupprimerSalle(int NumS) {
		PreparedStatement ps ;
		try {
			SupprimerEquipementParSalle(NumS);
			ps= conn.prepareStatement("delete from salles where NumS=? ");
			ps.setInt(1,NumS);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void ModifierInfoSalle(Salle s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSalle(Salle s) {
		 
		try {
			PreparedStatement ps = conn.prepareStatement("insert into salles(NumS,EtageS) values(?,?)");
			ps.setInt(2, s.getEtageS());
			ps.setInt(1, s.getNumS());
			ps.executeUpdate();
			ps.close();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public List<Salle> getAllSalle() {

		List<Salle> salles = new ArrayList<Salle>();
		Salle Salle = null;
		try { 
 
			PreparedStatement ps =conn.prepareStatement("select * from salles") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				
				Salle= new Salle();
				Salle.setNumS(rest.getInt("NumS"));
				Salle.setEtageS(rest.getInt("EtageS"));
				salles.add(Salle); 				
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return salles;
	}

	@Override
	public void SupprimerInscription(int id) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from inscription where IdInscription=? ");
			ps.setInt(1,id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void ModifierInfoInscription(Inscription insc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addInscription(Inscription insc) {
		 
		try {
			PreparedStatement ps = conn.prepareStatement("insert into inscription(DateInscription,NomF,CNIE,NumS) values(?,?,?,?)");
			ps.setString(1,insc.getDateInscrtiption());
			ps.setString(3, insc.getCniE());
			ps.setString(2, insc.getNomF());
			ps.setInt(4, insc.getNumS());
			ps.executeUpdate();
			ps.close();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public List<Inscription> getAllInscription() {
		List<Inscription> Inscriptions = new ArrayList<Inscription>();
		Inscription Inscription = null;
		try {
 
			PreparedStatement ps =conn.prepareStatement("select * from inscription") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				Inscription= new Inscription();
				Inscription.setCniE(rest.getString("CNIE"));
				Inscription.setDateInscrtiption(rest.getString("DateInscription"));
				Inscription.setIdInscription(rest.getInt("IdInscription"));
				Inscription.setNomF(rest.getString("NomF"));
				Inscription.setNumS(rest.getInt("NumS"));
				Inscriptions.add(Inscription); 				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Inscriptions;
	}

	@Override
	public void SupprimerEquipement(int id) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from equipement where IdEq=? ");
			ps.setInt(1,id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void ModifierInfoEquipement(Equipement equipement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEquipement(Equipement eq) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into equipement(NomEq,NumEq,NumS) values(?,?,?)");
			ps.setInt(2,eq.getNumEq());
			ps.setString(1, eq.getNomEq());
			ps.setInt(3, eq.getNumS());
			ps.executeUpdate();
			ps.close();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public List<Equipement> getAllEquipement() {

		List<Equipement> Equipements = new ArrayList<Equipement>();
		Equipement Equipement = null;
		try {
 
			PreparedStatement ps =conn.prepareStatement("select * from Equipement") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				Equipement= new Equipement();
				Equipement.setIdEq(rest.getInt("IdEq"));
				Equipement.setNumS(rest.getInt("NumS"));
				Equipement.setNomEq(rest.getString("NomEq"));
				Equipement.setNumEq(rest.getInt("NumEq"));
				Equipements.add(Equipement); 				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Equipements;
	}

	@Override
	public List<PresProf> getAllPresProf() {
		List<PresProf> PresProfs = new ArrayList<PresProf>();
		PresProf PresProf = null;
		try {
 
			PreparedStatement ps =conn.prepareStatement("select * from presprof") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				PresProf= new PresProf();

				PresProf.setCniPr(rest.getString("CNIPr"));
				PresProf.setIdPresProf(rest.getInt("IdPresProf"));
				PresProf.setNomM(rest.getString("NomM"));
				PresProf.setNumS(rest.getInt("NumS"));
				PresProf.setNomF(rest.getString("NomF"));
				PresProf.setDatePres(rest.getDate("DatePresProf"));
				PresProf.setCren1(rest.getBoolean("Cren1"));
				PresProf.setCren2(rest.getBoolean("Cren2"));
				PresProf.setCren3(rest.getBoolean("Cren3"));
				PresProf.setCren4(rest.getBoolean("Cren4"));
				PresProfs.add(PresProf); 				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return PresProfs;
	}

	@Override
	public void SupprimerEquipementParSalle(int NumS) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from equipement where NumS=? ");
			ps.setInt(1,NumS);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerModuleParSemestre(int NumS) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from modules where NumS=? ");
			ps.setInt(1,NumS);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerModuleParProf(String CNIProf) {
		PreparedStatement ps ;
		try {
			
			ps= conn.prepareStatement("delete from modules where CNIPr=? ");
			ps.setString(1,CNIProf);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerModuleParFiliere(String NomF) {
		PreparedStatement ps ;
		try {
			Module NomM = null;
			try {
	 
				ps =conn.prepareStatement("select * from modules where NomF=?") ; 
				ps.setString(1,NomF);
				ResultSet rest =ps.executeQuery(); 
				while(rest.next()) {
					NomM= new Module();
					NomM.setNomM(rest.getString("NomM"));
					String nomM = NomM.getNomM();
					SupprimerPresProfParModule(nomM);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			ps= conn.prepareStatement("delete from modules where NomF=? ");
			ps.setString(1,NomF);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerPresProfParSemestre(int NumS) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from presprof where NumS=? ");
			ps.setInt(1,NumS);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerInscParSemestre(int NumS) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from inscription where NumS=? ");
			ps.setInt(1,NumS);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerInscParFiliere(String NomF) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from inscription where NomF=? ");
			ps.setString(1,NomF);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerPresProfParFiliere(String NomF) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from presprof where NomF=? ");
			ps.setString(1,NomF);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerPresProfParModule(String NomM) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from presprof where NomM=? ");
			ps.setString(1,NomM);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerSemestreParFiliere(String NomF) {
		
		PreparedStatement ps ;
		try {
		//	List<Semestre>NumS = new ArrayList<Semestre>();
			Semestre NumSs = null;
			try {
	 
				ps =conn.prepareStatement("select * from semestres where NomF=?") ; 
				ps.setString(1,NomF);
				ResultSet rest =ps.executeQuery(); 
				while(rest.next()) {
					NumSs= new Semestre();
					NumSs.setNumS(rest.getInt("NumS"));
					int numS = NumSs.getNumS();
					SupprimerModuleParSemestre(numS);
					SupprimerPresProfParSemestre(numS);
					SupprimerInscParSemestre(numS);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			ps= conn.prepareStatement("delete from semestres where NomF=? ");
			ps.setString(1,NomF);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void addPresProf(PresProf pres) {
		 
		try {
			PreparedStatement ps = conn.prepareStatement("insert into presprof(DatePresProf,Cren1,Cren2,Cren3,Cren4,NomF,CNIPr,NomM,NumS) values(?,?,?,?,?,?,?,?,?)");
			ps.setDate(1, pres.getDatePres());
			ps.setBoolean(2, pres.isCren1());
			ps.setBoolean(3, pres.isCren2());
			ps.setBoolean(4, pres.isCren3());
			ps.setBoolean(5, pres.isCren4());
			ps.setString(6,pres.getNomF());
			ps.setString(7,pres.getCniPr());
			ps.setString(8,pres.getNomM());
			ps.setInt(9,pres.getNumS());
			ps.executeUpdate();
			ps.close();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public Module getModule(String Mod, String fil) {
		Module Module = null;
		try { 

			PreparedStatement ps =conn.prepareStatement("select * from modules m ,semestres s where s.NumS=m.NumS and s.NomF=m.NomF and m.NomF=? and m.NomM=? ;") ; 
			ps.setString(1, fil);
			ps.setString(2, Mod);
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {

				Module= new Module();
				Module.setNomM(rest.getString("NomM"));
				Module.setNomF(rest.getString("NomF"));
				Module.setCniPr(rest.getString("CNIPr"));
				Module.setNumS(rest.getInt("NumS"));
				Module.setNbrPres(rest.getInt("NbrPres"));
				Module.setSeuille(rest.getInt("Seuille"));
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Module;
	}

	@Override
	public Module getModuleParFS(String f, int S) {
		Module Module = null;
		try { 

			PreparedStatement ps =conn.prepareStatement("select * from modules m ,semestres s where s.NumS=m.NumS and s.NomF=m.NomF and m.NomF=? and m.NumS=? ;") ; 
			ps.setString(1, f);
			ps.setInt(2, S);
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				Module= new Module();
				Module.setNomM(rest.getString("NomM"));
				Module.setNomF(rest.getString("NomF"));
				Module.setCniPr(rest.getString("CNIPr"));
				Module.setNumS(rest.getInt("NumS"));
				Module.setNbrPres(rest.getInt("NbrPres"));
				Module.setSeuille(rest.getInt("Seuille"));
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Module;
	}

	@Override
	public void SupprimerAbsSuppParModule(String NomM) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from absencessupp where NomM=? ");
			ps.setString(1,NomM);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerAbsParModule(String NomM) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from absences where NomM=? ");
			ps.setString(1,NomM);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}


}
