package Modeles;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Enumeration;
import java.util.Hashtable;

import Modeles.ConnexionBDD.ConnexionBdd;

public class Requetes {

	/**
	 * Ajoute un employé en base de données
	 * 
	 * @param employe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void ajouterEmploye(Employe employe) throws ClassNotFoundException, SQLException {

		String data = "";
		data = data + employe.getId() + "','";
		data = data + employe.getNom() + "','";
		data = data + employe.getPrenom() + "','";
		data = data + employe.getLogin() + "','";
		data = data + employe.getMotDePasse() + "','";
		data = data + employe.getAdresse() + "','";
		data = data + employe.getCodePostal() + "','";
		data = data + employe.getVille() + "','";
		data = data + Date.valueOf(employe.getDateE()) + "','";
		data = data + employe.getServiceId() + "'";


		try{
			//			Connexion à la base de données
			Connection conn = ConnexionBdd.getInstance();

			//  Création d'un objet Statement
			Statement requete = conn.createStatement();

			//	Déclaration de la requête
			String sql = "INSERT INTO visiteur(id, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche, service_id) VALUES ('"+ data +")";

			System.out.println(sql);

			requete.executeUpdate(sql);

		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static Employe trouverEmploye(String id) throws ClassNotFoundException {
		Employe employe = new Employe();

		try{
			Connection conn = ConnexionBdd.getInstance();
			//  Création d'un objet Statement
			Statement requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			ResultSet retourBdd = requete.executeQuery("SELECT * FROM visiteur WHERE id = '" + id + "';");
			retourBdd.last();
			int row = retourBdd.getRow();
			retourBdd.beforeFirst();
			retourBdd.first();
			if(row != 0){
				employe.setId(retourBdd.getString("id"));
				employe.setNom(retourBdd.getString("nom"));
				employe.setPrenom(retourBdd.getString("prenom"));
				employe.setLogin(retourBdd.getString("login"));
				employe.setMotDePasse(retourBdd.getString("mdp"));
				employe.setAdresse(retourBdd.getString("adresse"));
				employe.setCodePostal(retourBdd.getString("cp"));
				employe.setVille(retourBdd.getString("ville"));
				employe.setDateE(retourBdd.getString("dateEmbauche"));
				employe.setServiceId((retourBdd.getString("service_id")));
				employe.setDateD(retourBdd.getString("dateDepart"));
			}
			requete.close();
			return employe;

		}catch (SQLException e){
			e.printStackTrace();
		}
		return employe;
	}

	public Employe trouverEmploye(String nom, String prenom) {
		return null;
	}

	/**
	 * Méthode statique qui renvois le nom du service dont l'id est passé en paramètre
	 * 
	 * @param service_id
	 * @return le libellé du service demandé
	 */
	public static String trouverService(int service_id) throws SQLException, ClassNotFoundException{

		String service = "";

		try{
			//		Connexion à la base de données
			Connection conn = ConnexionBdd.getInstance();
			//  Création d'un objet Statement
			Statement requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			ResultSet retourBdd = requete.executeQuery("SELECT * FROM services WHERE service_id = " + service_id + ";");
			retourBdd.first();
			service = retourBdd.getString("service_libelle");

			requete.close();
			return service;

		}catch (SQLException e){
			e.printStackTrace();
		}
		return service;
	}

	/**
	 * Méthode statique qui renvois le nom du service dont l'id est passé en paramètre
	 * 
	 * @param service_id
	 * @return le libellé du service demandé
	 */
	public static String trouverService(String serviceLib) throws SQLException, ClassNotFoundException{
		//		Connexion à la base de données
		String service = null;

		try{
			Connection conn = ConnexionBdd.getInstance();
			//  Création d'un objet Statement
			Statement requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			ResultSet retourBdd = requete.executeQuery("SELECT * FROM services WHERE service_libelle = '" + serviceLib + "';");
			retourBdd.first();
			service = retourBdd.getString("service_id");

			requete.close();
			return service;

		}catch (SQLException e){
			e.printStackTrace();
		}
		return service;
	}


	public static Hashtable <Integer,String> listerServices() throws ClassNotFoundException {

		try{
			//			Connexion à la base de données
			Connection conn = ConnexionBdd.getInstance();
			//  Création d'un objet Statement
			Statement requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			ResultSet retourBdd = requete.executeQuery("SELECT * FROM services ORDER BY service_id;");

			// Déclaration d'un hashtable qui accueillera la liste des employes au forma <id, Employe>
			Hashtable<Integer, String> listeServices = new Hashtable<Integer, String>();

			retourBdd.last();
			int row = retourBdd.getRow();
			retourBdd.beforeFirst();

			//	Boucle qui génère les employés un à un
			while (retourBdd.next()) {

				//	Ajout de l'employé à la liste des employés
				listeServices.put(Integer.parseInt(retourBdd.getString("service_id")), retourBdd.getString("service_libelle"));

			}
			// Boucle de lecture des données
			//			String key;
			//			Enumeration keys = employes.keys();
			//			while (keys.hasMoreElements()){
			//				key = (String) keys.nextElement();
			//				System.out.println(key + " = " + employes.get(key).getNom() );
			//			}

			requete.close();
			return listeServices;

		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;



	}

	public void modifierEmploye(Employe employe) {
	}

	/**
	 * Methode statique qui retourne tout les employes
	 * 
	 * @return HashTable <id, Employe> contient la liste de tout les employés
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static Hashtable<String, Employe> listerEmployer() throws ParseException, SQLException, ClassNotFoundException {


		try{
			//			Connexion à la base de données
			Connection conn = ConnexionBdd.getInstance();
			//  Création d'un objet Statement
			Statement requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			ResultSet retourBdd = requete.executeQuery("SELECT * FROM visiteur ORDER BY nom;");

			// Déclaration d'un hashtable qui accueillera la liste des employes au forma <id, Employe>
			Hashtable<String, Employe> listeEmployes = new Hashtable<String, Employe>();

			retourBdd.last();
			System.out.println("aa"+retourBdd.getRow()+"aa");
			retourBdd.beforeFirst();

			// Déclaration d'un employé qui va permettre l'ajout des employés
			Employe employe;

			//	Boucle qui génère les employés un à un
			while (retourBdd.next()) {
				employe = new Employe();
				employe.setId(retourBdd.getString("id"));
				employe.setNom(retourBdd.getString("nom"));
				employe.setPrenom(retourBdd.getString("prenom"));
				employe.setLogin(retourBdd.getString("login"));
				employe.setMotDePasse(retourBdd.getString("mdp"));
				employe.setAdresse(retourBdd.getString("adresse"));
				employe.setCodePostal(retourBdd.getString("cp"));
				employe.setVille(retourBdd.getString("ville"));
				employe.setDateE(retourBdd.getString("dateEmbauche"));
				employe.setServiceId((retourBdd.getString("service_id")));
				employe.setDateD(retourBdd.getString("dateDepart"));

				//	Ajout de l'employé à la liste des employés
				listeEmployes.put(retourBdd.getString("id"),employe);

			}
			// Boucle de lecture des données
			//			String key;
			//			Enumeration keys = employes.keys();
			//			while (keys.hasMoreElements()){
			//				key = (String) keys.nextElement();
			//				System.out.println(key + " = " + employes.get(key).getNom() );
			//			}

			requete.close();
			return listeEmployes;

		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;

	}

	public Boolean virifierLogin(String login, String mdp) {
		return null;
	}
}
