package Modeles;

import java.sql.Date;

public class Employe {

	private String id;

	private String nom;

	private String prenom;

	private String dateE = "2017-04-16";

	private String adresse;
	
	private String codePostal;
	
	private String ville;

	private String mail;

	private Integer telephone;

	private String login;

	private String motDePasse;

	private Integer serviceId = 0;

	private String dateD;

	public Employe() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = Integer.parseInt(serviceId);
	}

	public String getDateE() {
		return dateE;
	}

	public void setDateE(String date) {
		if(date.isEmpty())date = "";
		this.dateE = date;
	}

	public String getDateD() {
		return dateD;
	}

	public void setDateD(String date) {
		if (date == null) {
		    date = ""; // or whatever special case
		}
		this.dateD = date;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}


}
