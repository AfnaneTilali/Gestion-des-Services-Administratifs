package Entities;

public class Element {
	private int idEl ;
	private String nomEl ;
	
	public int getIdEl() {
		return idEl;
	}
	public void setIdEl(int idEl) {
		this.idEl = idEl;
	}
	public String getNomEl() {
		return nomEl;
	}
	public void setNomEl(String nomEl) {
		this.nomEl = nomEl;
	}
	@Override
	public String toString() {
		return "Element [idEl=" + idEl + ", nomEl=" + nomEl + "]";
	}
	
}
