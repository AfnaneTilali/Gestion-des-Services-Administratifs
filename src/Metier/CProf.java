package Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Module;
import Entities.Prof;

public class CProf implements IntProf {
	Connection conn = SingletonLogin.getConnection();

	@Override
	public void SupprimerProf(String CNI) {
		System.out.println("SupprimerProf fonction");
		try {
			SupprimerAbsParProf(CNI);
			SupprimerAbsSuppParProf(CNI);
			SupprimerPresProfParCNIPr(CNI);
			SupprimerModuleParProf(CNI);
			PreparedStatement ps = conn.prepareStatement("delete from profs where CNIPr=?");

			ps.setString(1,CNI);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		} 	
		
	}

	@Override
	public void ModifierInfoProf(Prof p) {
		System.out.println("Start ModifierInfoProf ");
		try {
			PreparedStatement ps = conn.prepareStatement("update profs set NomPr=? ,PrenomPr=?, EmailPr=?,PhonePr=? where CNIPr=?");

			System.out.println("CNI prof  :  " + p.getCniPr());
			ps.setString(1, p.getNomPr());
			ps.setString(2,p.getPrenomPr());
			ps.setString(3, p.getEmailPr());
			ps.setInt(4, p.getPhonePr());
			ps.setString(5, p.getCniPr());

			ps.executeUpdate();
			ps.close();
			System.out.println("End ModifierInfoProf ");

		}catch (SQLException e1) {

			e1.printStackTrace();
		}		
		
	}

	@Override
	public void addProf(Prof p) {
		System.out.println("add Parent function");
		try {
			PreparedStatement ps = conn.prepareStatement("insert into profs(NomPr,PrenomPr,CNIPr,EmailPr,PhonePr"+") values(?,?,?,?,?)");
			ps.setString(1, p.getNomPr());
			ps.setString(2,p.getPrenomPr());
			ps.setString(3, p.getCniPr());
			ps.setString(4, p.getEmailPr());
			ps.setInt(5, p.getPhonePr());

			ps.executeUpdate();
			ps.close();	



		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
	}

	@Override
	public Prof RechercheProf(String CNI) {
		System.out.println("cni : " + CNI);
		Prof p = null ;

		if ( CNI!=null) {

			PreparedStatement ps;

			try {
				ps = conn.prepareStatement("select * from profs where CNIPr= ?");
				ps.setString(1,CNI);
				ResultSet res = ps.executeQuery();
				System.out.println("test Search Prof");
				p = new Prof();
				while(res.next()) {

					p.setNomPr(res.getString("NomPr"));
					p.setPrenomPr(res.getString("PrenomPr")) ;
					p.setCniPr(res.getString("CNIPr")) ;
					p.setEmailPr(res.getString("EmailPr")) ;
					p.setPhonePr(res.getInt("PhonePr"));
				}
				System.out.println("CNI Prof :" + p.getCniPr());
			} catch (Exception c) {

				c.printStackTrace();
			}
			
			if(p.getCniPr()==null) {
				System.out.println("CNI n'exsiste pas");
				return null;
			}
			else {
				return p;
			}

		} else {
			System.out.println("CNI is null !!");
		}
		return p;
	}

	@Override
	public Prof getProf(String CNI) {
		Prof p =null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from profs WHERE CNIPr=? ;");
			ps.setString(1, CNI );
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				p = new Prof();
				p.setNomPr(rs.getString("NomPr"));
				p.setPrenomPr(rs.getString("PrenomPr")) ;
				p.setCniPr(rs.getString("CNIPr")) ;
				p.setEmailPr(rs.getString("EmailPr")) ;
				p.setPhonePr(rs.getInt("PhonePr"));


			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(p==null) throw new RuntimeException("Prof qui a le Num de CNI : "+CNI+" inexistant");

		return p;
	}
	public Prof getProfN(String NomPr) {
		Prof p =null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from profs WHERE NomPr=? ;");
			ps.setString(1, NomPr );
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				p = new Prof();
				p.setNomPr(rs.getString("NomPr"));
				p.setPrenomPr(rs.getString("PrenomPr")) ;
				p.setCniPr(rs.getString("CNIPr")) ;
				p.setEmailPr(rs.getString("EmailPr")) ;
				p.setPhonePr(rs.getInt("PhonePr"));

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(p==null) throw new RuntimeException("Prof qui a le Nom : "+NomPr+" inexistant");

		return p;
	}
	@Override
	public List<Prof> getAllProf() {
		List<Prof> Profs = new ArrayList<Prof>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from profs ORDER BY NomPr ASC  ;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Prof p = new Prof();
				p.setNomPr(rs.getString("NomPr"));
				p.setPrenomPr(rs.getString("PrenomPr")) ;
				p.setCniPr(rs.getString("CNIPr")) ;
				p.setEmailPr(rs.getString("EmailPr")) ;
				p.setPhonePr(rs.getInt("PhonePr"));
				Profs.add(p);
			}
			ps.close();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		return Profs;
	}

	@Override
	public List<Module> getModuleParFS(String f,int S) {
		List<Module> Modules = new ArrayList<Module>();
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
				Modules.add(Module); 				
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return Modules;
	}
	@Override
	public String getCniPr(String Nom) {
		String CniPr = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT CNIPr from profs WHERE NomPr=? ;");
			ps.setString(1, Nom );
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				CniPr=rs.getString("CNIPr") ;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(CniPr==null) throw new RuntimeException("Prof qui a le Nom: "+Nom+" inexistant");
		return CniPr;
	}

	@Override
	public void SupprimerPresProfParCNIPr(String CNIPr) {

		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from presprof where CNIPr=? ");
			ps.setString(1,CNIPr);
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
	public void SupprimerAbsSuppParProf(String CniPr) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from absencessupp where CniPr=? ");
			ps.setString(1,CniPr);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void SupprimerAbsParProf(String CniPr) {
		PreparedStatement ps ;
		try {
			ps= conn.prepareStatement("delete from absences where CNIPr=? ");
			ps.setString(1,CniPr);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
