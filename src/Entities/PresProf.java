package Entities;

import java.sql.Date;

public class PresProf {
	private int idPresProf ;
	private Date datePres ;
	private boolean cren1;
	private boolean cren2;
	private boolean cren3;
	private boolean cren4;
	private String nomM;
	private String cniPr;
	private String nomF ;
	private int numS;
	public int getIdPresProf() {
		return idPresProf;
	}
	public void setIdPresProf(int idPresProf) {
		this.idPresProf = idPresProf;
	}
	public Date getDatePres() {
		return datePres;
	}
	public void setDatePres(Date datePres) {
		this.datePres = datePres;
	}
	public boolean isCren1() {
		return cren1;
	}
	public void setCren1(boolean cren1) {
		this.cren1 = cren1;
	}
	public boolean isCren2() {
		return cren2;
	}
	public void setCren2(boolean cren2) {
		this.cren2 = cren2;
	}
	public boolean isCren3() {
		return cren3;
	}
	public void setCren3(boolean cren3) {
		this.cren3 = cren3;
	}
	public boolean isCren4() {
		return cren4;
	}
	public void setCren4(boolean cren4) {
		this.cren4 = cren4;
	}
	public String getNomM() {
		return nomM;
	}
	public void setNomM(String nomM) {
		this.nomM = nomM;
	}
	public String getCniPr() {
		return cniPr;
	}
	public void setCniPr(String cniPr) {
		this.cniPr = cniPr;
	}
	public String getNomF() {
		return nomF;
	}
	public void setNomF(String nomF) {
		this.nomF = nomF;
	}
	public int getNumS() {
		return numS;
	}
	public void setNumS(int numS) {
		this.numS = numS;
	}
	@Override
	public String toString() {
		return "PresProf [idPresProf=" + idPresProf + ", datePres=" + datePres + ", cren1=" + cren1 + ", cren2=" + cren2
				+ ", cren3=" + cren3 + ", cren4=" + cren4 + ", nomM=" + nomM + ", cniPr=" + cniPr + ", nomF=" + nomF
				+ ", numS=" + numS + "]";
	}
	
	
}
