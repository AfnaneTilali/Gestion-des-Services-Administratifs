package Metier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.*;

public class CAbs implements IntAbs{
	Connection conn = SingletonLogin.getConnection();

	@Override
	public List<Module> getModule() {
		List<Module> Modules = new ArrayList<Module>();
		Module Module = null;
		try { 

			PreparedStatement ps =conn.prepareStatement("select * from modules") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {

				Module= new Module();

				Module.setNomM(rest.getString("NomM"));
				Module.setNomF(rest.getString("NomF"));
				Module.setCniPr(rest.getString("CNIPr"));
				Module.setNumS(rest.getInt("NumS"));
				Module.setNbrPres(rest.getInt("NbrPres"));
				Modules.add(Module);
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Modules;
	}

	@Override
	public int getNbrPr(String NomF, int NumS , String CNIPr) {	
		int NbrPr =0; 
		try {
			PreparedStatement ps =conn.prepareStatement("select NbrPres from modules m ,semestres s where s.NumS=m.NumS and s.NomF=m.NomF and m.NomF=? and m.NumS=? and m.CNIPr=?;") ; 
			ps.setString(1,NomF );
			ps.setInt(2,NumS );
			ps.setString(3,CNIPr );
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				NbrPr=rest.getInt("NbrPres");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return NbrPr;
	}
	@Override
	public void addAbsSupp(Absence a) {
		System.out.println("add Abs File function");
		try {
			PreparedStatement ps = conn.prepareStatement("insert into absencessupp(DateAbssupp,Cren1,Cren2,Cren3,Cren4,CNIE,CNIPr,NomM,Justification) values(?,?,?,?,?,?,?,?,?)");
			int success =0;
			ps.setDate(1, a.getDateAbs());
			ps.setBoolean(2, a.isCren1());
			ps.setBoolean(3, a.isCren2());
			ps.setBoolean(4, a.isCren3());
			ps.setBoolean(5, a.isCren4());
			ps.setString(6,a.getCniE());
			ps.setString(7,a.getCniPr());
			ps.setString(8,a.getNomM());
			ps.setBytes(9, a.getBytes());

			//		ps.setString(9,a.getNomF());
			//		ps.setInt(10, a.getNumS());
			success =ps.executeUpdate();
			if(success == 1) {
				System.out.println("File is stored");
				ps.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	@Override
	public void addAbs(Absence a) {
		System.out.println("add Abs function");
		try {
			PreparedStatement ps = conn.prepareStatement("insert into absences(DateAbs,Cren1,Cren2,Cren3,Cren4,CNIE,CNIPr,NomM,Justification) values(?,?,?,?,?,?,?,?,?)");
			int success =0;
			ps.setDate(1, a.getDateAbs());
			ps.setBoolean(2, a.isCren1());
			ps.setBoolean(3, a.isCren2());
			ps.setBoolean(4, a.isCren3());
			ps.setBoolean(5, a.isCren4());
			ps.setString(6,a.getCniE());
			ps.setString(7,a.getCniPr());
			ps.setString(8,a.getNomM());
			ps.setBytes(9, a.getBytes());

			//		ps.setString(9,a.getNomF());
			//		ps.setInt(10, a.getNumS());
			success =ps.executeUpdate();
			if(success == 1) {
				System.out.println("File is stored");
				ps.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
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
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Module;
	}
	@Override
	public void addPrs(Module m) {
		PreparedStatement pps;
		try {
			pps = conn.prepareStatement("update modules SET NbrPres=? WHERE NomM =? and NomF=?;");
			System.out.println("m.getNbrPres() : "+m.getNbrPres());
			System.out.println("m.getNomM() : "+m.getNomM());
			System.out.println("m.getNomF() : "+m.getNomF());

			pps.setInt(1,m.getNbrPres());
			pps.setString(2, m.getNomM());
			pps.setString(3, m.getNomF());
			pps.executeUpdate();
			pps.close();
			System.out.println("End addPrs");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	@Override
	public int CountParModuleEtCNIE(String Module, String CNI) {
		int Cpt = 0;
		try { 
			PreparedStatement ps =conn.prepareStatement("select count(IdAbs) from absences where CNIE=? and NomM=?") ; 
			ps.setString(1,CNI);
			ps.setString(2,Module);
			ResultSet rest =ps.executeQuery();  
			while(rest.next()) {
				Cpt=rest.getInt("count(IdAbs)");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Cpt;
	}

	@Override
	public List<Absence> getAllAbs() {
		List<Absence> abs = new ArrayList<Absence>();
		Absence a = null;
		try {
			PreparedStatement ps =conn.prepareStatement("select * from absences") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {

				a= new Absence();
				a.setIdAbs(rest.getInt("IdAbs"));
				a.setCniE(rest.getNString("CNIE"));
				a.setCniPr(rest.getString("CNIPr"));
				a.setNomM(rest.getString("NomM"));
				a.setDateAbs(rest.getDate("DateAbs"));
				a.setCren1(rest.getBoolean("Cren1"));
				a.setCren2(rest.getBoolean("Cren2"));
				a.setCren3(rest.getBoolean("Cren3"));
				a.setCren4(rest.getBoolean("Cren4"));
				abs.add(a); 				
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return abs;}

	@Override
	public void SupprimerAbs(int id) {

		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from absences where IdAbs=? ");
			ps.setInt(1,id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void ModifierAbs(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Absence RechercheAbs(String CniE, Date d) {
		Absence a =null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from absences WHERE CNIE=?and DateAbs=? ;");
			ps.setNString(1, CniE);
			ps.setDate(2, d);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				a = new Absence();
				a.setIdAbs(rs.getInt("IdAbs"));
				a.setCniE(rs.getString("CNIE"));
				a.setCniPr(rs.getString("CNIPr"));
				a.setNomM(rs.getString("NomM"));
				a.setDateAbs(rs.getDate("DateAbs"));
				a.setCren1(rs.getBoolean("Cren1"));
				a.setCren2(rs.getBoolean("Cren2"));
				a.setCren3(rs.getBoolean("Cren3"));
				a.setCren4(rs.getBoolean("Cren4"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return a;
	}

	@Override
	public Absence getAbsId(int id) {
		Absence a =null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from absences WHERE IdAbs=? ;");
			ps.setInt(1, id );
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				a = new Absence();
				a.setIdAbs(rs.getInt("IdAbs"));
				a.setCniE(rs.getString("CNIE"));
				a.setCniPr(rs.getString("CNIPr"));
				a.setNomM(rs.getString("NomM"));
				a.setDateAbs(rs.getDate("DateAbs"));
				a.setCren1(rs.getBoolean("Cren1"));
				a.setCren2(rs.getBoolean("Cren2"));
				a.setCren3(rs.getBoolean("Cren3"));
				a.setCren4(rs.getBoolean("Cren4"));
				a.setBytes(rs.getBytes("Justification"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return a;
	}

	@Override
	public List<AbsSupp> getAllAbsSupp() {
		List<AbsSupp> absSupp = new ArrayList<AbsSupp>();
		AbsSupp abs = null;
		try {
			PreparedStatement ps =conn.prepareStatement("select * from absencessupp") ; 
			ResultSet rest =ps.executeQuery(); 
			while(rest.next()) {
				abs= new AbsSupp();
				abs.setIdAbsSupp(rest.getInt("IdAbssupp"));
				abs.setCniPr(rest.getString("CNIPr"));
				abs.setIdAbsSupp(rest.getInt("IdAbsSupp"));
				abs.setNomM(rest.getString("NomM"));
				abs.setCniE(rest.getString("CNIE"));
				abs.setDateAbsSupp(rest.getDate("DateAbsSupp"));
				abs.setCren1(rest.getBoolean("Cren1"));
				abs.setCren2(rest.getBoolean("Cren2"));
				abs.setCren3(rest.getBoolean("Cren3"));
				abs.setCren4(rest.getBoolean("Cren4"));
				absSupp.add(abs); 				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return absSupp;
	}

}
