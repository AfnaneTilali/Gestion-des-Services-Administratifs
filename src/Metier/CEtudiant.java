package Metier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Etudiant;
import Entities.Filiere;
import Entities.Inscription;
import Metier.SingletonLogin;

public class CEtudiant implements IntEtudiant{
	Connection conn = SingletonLogin.getConnection();



	@Override
	public void SupprimerEtudiant(String CNI) {

		System.out.println("SupprimerEtudiant fonction");
		try {
			SupprimerAbsSuppParE(CNI);
			SupprimerInscParCNIE(CNI);
			PreparedStatement pss;
			pss = conn.prepareStatement("delete from absences where CNIE=?");
			pss.setString(1,CNI );
			pss.executeUpdate();
			pss.close();
			pss = conn.prepareStatement("delete from etudiants where CNIE=?");
			pss.setString(1,CNI );
			pss.executeUpdate();
			pss.close();


		} catch (SQLException e1) {

			e1.printStackTrace();
		} 
	}

	@Override
	public void ModifierInfoEtudiant(Etudiant e) {
		System.out.println("Start ModifierInfoEtudiant ");
		try {
			PreparedStatement ps = conn.prepareStatement("update Etudiants set NomE=? ,PrenomE=? ,CINE=?,AdresseE=?,EmailE=?,PhoneE=?,SexeE=?,FiliereE=?,VilleNE=?,DateN=?,NbrAbs=?,AnneeUniv=?,Country=?,Dossier=? where CNIE=?");
			System.out.println("CNI etud  : " + e.getCni());
			ps.setString(1, e.getNom());
			ps.setString(2, e.getPrenom());
			ps.setString(3, e.getCin());
			ps.setString(4, e.getAdresse());
			ps.setString(5, e.getEmail());
			ps.setInt(6, e.getPhone());
			ps.setString(7, e.getSexe());
			ps.setString(8, e.getFiliere());
			ps.setString(9, e.getVilleN());
			ps.setString(10, e.getDateN());
			ps.setInt(11, e.getNbrAbs());
			ps.setString(12, e.getAnneeUniv());
			ps.setString(13, e.getCountry());
			ps.setBytes(14, e.getBytes());
			ps.setString(15, e.getCni());
			ps.executeUpdate();
			ps.close();
			System.out.println("End ModifierInfoEtudiant ");
		}catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

	@Override
	public void addEtudiant(Etudiant e,Inscription Ins){
		System.out.println("add function");
		try {
			PreparedStatement ps = conn.prepareStatement("insert into Etudiants(NomE,PrenomE, CINE,CNIE,AdresseE,EmailE,PhoneE,SexeE,VilleNE,DateN,FiliereE,Dossier,CNIP,Country,AnneeUniv,Tof" +") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			int success =0;
			ps.setString(1, e.getNom());
			ps.setString(2,e.getPrenom());
			ps.setString(3, e.getCin());
			ps.setString(4, e.getCni());
			ps.setString(5, e.getAdresse());
			ps.setString(6, e.getEmail());
			ps.setInt(7, e.getPhone());
			ps.setString(8, e.getSexe());
			ps.setString(9, e.getVilleN());
			ps.setString(10, e.getDateN());
			ps.setString(11, e.getNomF());
			ps.setBytes(12, e.getBytes());
			ps.setString(13, e.getCniP());
			ps.setString(14, e.getCountry());
			ps.setString(15, e.getAnneeUniv());
			ps.setBlob(16, e.getImage());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("File is stored");
				ps.close();
			}
			PreparedStatement pps = conn.prepareStatement("insert into inscription(CNIE , NomF , DateInscription,NumS) values(?,?,?,?)");
			pps.setString(1, Ins.getCniE());
			pps.setString(2, Ins.getNomF());
			pps.setString(3, Ins.getDateInscrtiption());
			pps.setInt(4,Ins.getNumS());
			pps.executeUpdate();
			pps.close();	
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public List<Etudiant> getAllEtudiant() {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants ORDER BY NomE ASC  ;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Etudiant e = new Etudiant();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				Etudiants.add(e);

			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParFiliere(String f) {
		List<Etudiant> EtudiantsParF = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants WHERE FiliereE=? ;");
			ps.setString(1, f );
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;  
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				EtudiantsParF.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		return EtudiantsParF;
	}

	@Override
	public List<Etudiant> getEtudiantParNbrAbs(int NbrAbs) {
		List<Etudiant> EtudiantsParNbrAbs = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants WHERE NbrAbs=? ;");
			ps.setInt(1, NbrAbs );
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;  
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));

				EtudiantsParNbrAbs.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		return EtudiantsParNbrAbs;
	}

	@Override
	public Etudiant getEtudiant(String CNI) {
		Etudiant e =null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants WHERE CNIE=? ;");
			ps.setString(1, CNI );
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				e = new Etudiant();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setNbrAbs(rs.getInt("NbrAbs"));
				e.setCniP(rs.getString("CNIP"));
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setNbrAbs(rs.getInt("NbrAbs"));

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(e==null) throw new RuntimeException("Etudiant qui a le Num de CNI : "+CNI+" inexistant");

		return e;
	}


	@Override
	public Etudiant RechercheEtudiant(String CNI) {

		//	boolean re= false;

		System.out.println("validation RechercheEtud !! ");
		System.out.println("cni : " + CNI);
		Etudiant e = null ;
		if ( CNI!=null) {

			PreparedStatement ps;

			try {
				ps = conn.prepareStatement("select * from etudiants where CNIE= ?");
				ps.setString(1,CNI);
				ResultSet res = ps.executeQuery();
				System.out.println("test Search");
				e = new Etudiant();
				while(res.next()) {
					e.setNom(res.getString("NomE"));
					e.setPrenom(res.getString("PrenomE")) ;
					e.setCni(res.getString("CNIE")) ;
					e.setCin(res.getString("CINE")) ;
					e.setAdresse(res.getString("AdresseE")) ;
					e.setEmail(res.getString("EmailE")) ;
					e.setPhone(res.getInt("PhoneE"));
					e.setSexe(res.getString("SexeE")) ;
					e.setFiliere(res.getString("FiliereE")) ;
					e.setVilleN(res.getString("VilleNE")) ;
					e.setDateN(res.getString("DateN")) ;
					e.setCniP(res.getString("CNIP"));
					e.setAnneeUniv(res.getString("AnneeUniv"));
					e.setCountry(res.getString("Country"));
					e.setBytes(res.getBytes("Dossier"));
					e.setNbrAbs(res.getInt("NbrAbs"));
				}	

			} catch (Exception c) {

				c.printStackTrace();
			}

			if(e.getCni()==null) {
				//	re= false;
				System.out.println("CNI n'exsiste pas");

				return null;
			}
			else {
				//	re = true ;
				return e;
			}

		} else {
			System.out.println("CNI is null !!");
		}
		return e;
	}


	public List<Etudiant> getEtudiantParSemestre(int s) {

		System.out.println("Start getEtudiantParSemestre");
		List<Etudiant> EtudiantsParF = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e , inscription i WHERE i.NumS=? and i.CNIE=e.CNIE ;");
			ps.setInt(1, s);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i=new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;  
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setCniP(rs.getString("CNIP"));
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				EtudiantsParF.add(e);
				System.out.println("End getEtudiantParSemestre");

			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		return EtudiantsParF;
	}


	@Override
	public List<Etudiant> getEtudiantParFiliereSemestre(String f, int s) {
		List<Etudiant> EtudiantsParFS = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NomF=? and NumS=? ;");
			ps.setString(1, f );
			ps.setInt(2, s );
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i=new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				EtudiantsParFS.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return EtudiantsParFS;
	}


	@Override
	public Etudiant getEtudiantParParent(String CNIP) {
		Etudiant e =null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants WHERE CNIP=? ;");
			ps.setString(1, CNIP );
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				e = new Etudiant();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setNbrAbs(rs.getInt("NbrAbs"));
				e.setCniP(rs.getString("CNIP"));
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setNbrAbs(rs.getInt("NbrAbs"));

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(e==null) throw new RuntimeException("Le Num de CNIP : "+CNIP+" inexistant");

		return e;
	}

	@Override
	public void SupprimerInscParCNIE(String CNIE) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from inscription where CNIE=? ");
			ps.setString(1,CNIE);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<Etudiant> getEtudiantParFiliereNbrAbs(String filiere, int NbrAbs) {
		List<Etudiant> EtudiantsParFNbrAbs = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NomF=? and NbrAbs=? ;");
			ps.setString(1, filiere );
			ps.setInt(2, NbrAbs );
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				EtudiantsParFNbrAbs.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return EtudiantsParFNbrAbs;
	}

	@Override
	public List<Etudiant> getEtudiantParFiliereAnnUniv(String filiere, String AnnUniv) {
		List<Etudiant> EtudiantsParFAnnUniv = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NomF=? and AnneeUniv=? ;");
			ps.setString(1, filiere );
			ps.setString(2, AnnUniv );
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				EtudiantsParFAnnUniv.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return EtudiantsParFAnnUniv;
	}

	@Override
	public List<Etudiant> getEtudiantParFiliereSemestreAnnUniv(String filiere, int Smestre, String AnnUniv) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NomF=? and AnneeUniv=? and NumS=?;");
			ps.setString(1, filiere );
			ps.setString(2, AnnUniv );
			ps.setInt(3, Smestre);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParFiliereSemestreNbrAbs(String filiere, int Smestre, int NbrAbs) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NomF=? and NbrAbs=? and NumS=?;");
			ps.setString(1, filiere );
			ps.setInt(2, NbrAbs );
			ps.setInt(3, Smestre);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParFiliereAnnUnivNbrAbs(String filiere, int NbrAbs, String AnneUniv) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NomF=? and NbrAbs=? and AnneeUniv=?;");
			ps.setString(1, filiere );
			ps.setInt(2, NbrAbs );
			ps.setString(3, AnneUniv);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParSemestreAnneUniv(int Semestre, String AnnUniv) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NumS=? and AnneeUniv=?;");
			ps.setInt(1, Semestre );
			ps.setString(2, AnnUniv);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}
			ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParSemestreNbrAbs(int Semestre, int NbrAbs) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NumS=? and NbrAbs=?;");
			ps.setInt(1, Semestre );
			ps.setInt(2, NbrAbs);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				e.setCniP(rs.getString("CNIP"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}ps.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParSemestreAnnUnivNbrAbs(int Semestre, String AnnUniv, int NbrAbs) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and  NumS=? and NbrAbs=? and AnneeUniv=?;");
			ps.setInt(1, Semestre );
			ps.setInt(2, NbrAbs);
			ps.setString(3, AnnUniv);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParAnnUnivNbrAbs(int NbrAbs, String AnneUniv) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and NbrAbs=? and AnneeUniv=?;");
			ps.setInt(1, NbrAbs );
			ps.setString(2, AnneUniv);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParFiliereSemestreAnnUnivNbrAbs(String filiere, int Semestre, String AnnUniv,
			int NbrAbs) {
		List<Etudiant> Etudiants = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants e,inscription i WHERE e.CNIE=i.CNIE and NomF=? and NumS=? NbrAbs=? and AnneeUniv=?;");
			ps.setString(1, filiere);
			ps.setInt(2, Semestre );
			ps.setInt(3, NbrAbs);
			ps.setString(4, AnnUniv);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				Inscription i =new Inscription();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				i.setDateInscrtiption(rs.getString("DateInscription"));
				i.setIdInscription(rs.getInt("IdInscription"));
				i.setNomF(rs.getString("NomF"));
				i.setCniE(rs.getString("CNIE"));
				i.setNumS(rs.getInt("NumS"));
				Etudiants.add(e);
			}ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Etudiants;
	}

	@Override
	public List<Etudiant> getEtudiantParAnnUniv(String AnnUniv) {
		List<Etudiant> EtudiantsParAnnUniv = new ArrayList<Etudiant>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from etudiants WHERE AnneeUniv=? ;");
			ps.setString(1, AnnUniv );
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Etudiant e = new Etudiant();
				e.setNom(rs.getString("NomE"));
				e.setPrenom(rs.getString("PrenomE")) ;
				e.setCni(rs.getString("CNIE")) ;
				e.setCin(rs.getString("CINE")) ;
				e.setAdresse(rs.getString("AdresseE")) ;  
				e.setEmail(rs.getString("EmailE")) ;
				e.setPhone(rs.getInt("PhoneE"));
				e.setSexe(rs.getString("SexeE")) ;
				e.setFiliere(rs.getString("FiliereE")) ;
				e.setVilleN(rs.getString("VilleNE")) ;
				e.setDateN(rs.getString("DateN")) ;
				e.setAnneeUniv(rs.getString("AnneeUniv"));
				e.setCountry(rs.getString("Country"));
				e.setBytes(rs.getBytes("Dossier"));
				e.setCniP(rs.getString("CNIP"));
				e.setNbrAbs(rs.getInt("NbrAbs"));
				EtudiantsParAnnUniv.add(e);
			}
			ps.close();
		} catch (SQLException e1) {	
			e1.printStackTrace();
		}
		return EtudiantsParAnnUniv;
	}
	@Override
	public void SupprimerAbsSuppParE(String CniE) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from absencessupp where CNIE=? ");
			ps.setString(1,CniE);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
