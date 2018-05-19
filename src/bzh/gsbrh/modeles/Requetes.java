package bzh.gsbrh.modeles;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
//import java.util.Enumeration;
import java.util.Hashtable;

import bzh.gsbrh.modeles.ConnexionBDD;
import bzh.gsbrh.observateurs.Lexique;


/**
 * Class pour communiquer avec la base de données
 * 
 * @author Anthony Nizac
 * @version 2.0
 */
public class Requetes extends Lexique{

	private static ResultSet resultat = null;	//	resultat objet de type ResultSet contient les resultats des requetes
	
	private static Statement requete = null;	//	requete objet de type Statement permet d'éxécuter les requêtes sql
	
	/**
	 * Ajoute un employé en base de données
	 * 
	 * @param employe : à ajouter en base de données
	 * @return retourne 1 si tout c'est bien passé, 0 si erreur
	 */
	public static int ajouterEmploye(Employe employe){

		int flag = 0;

		String data = "";
		data = data + addSlashe(employe.getId()) + "','";
		data = data + addSlashe(employe.getNom()) + "','";
		data = data + addSlashe(employe.getPrenom()) + "','";
		data = data + addSlashe(employe.getLogin()) + "','";
		data = data + addSlashe(employe.getMotDePasse()) + "','";
		data = data + addSlashe(employe.getAdresse()) + "','";
		data = data + addSlashe(employe.getCodePostal()) + "','";
		data = data + addSlashe(employe.getVille()) + "','";
		data = data + addSlashe(employe.getMail()) + "','";
		data = data + addSlashe(employe.getTelephone()) + "','";
		data = data + Date.valueOf(employe.getDateE()) + "','";
		data = data + (employe.getServiceId()+1) + "'";


		try{
			//          Connexion à la base de données
			Connection conn = ConnexionBDD.getInstance();

			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  Déclaration de la requête
			String sql = "INSERT INTO visiteur(id, nom, prenom, login, mdp, adresse, cp, ville, mail, tel, dateEmbauche, service_id) VALUES ('"+ data +")";

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

	/**
	 * Trouve un employé en base de données
	 * 
	 * @param id : de l'employé
	 * @return l'employé trouvé en base, ou un employé vide
	 */
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
				employe.setMail(resultat.getString("mail"));
				employe.setTelephone(resultat.getString("tel"));
				employe.setDateE(resultat.getString("dateEmbauche"));
				employe.setServiceId(resultat.getInt("service_id") - 1);
				employe.setDateD(resultat.getString("dateDepart"));
			}
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

	/**
	 * Trouve un employé en base de données
	 * 
	 * @param valeur : correspond à la valeur du champs sur lequel on va effectuer la recherche
	 * @param option : correspond au champ sur lequel on effectue la recherche
	 * @return l'employé trouvé en base ou un employé vide
	 */
	public static Employe trouverEmploye(String valeur, int option) {
		Employe employe = new Employe();

		try{
			Connection conn = ConnexionBDD.getInstance();
			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			switch(option){
			case M_ID:
				resultat = requete.executeQuery("SELECT * FROM visiteur WHERE id = '" + valeur + "';");
				break;
			case M_LOGIN:
				resultat = requete.executeQuery("SELECT * FROM visiteur WHERE login = '" + valeur + "';");
				break;			
			}
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
				employe.setMail(resultat.getString("mail"));
				employe.setTelephone(resultat.getString("tel"));
				employe.setDateE(resultat.getString("dateEmbauche"));
				employe.setServiceId(resultat.getInt("service_id") - 1);
				employe.setDateD(resultat.getString("dateDepart"));
			}
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

	/**
	 * Verifie si un employé existe
	 * 
	 * @param compar : valeur à chercher
	 * @param option : correspond à la colonne de la table sur laquel chercher la valeur
	 * @return un booleen vrai si l'employé éxiste, false sinon
	 */
	public static boolean employeExiste(String compar, int option) {
		boolean flag = false;

		try{
			Connection conn = ConnexionBDD.getInstance();
			//  Création d'un objet Statement
			requete = conn.createStatement();
			switch(option){
			case M_ID:
				//  L'objet ResultSet contient le résultat de la requête SQL
				resultat = requete.executeQuery("SELECT * FROM visiteur WHERE id = '" + compar + "';");
				break;
			case M_LOGIN:
				//  L'objet ResultSet contient le résultat de la requête SQL
				resultat = requete.executeQuery("SELECT * FROM visiteur WHERE login = '" + compar + "';");
				break;
			}

			resultat.last();
			int row = resultat.getRow();
			if(row > 0)
				flag = true;
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
	 * Méthode statique qui renvois le nom du service dont l'id est passé en paramètre
	 * 
	 * @param service_id : correspond à l'id du service recherché
	 * @return le libellé du service demandé
	 */
	public static String trouverService(int service_id){

		String service = "";
		service_id = service_id + 1;
		try{
			//      Connexion à la base de données
			Connection conn = ConnexionBDD.getInstance();
			//  Création d'un objet Statement
			requete = conn.createStatement();

			//  L'objet ResultSet contient le résultat de la requête SQL
			resultat = requete.executeQuery("SELECT * FROM services WHERE service_id = " + service_id + ";");
			resultat.first();
			service = resultat.getString("service_libelle");
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
	 * cherche en base de données la liste des services
	 * 
	 * @return les libelles de liste des services
	 */
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

	/**
	 * Modifie un employé
	 * 
	 * @param employe : correspond au nouvelle valeur de l'employé
	 * @param codeModif : tableau des codes des éléments a modifier
	 * @return le nombre de resultat enregistré en base (1 ou 0)
	 */
	public static int modifierEmploye(Employe employe, int[] codeModif) {
		// TODO Auto-generated method stub
		int result = 0;
		String data = "";
		for(int i = 0; i < codeModif.length; i++){
			switch(codeModif[i]){
			case M_NOM:
				data = data +"nom = '"+ addSlashe(employe.getNom()) + "'";
				break;
			case M_PRENOM:
				data = data +"prenom = '"+ addSlashe(employe.getPrenom()) + "'";
				break;
			case M_LOGIN:
				data = data +"login = '"+ addSlashe(employe.getLogin()) + "'";
				break;
			case M_MDP:
				data = data +"mdp = '"+ addSlashe(employe.getMotDePasse()) + "'";
				break;
			case M_ADRESSE:
				data = data +"adresse = '"+ addSlashe(employe.getAdresse()) + "'";
				break;
			case M_CP:
				data = data +"cp = '"+ addSlashe(employe.getCodePostal()) + "'";
				break;
			case M_VILLE:
				data = data +"ville = '"+ addSlashe(employe.getVille()) + "'";
				break;
			case M_MAIL:
				data = data +"mail = '"+ addSlashe(employe.getMail()) + "'";
				break;
			case M_TEL:
				data = data +"tel = '"+ addSlashe(employe.getTelephone()) + "'";
				break;
			case M_DATEE:
				data = data +"dateEmbauche = '"+ Date.valueOf(employe.getDateE()) + "'";
				break;
			case M_SERVICEID:
				data = data +"service_id = '"+ (employe.getServiceId()+1) + "'";
				break;
			case M_DATED:
				if(employe.getDateD().equals(""))
					data = data +"dateDepart = null";
				else
					data = data +"dateDepart = '"+ Date.valueOf(employe.getDateD())+"'";
				break;
			default:
				break;
			}
			if(i != (codeModif.length-1))
				data = data + ",";
			System.out.println(data);
		}

		try{
			// Connexion à la base de données et création d'un objet Statement
			requete = ConnexionBDD.getInstance().createStatement();

			//  Déclaration de la requête
			String sql = "UPDATE visiteur SET "+ data +" WHERE id = '"+employe.getId()+"'";
			System.out.println(sql);

			result = requete.executeUpdate(sql);

		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return result;
	}

	/**
	 * Methode statique qui retourne tout les employes
	 * 
	 * @return ArrayList contient la liste de tout les employés
	 */
	public static ArrayList<Employe> listerEmployer() {
		ArrayList<Employe>liste = null;

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
			Employe unEmploye;
			liste = new ArrayList<Employe>();


			//  Boucle qui génère les employés un à un 
			for(int i = 0;i<row;i++){
				unEmploye = new Employe();
				//  Ajout de l'employé à la liste des employés
				unEmploye.setId(resultat.getString("id"));
				unEmploye.setNom(resultat.getString("nom"));
				unEmploye.setPrenom(resultat.getString("prenom"));
				unEmploye.setLogin(resultat.getString("login"));
				unEmploye.setMotDePasse(resultat.getString("mdp"));
				unEmploye.setAdresse(resultat.getString("Adresse"));
				unEmploye.setVille(resultat.getString("ville"));
				unEmploye.setMail(resultat.getString("mail"));
				unEmploye.setTelephone(resultat.getString("tel"));
				unEmploye.setCodePostal(resultat.getString("cp"));
				unEmploye.setDateE(resultat.getString("dateEmbauche"));
				unEmploye.setServiceId(resultat.getInt("service_id")-1);
				unEmploye.setDateD(resultat.getString("dateDepart"));
				resultat.next();
				liste.add(unEmploye);
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

	/**
	 * Verifie login et modt de passe en base
	 * 
	 * @param loginAVerifier : login saisie par l'utilisateur
	 * @param mdpAVerifier : mot de passe saisie par l'utilisateur
	 * @return un booleen qui confirme ou non la validité des information saisie
	 */
	public static boolean verifierMdp(String loginAVerifier,String mdpAVerifier){
		boolean loginOk = false;

		String sql = null;


		try{
			requete = ConnexionBDD.getInstance().createStatement();
			sql = "SELECT login, mdp FROM visiteur WHERE login='"+ addSlashe(loginAVerifier) +"'";
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

	/**
	 * Ferme les instance de connexion à la base de donnée 
	 */
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

	/**
	 * Sécurise les chaines de caractère
	 * 
	 * @param s : chaine de caractère à traité
	 * @return une chaine avec de caractère sécurisé pour être accépté en base de donnée 
	 */
	public static String addSlashe(String s){
		s = s.replaceAll("\\\\", "\\\\\\\\");
		s = s.replaceAll("\\n", "\\\\n");
		s = s.replaceAll("\\r", "\\\\r");
		s = s.replaceAll("\\00", "\\\\0");
		return s = s.replaceAll("'", "\\\\'");		
	}


}
