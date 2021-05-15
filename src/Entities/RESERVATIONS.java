package Entities;

public class RESERVATIONS {
	
	  
	private int num_reservation;
	private int num_salle;
	private String date_res;
	private boolean cr1 = false;
	private boolean cr2 = false;
	private boolean cr3 = false;
	private boolean cr4 = false;
	public boolean isCr1() {
		return cr1;
	}
	public void setCr1(boolean cren1) {
		cr1 = cren1;
	}
	public boolean isCr2() {
		return cr2;
	}
	public void setCr2(boolean cren2) {
		cr2 = cren2;
	}
	public boolean isCr3() {
		return cr3;
	}
	public void setCr3(boolean cren3) {
		cr3 = cren3;
	}
	public boolean isCr4() {
		return cr4;
	}
	public void setCr4(boolean cren4) {
		cr4 = cren4;
	}
	public int getNum_reservation() {
		return num_reservation;
	}
	public void setNum_reservation(int num_Reservation) {
		num_reservation = num_Reservation;
	}
	public int getNum_salle() {
		return num_salle;
	}
	public void setNum_salle(int num_Salle) {
		num_salle = num_Salle;
	}
	public String getDate_res() {
		return date_res;
	}
	public void setDate_res(String date_Res) {
		date_res = date_Res;
	}
	
	public RESERVATIONS() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RESERVATIONS(int num_Salle,String date_Res, boolean cren1, boolean cren2, boolean cren3, boolean cren4) {
		super();
		num_salle = num_Salle;
		date_res = date_Res;
		cr1 = cren1;
		cr2 = cren2;
		cr3 = cren3;
		cr4 = cren4;
		
	}
	
		
}
