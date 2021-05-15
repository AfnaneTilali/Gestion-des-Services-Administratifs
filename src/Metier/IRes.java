package Metier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.RESERVATIONS;

public interface IRes {
	Map<String, String> Erreurs = new HashMap<String, String>();

	public int makeReservation(RESERVATIONS r);
	public void deleteReservation(int Num_Reservation, int cr);
	public void modifierReservation(RESERVATIONS r);
	public List<RESERVATIONS> reservationList();
	public RESERVATIONS getReservation(int Num_Res);
	public List<RESERVATIONS> reservationParDate(String date);
	public int getNumRes(String date , int numSalle) ;
	public List<RESERVATIONS> reservationParDateSalles(String date, int cr);
	
	public void setErreur( String champ, String message );
	public Map<String, String> getErreurs();
}
