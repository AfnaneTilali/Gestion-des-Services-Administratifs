package Web;

import java.util.ArrayList;
import java.util.List;

import Entities.Parent;

public class MParent {
	private Parent parent ;
	private List<Parent> listParent = new ArrayList<Parent>();
	
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public List<Parent> getListParent() {
		return listParent;
	}
	public void setListParent(List<Parent> listParent) {
		this.listParent = listParent;
	}
	@Override
	public String toString() {
		return "MParent [Parent=" + parent + ", listParent=" + listParent + "]";
	}
	
}
