package Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.RESERVATIONS;


public class CRes implements IRes{
	Connection conn = SingletonLogin.getConnection();
	private Map<String, String> Erreurs = new HashMap<String, String>();

	public int makeReservation(RESERVATIONS r) {
		List<RESERVATIONS> lst = reservationList();

		//La variable k va nous aider à choisir si la réservation va se dérouler normalement
		//ou une ancienne réservation va être modifiée 
		int k = 0;
		for(RESERVATIONS re:lst) {
			if(r.getDate_res().equals(re.getDate_res()) && r.getNum_salle() == re.getNum_salle()) {k++;}
		}



		try {

			//On va parcourir la liste de réservation pour pouvoir accéder
			//à une réservation ancienne et la modifier pour ajouter le nouveau créneau
			for(RESERVATIONS re:lst)
			{
				if(r.getDate_res().equals(re.getDate_res()) && r.getNum_salle() == re.getNum_salle())
				{

					PreparedStatement ps1 = conn.prepareStatement
							("UPDATE RESERVATIONS SET Cren? = ? WHERE NumR = ?");
					//On sauvegarde la position du créneau (Créneau 1,2,3 ou 4)
					int index1 = 0;
					if(r.isCr1()) index1 = 1;
					else if(r.isCr2()) index1 = 2;
					else if(r.isCr3()) index1 = 3;
					else if(r.isCr4()) index1 = 4;
					else index1 = -1;

					int index1de4 = 0;
					int index2de4 = 0;
					int index3de4 = 0;
					int index4de4 = 0;
					if(re.isCr1()) index1de4 = 1;
					if(re.isCr2()) index2de4 = 2;
					if(re.isCr3()) index3de4 = 3;
					if(re.isCr4()) index4de4 = 4;

					if(index1 == index1de4 || index1 == index2de4 || index1 == index3de4 || index1 == index4de4) {
						return 1;
					}
					else {
						ps1.setInt(1, index1);
						ps1.setBoolean(2, true);
						ps1.setInt(3, re.getNum_reservation());
						ps1.executeUpdate();
						ps1.close();
					}
				}
			}

			if(k == 0)
			{
				PreparedStatement ps2 = conn.prepareStatement
						("INSERT INTO RESERVATIONS(NumS,DateR,Cren1,Cren2,Cren3,Cren4) VALUES (?,?,?,?,?,?)");
				ps2.setInt(1, r.getNum_salle());
				ps2.setString(2, r.getDate_res());
				ps2.setBoolean(3, r.isCr1());
				ps2.setBoolean(4, r.isCr2());
				ps2.setBoolean(5, r.isCr3());
				ps2.setBoolean(6, r.isCr4());
				ps2.executeUpdate();
				ps2.close();
			}




		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void deleteReservation(int NumRes, int cr) {

		try {

			//Vu que supprimer une réservation concerne seulement un seul créneau
			//donc on ne peux pas supprimer la réservation entièrement
			PreparedStatement ps = conn.prepareStatement
					("UPDATE RESERVATIONS SET Cren? = ? WHERE Num_Reservation = ?");
			//Pour éviter la redendonce j'ai ajouté un ? a la fin du nom de variable pour pouvoir
			//parcourir les créneaux facilement
			ps.setInt(1, cr);
			ps.setBoolean(2, false);
			ps.setInt(3, NumRes);
			ps.executeUpdate();
			ps.close();
			//ps = conn.prepareStatement("SELECT COUNT(Cren?) FROM RESERVATIONS WHERE Cren1 = FALSE AND Num_Reservation = 82");
			//String sql = "SELECT COUNT(Cren?) FROM RESERVATIONS WHERE Cren1 = FALSE AND Num_Reservation = 82" ;
			PreparedStatement ps2;
			RESERVATIONS rv = getReservation(NumRes);

			//Si la réservation ne contient qu'un seul créneau réservé
			// donc on peux supprimer la réservation une fois pour toute
			if(!rv.isCr1()  && !rv.isCr2() && !rv.isCr3() && !rv.isCr4() ) {	
				ps2 = conn.prepareStatement
						("DELETE FROM  RESERVATIONS WHERE NumR = ?");
				ps2.setInt(1, NumRes);
				ps2.executeUpdate();
				ps2.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifierReservation(RESERVATIONS r) {
		try {
			PreparedStatement ps = conn.prepareStatement
					("UPDATE RESERVATIONS SET NumS = ?,DateR=?, Cren1 = ?,Cren2 = ?,Cren3 = ?, Cren4 = ? WHERE NumR = ?");
			ps.setInt(1, r.getNum_salle());
			ps.setString(2, r.getDate_res());
			ps.setBoolean(3, r.isCr1());
			ps.setBoolean(4, r.isCr2());
			ps.setBoolean(5, r.isCr3());
			ps.setBoolean(6, r.isCr4());
			ps.setInt(7, r.getNum_reservation());			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<RESERVATIONS> reservationList() {
		List<RESERVATIONS> res = new ArrayList<RESERVATIONS>();
		try {
			PreparedStatement ps = conn.prepareStatement
					("SELECT * FROM RESERVATIONS ");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				RESERVATIONS r = new RESERVATIONS();
				r.setNum_reservation(rs.getInt("NumR"));
				r.setNum_salle(rs.getInt("NumS"));
				r.setDate_res(rs.getString("DateR"));
				r.setCr1(rs.getBoolean("Cren1"));
				r.setCr2(rs.getBoolean("Cren2"));
				r.setCr3(rs.getBoolean("Cren3"));
				r.setCr4(rs.getBoolean("Cren4"));
				res.add(r);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public RESERVATIONS getReservation(int Num_Res) {
		RESERVATIONS r = null;
		try {
			PreparedStatement ps = conn.prepareStatement
					("SELECT * FROM RESERVATIONS WHERE NumR = ?");
			ps.setInt(1,Num_Res);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				r = new RESERVATIONS();
				r.setNum_reservation(rs.getInt("NumR"));
				r.setNum_salle(rs.getInt("NumS"));
				r.setDate_res(rs.getString("DateR"));
				r.setCr1(rs.getBoolean("Cren1"));
				r.setCr2(rs.getBoolean("Cren2"));
				r.setCr3(rs.getBoolean("Cren3"));
				r.setCr4(rs.getBoolean("Cren4"));
			}
			ps.close();
		} catch (SQLException e) {
			if(r==null) throw new RuntimeException("Reservation Introuvable");
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<RESERVATIONS> reservationParDate(String date) {
		List<RESERVATIONS> res = new ArrayList<RESERVATIONS>();
		try {
			PreparedStatement ps = conn.prepareStatement
					("SELECT * FROM RESERVATIONS WHERE DateR LIKE ?");
			ps.setString(1, date);

			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				RESERVATIONS r = new RESERVATIONS();
				r.setNum_reservation(rs.getInt("NumR"));
				r.setNum_salle(rs.getInt("NumS"));
				r.setDate_res(rs.getString("DateR"));
				r.setCr1(rs.getBoolean("Cren1"));
				r.setCr2(rs.getBoolean("Cren2"));
				r.setCr3(rs.getBoolean("Cren3"));
				r.setCr4(rs.getBoolean("Cren4"));
				res.add(r);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public int getNumRes(String date , int numSalle) {
		int numRes = 0 ;
		try {
			PreparedStatement ps = conn.prepareStatement
					("SELECT Num_Reservation FROM RESERVATIONS WHERE DateR LIKE ? AND NumS = ?  ");
			ps.setString(1, date);
			ps.setInt(2, numSalle);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				RESERVATIONS r = new RESERVATIONS();
				numRes = rs.getInt("NumR");
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numRes;
	}

	@Override
	public List<RESERVATIONS> reservationParDateSalles(String date, int cr) {
		List<RESERVATIONS> res = new ArrayList<RESERVATIONS>();
		List<RESERVATIONS> noRes = reservationList();
		int noNum [] = new int[9];
		int index = 0;
		for(RESERVATIONS re:noRes) {
			if(re.getDate_res().equals(date))
			{
				noNum[index] = re.getNum_salle();
				index++;
			}
		}

		try {
			PreparedStatement ps = conn.prepareStatement
					("SELECT * FROM RESERVATIONS WHERE DateR LIKE ? AND  Cren?=? ORDER BY NumS");
			ps.setString(1, date);
			ps.setInt(2, cr);
			ps.setBoolean(3, false);
			ResultSet rs = ps.executeQuery();
			int save = 1;
			String last = null;
			while(rs.next())
			{	int i;
			if(rs.getInt("NumS") != save)
			{


				for( i = save ; i < rs.getInt("NumS") ; i++)
				{
					int compteur = 0;
					for(int g = 0; g < 9 ; g++)
					{
						if(i == noNum[g])
							compteur++;
					}
					if(compteur != 0)
						System.out.println("Nothing");
					else {
						RESERVATIONS r = new RESERVATIONS();
						r.setNum_reservation(-1);
						r.setNum_salle(i);
						r.setDate_res(rs.getString("DateR"));
						r.setCr1(false);
						r.setCr2(false);
						r.setCr3(false);
						r.setCr4(false);
						res.add(r);
					}

				} 
				save = i;
			}

			RESERVATIONS r1 = new RESERVATIONS();
			r1.setNum_reservation(rs.getInt("NumR"));
			r1.setNum_salle(rs.getInt("NumS"));
			r1.setDate_res(rs.getString("DateR"));
			r1.setCr1(rs.getBoolean("Cren1"));
			r1.setCr2(rs.getBoolean("Cren2"));
			r1.setCr3(rs.getBoolean("Cren3"));
			r1.setCr4(rs.getBoolean("Cren4"));
			res.add(r1);
			save++;
			if(save > 9)
				break;
			last = rs.getString("DateR");
			}

			int j;
			if(save < 9)
			{
				for( j = save ; j <= 9 ; j++)
				{
					int compteur1 = 0;
					for(int g = 0; g < 9 ; g++)
					{
						if(j == noNum[g])
							compteur1++;

					}

					if(compteur1 != 0)
						System.out.println("Nothing");
					else {
						RESERVATIONS r2 = new RESERVATIONS();
						r2.setNum_reservation(-1);
						r2.setNum_salle(j);
						r2.setDate_res(last);
						r2.setCr1(false);
						r2.setCr2(false);
						r2.setCr3(false);
						r2.setCr4(false);
						res.add(r2);
					}
				}

			}


			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}	
	public void setErreur( String champ, String message ) {
		Erreurs.put(champ, message);
	}

	public Map<String, String> getErreurs() {
		return Erreurs;
	}
	

}
