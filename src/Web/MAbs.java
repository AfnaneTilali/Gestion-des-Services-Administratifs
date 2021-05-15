package Web;

import java.util.ArrayList;
import java.util.List;

import Entities.*;

public class MAbs {
	
	private Absence abs ;
	private List<Absence> listAbs = new ArrayList<Absence>();
	private List<AbsSupp> listAbsSupp = new ArrayList<AbsSupp>();

	public List<AbsSupp> getListAbsSupp() {
		return listAbsSupp;
	}
	public void setListAbsSupp(List<AbsSupp> listAbsSupp) {
		this.listAbsSupp = listAbsSupp;
	}
	public Absence getAbs() {
		return abs;
	}
	public void setAbs(Absence abs) {
		this.abs = abs;
	}
	public List<Absence> getListAbs() {
		return listAbs;
	}
	public void setListAbs(List<Absence> listAbs) {
		this.listAbs = listAbs;
	}
	@Override
	public String toString() {
		return "MAbs [abs=" + abs + ", listAbs=" + listAbs + ", listAbsSupp=" + listAbsSupp + "]";
	}
	
	
}
