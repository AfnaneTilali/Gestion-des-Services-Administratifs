package Metier;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Entities.Parent;

public class CParent implements IntParent{
	Connection conn = SingletonLogin.getConnection();

	
	@Override
	public void SupprimerParent(String CNI) {

		System.out.println("SupprimerParent fonction");
		try {

			PreparedStatement ps = conn.prepareStatement("delete from Parents where CNIP=?");

			ps.setString(1,CNI);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		} 		
	}

	@Override
	public void ModifierInfoParent(Parent p) {
	//	System.out.println("Start ModifierInfoParent ");
		try {
			PreparedStatement ps = conn.prepareStatement("update parents set NomP=? ,PrenomP=?, EmailP=?,PhoneP=? where CNIP=?");

			System.out.println("CNI parent  :  " + p.getCniP());
			ps.setString(1, p.getNomP());
			ps.setString(2,p.getPrenomP());
			ps.setString(3, p.getEmailP());
			ps.setInt(4, p.getPhone());
			ps.setString(5, p.getCniP());

			ps.executeUpdate();
			ps.close();
	//		System.out.println("End ModifierInfoParent ");

		}catch (SQLException e1) {

			e1.printStackTrace();
		}		
	}

	@Override
	public void addParent(Parent e) {
		System.out.println("add Parent function");
		try {
			PreparedStatement ps = conn.prepareStatement("insert into parents(NomP,PrenomP,CNIP,EmailP,PhoneP"+") values(?,?,?,?,?)");
			ps.setString(1, e.getNomP());
			ps.setString(2,e.getPrenomP());
			ps.setString(3, e.getCniP());
			ps.setString(4, e.getEmailP());
			ps.setInt(5, e.getPhone());

			ps.executeUpdate();
			ps.close();	



		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

	@Override
	public Parent RechercheParent(String CNI) {
		System.out.println("cni : " + CNI);
		Parent p = null ;

		if ( CNI!=null) {

			PreparedStatement ps;

			try {
				ps = conn.prepareStatement("select * from parents where CNIP= ?");
				ps.setString(1,CNI);
				ResultSet res = ps.executeQuery();
				System.out.println("test Search");
				p = new Parent();
				while(res.next()) {

					p.setNomP(res.getString("NomP"));
					p.setPrenomP(res.getString("PrenomP")) ;
					p.setCniP(res.getString("CNIP")) ;
					p.setEmailP(res.getString("EmailP")) ;
					p.setPhone(res.getInt("PhoneP"));

				}
				System.out.println("CNI Parent :" + p.getCniP());
			} catch (Exception c) {

				c.printStackTrace();
			}
			
			if(p.getCniP()==null) {
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
	public Parent getParent(String CNI) {
		Parent p =null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from parents WHERE CNIP=? ;");
			ps.setString(1, CNI );
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				p = new Parent();
				p.setNomP(rs.getString("NomP"));
				p.setPrenomP(rs.getString("PrenomP")) ;
				p.setCniP(rs.getString("CNIP")) ;
				p.setEmailP(rs.getString("EmailP")) ;
				p.setPhone(rs.getInt("PhoneP"));


			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(p==null) throw new RuntimeException("Parent qui a le Num de CNI : "+CNI+" inexistant");

		return p;
	}

	@Override
	public List<Parent> getAllParent() {
		List<Parent> Parents = new ArrayList<Parent>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from Parents ORDER BY NomP ASC  ;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Parent p = new Parent();
				p.setNomP(rs.getString("NomP"));
				p.setPrenomP(rs.getString("PrenomP")) ;
				p.setCniP(rs.getString("CNIP")) ;
				p.setEmailP(rs.getString("EmailP")) ;
				p.setPhone(rs.getInt("PhoneP"));

				Parents.add(p);

			}
			ps.close();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}


		return Parents;
	}

	@Override
	public void UpPdf() {

		Document doc = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("ListParent.pdf"));
			doc.open();
			doc.add(new Paragraph("List All Parent"));
			doc.close();
			writer.close();
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}	
	
	}

}
