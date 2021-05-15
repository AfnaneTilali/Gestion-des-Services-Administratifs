package Entities;

import java.io.InputStream;
import java.util.Arrays;

public class Etudiant {
	private String nom ;
	private String prenom ;
	private String cni ;
	private String cin ;
	private String adresse ;
	private String email ;
	private int phone;
	private String sexe ;
	private String filiere ;
	private int nbrAbs ;
	private String villeN ;
	private String dateN ;
	private String nomF ;
	private byte[] bytes ;
	private String cniP ;
	private String anneeUniv;
	private String country;
	private InputStream image;
	
	public InputStream getImage() {
		return image;
	}
	public void setImage(InputStream image) {
		this.image = image;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAnneeUniv() {
		return anneeUniv;
	}
	public void setAnneeUniv(String anneeUniv) {
		this.anneeUniv = anneeUniv;
	}
	public String getCniP() {
		return cniP;
	}
	public void setCniP(String cniP) {
		this.cniP = cniP;
	}
	public String getNomF() {
		return nomF;
	}
	public void setNomF(String nomF) {
		this.nomF = nomF;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCni() {
		return cni;
	}
	public void setCni(String cni) {
		this.cni = cni;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public int getNbrAbs() {
		return nbrAbs;
	}
	public void setNbrAbs(int nbrAbs) {
		this.nbrAbs = nbrAbs;
	}
	public String getVilleN() {
		return villeN;
	}
	public void setVilleN(String villeN) {
		this.villeN = villeN;
	}
	public String getDateN() {
		return dateN;
	}
	public void setDateN(String dateN) {
		this.dateN = dateN;
	}
	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", cni=" + cni + ", cin=" + cin + ", adresse=" + adresse
				+ ", email=" + email + ", phone=" + phone + ", sexe=" + sexe + ", filiere=" + filiere + ", nbrAbs="
				+ nbrAbs + ", villeN=" + villeN + ", dateN=" + dateN + ", nomF=" + nomF + ", bytes="
				+ Arrays.toString(bytes) + ", cniP=" + cniP + ", anneeUniv=" + anneeUniv + ", country=" + country
				+ ", image=" + image + "]";
	}

	
}
