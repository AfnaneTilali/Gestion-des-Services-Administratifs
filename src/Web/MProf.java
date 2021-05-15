package Web;

import java.util.ArrayList;
import java.util.List;

import Entities.Module;
import Entities.Prof;

public class MProf {
	private Prof prof ;
	private List<Prof> listProf = new ArrayList<Prof>();

	
	public Prof getProf() {
		return prof;
	}
	public void setProf(Prof prof) {
		this.prof = prof;
	}
	public List<Prof> getListProf() {
		return listProf;
	}
	public void setListProf(List<Prof> listProf) {
		this.listProf = listProf;
	}
	@Override
	public String toString() {
		return "MProf [prof=" + prof + ", listProf=" + listProf + "]";
	}
	
	
}
