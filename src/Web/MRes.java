package Web;

import java.util.ArrayList;
import java.util.HashMap;

import Metier.IRes;
import Entities.RESERVATIONS;
import java.util.List;
import java.util.Map;

//import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class MRes {
	private IRes metier;
	
	private String dateRes;
	private RESERVATIONS rs = new RESERVATIONS();
	//private RESERVATIONS err = metier.getReservation(Num_Res);
	private Map<String, String> Erreurs = new HashMap<String, String>();
    private String errors; 
	private List<RESERVATIONS> reservations = new ArrayList<RESERVATIONS>();

	public int err(int n) {
		RESERVATIONS res = metier.getReservation(n);
		return metier.makeReservation(res);
	}
	
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public RESERVATIONS getRs() {
		return rs;
	}
	public void setRs(RESERVATIONS rs) {
		this.rs = rs;
	}
	
	public String getDateRes() {
		return dateRes;
	}
	public void setDateRes(String dateRes) {
		this.dateRes = dateRes;
	}
	public List<RESERVATIONS> getReservations() {
		return reservations;
	}
	public void setReservations(List<RESERVATIONS> listeRes) {
		reservations = listeRes;
	}

	public IRes getMetier() {
		return metier;
	}

	public void setMetier(IRes metier) {
		this.metier = metier;
	}
	public void setErreur( String champ, String message ) {
		Erreurs.put(champ, message);
	}

	public Map<String, String> getErreurs() {
		return Erreurs;
	}
	

	@Override
	public String toString() {
		return "MRes [metier=" + metier + ", dateRes=" + dateRes + ", rs=" + rs + ", Erreurs=" + Erreurs + ", errors="
				+ errors + ", reservations=" + reservations + "]";
	}


}
