package Entities;

import java.sql.Date;
import java.util.Arrays;

public class Absence {
	private int idAbs ;
	private Date dateAbs ;
	//private String Time ;
	private boolean cren1;
	private boolean cren2;
	private boolean cren3;
	private boolean cren4;
	private String cniE ;
	private String nomM;
	private String cniPr;
	private String nomF ;
	private int numS;
	private byte[] bytes ;

	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public int getIdAbs() {
		return idAbs;
	}
	public void setIdAbs(int idAbs) {
		this.idAbs = idAbs;
	}
	public Date getDateAbs() {
		return dateAbs;
	}
	public void setDateAbs(Date dateAbs) {
		this.dateAbs = dateAbs;
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
	public String getCniE() {
		return cniE;
	}
	public void setCniE(String cniE) {
		this.cniE = cniE;
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
		return "Absence [idAbs=" + idAbs + ", dateAbs=" + dateAbs + ", cren1=" + cren1 + ", cren2=" + cren2 + ", cren3="
				+ cren3 + ", cren4=" + cren4 + ", cniE=" + cniE + ", nomM=" + nomM + ", cniPr=" + cniPr + ", nomF="
				+ nomF + ", numS=" + numS + ", bytes=" + Arrays.toString(bytes) + "]";
	}	
	
}
