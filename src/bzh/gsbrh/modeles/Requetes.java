package bzh.gsbrh.modeles;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
//import java.util.Enumeration;
import java.util.Hashtable;

import bzh.gsbrh.modeles.ConnexionBDD;


public class Requetes {
	private static ResultSet resultat = null;
	private static Statement requete = null;
	/**
	 * Ajoute un employé en base de données
	 * 
	 * @param employe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int ajouterEmploye(Employe employe){

		int flag = 0;

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
		data = data + (employe.getServiceId()+1) + "'";


		try{
			//          Connexion à la base de données
			Connection conn = ConnexionBDD.getInstance();

			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  Déclaration de la requête
			String sql = "INSERT INTO visiteur(id, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche, service_id) VALUES ('"+ data +")";

			System.out.println(sql);

			flag = requete.executeUpdate(sql);

		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}

	public static Employe trouverEmploye(String id) {
		Employe employe = new Employe();

		try{
			Connection conn = ConnexionBDD.getInstance();
			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			resultat = requete.executeQuery("SELECT * FROM visiteur WHERE id = '" + id + "';");
			resultat.last();
			int row = resultat.getRow();
			resultat.first();
			if(row != 0){
				employe.setId(resultat.getString("id"));
				employe.setNom(resultat.getString("nom"));
				employe.setPrenom(resultat.getString("prenom"));
				employe.setLogin(resultat.getString("login"));
				employe.setMotDePasse(resultat.getString("mdp"));
				employe.setAdresse(resultat.getString("adresse"));
				employe.setCodePostal(resultat.getString("cp"));
				employe.setVille(resultat.getString("ville"));
				employe.setDateE(resultat.getString("dateEmbauche"));
				employe.setServiceId(resultat.getInt("service_id") - 1);
				employe.setDateD(resultat.getString("dateDepart"));
			}
			requete.close();
			return employe;

		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
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
	public static String trouverService(int service_id){

		String service = "";

		try{
			//      Connexion à la base de données
			Connection conn = ConnexionBDD.getInstance();
			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			resultat = requete.executeQuery("SELECT * FROM services WHERE service_id = " + service_id + ";");
			resultat.first();
			service = resultat.getString("service_libelle");

			requete.close();
			return service;

		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return service;
	}

	/**
	 * Méthode statique qui renvois le nom du service dont l'id est passé en paramètre
	 * 
	 * @param service_id
	 * @return le libellé du service demandé
	 */
	public static String trouverService(String serviceLib){
		//      Connexion à la base de données
		String service = null;

		try{
			Connection conn = ConnexionBDD.getInstance();
			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			resultat = requete.executeQuery("SELECT * FROM services WHERE service_libelle = '" + serviceLib + "';");
			resultat.first();
			service = resultat.getString("service_id");

			requete.close();
			return service;

		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return service;
	}


	public static String [] listerServices(){
		String [] liste = null;
		try{
			//          Connexion à la base de données

			//  Création d'un objet Statement
			requete = ConnexionBDD.getInstance().createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			resultat = requete.executeQuery("SELECT * FROM services ORDER BY service_id;");

			// Déclaration d'un hashtable qui accueillera la liste des employes au forma <id, Employe>
			Hashtable<Integer, String> listeServices = new Hashtable<Integer, String>();

			resultat.last();
			int row = resultat.getRow();
			resultat.first();

			liste = new String[row];
			
			for(int i = 0; i < row; i++){
				liste[i] = resultat.getString("service_libelle");
				resultat.next();
			}
			
		}catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
		} finally{
			close();
		}
		
		return liste;



	}

	public static int modifierEmploye(Employe employe) {

		int flag = 0;

		String data = "";
		data = data +"id = '" + employe.getId() + "',";
		data = data +"nom = '"+ employe.getNom() + "',";
		data = data +"prenom = '"+ employe.getPrenom() + "',";
		data = data +"login = '"+ employe.getLogin() + "',";
		data = data +"mdp = '"+ employe.getMotDePasse() + "',";
		data = data +"adresse = '"+ employe.getAdresse() + "',";
		data = data +"cp = '"+ employe.getCodePostal() + "',";
		data = data +"ville = '"+ employe.getVille() + "',";
		data = data +"dateEmbauche = '"+ Date.valueOf(employe.getDateE()) + "',";
		if(employe.getDateD().equals("")){
			data = data +"service_id = '"+ (employe.getServiceId()+1) + "'";
		}else{
			data = data +"service_id = '"+ (employe.getServiceId()+1) + "',"; 
			data = data +"dateDepart = '"+ Date.valueOf(employe.getDateD())+"'";
		}

		try{
			//          
			

			// Connexion à la base de données et création d'un objet Statement
			requete = ConnexionBDD.getInstance().createStatement();

			//  Déclaration de la requête
			String sql = "UPDATE visiteur SET "+ data +" WHERE id = '"+employe.getId()+"'";


			flag = requete.executeUpdate(sql);

		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;


	}

	/**
	 * Methode statique qui retourne tout les employes
	 * 
	 * @return HashTable <id, Employe> contient la liste de tout les employés
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static Object [][] listerEmployer() {
		Object [][]liste = null;

		try{
			//          Connexion à la base de données
			Connection conn = ConnexionBDD.getInstance();
			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			resultat = requete.executeQuery("SELECT * FROM visiteur ORDER BY nom;");

			resultat.last();
			int row = resultat.getRow();
			resultat.first();

			// Déclaration d'un employé qui va permettre l'ajout des employés
			Employe employe;
			liste = new Object[row][12];


			//  Boucle qui génère les employés un à un 
			for(int i = 0;i<row;i++){

				//  Ajout de l'employé à la liste des employés
				liste[i][0] =  resultat.getString("id");
				liste[i][1] =  resultat.getString("nom");
				liste[i][2] =  resultat.getString("prenom");
				liste[i][3] =  resultat.getString("login");
				liste[i][4] =  resultat.getString("adresse");
				liste[i][5] =  resultat.getString("cp");
				liste[i][6] =  resultat.getString("ville");
				liste[i][7] =  resultat.getString("dateEmbauche");
				liste[i][8] =  resultat.getString("service_id");
				if(resultat.getString("dateDepart") == null){
					liste[i][9] = "";
				}else{
					liste[i][9] =  resultat.getString("dateDepart");
				}
				resultat.next();
			}
			

		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return liste;

	}

	public static boolean verifierMdp(String loginAVerifier,String mdpAVerifier){
        boolean loginOk = false;

        String sql = null;


        try{
            requete = ConnexionBDD.getInstance().createStatement();
            sql = "SELECT login, mdp FROM visiteur WHERE login='"+ loginAVerifier +"'";
            resultat = requete.executeQuery(sql);
            if(resultat.first()){
                String aTester = resultat.getString("mdp");
                if(aTester.equals(mdpAVerifier))
                    loginOk = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            close();
        }

        return loginOk;
    }

	private static void close(){
		try {
			requete.close();
			if(resultat != null)
				resultat.close();
			ConnexionBDD.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		requete = null;
		resultat = null;
	}
}
