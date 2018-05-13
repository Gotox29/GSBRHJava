package bzh.gsbrh.observateurs;

public class Lexique {
	
	/*
	 * Code des actions du controleur
	 */
	//	Code de lancement de la fenetre d'ajout
	final static public int LA_AJ = 1;
	
	/*
	 * Identifiant unique des bouton pour les comparer
	 * 
	 */
	//	ID bouton connexion
	final static public int ID_CO = 10;
	
	//	ID bouton ajout de liste
	final static public int LI_AJ = 11;
	
	//	ID bouton modifer de liste
	final static public int LI_MO = 12;
	
	//	ID bouton supprimer de liste
	final static public int LI_SU = 13;
	
	//	ID bouton retour du formulaire
	final static public int FO_BA = 14;
	
	//	ID bouton reinitialiser du formulaire
	final static public int FO_RE = 15;
	
	//	ID bouton ajouter du formulaire
	final static public int FO_AJ = 16;
	
	//	ID bouton modifier du formulaire
	final static public int FO_MO = 17;
	
	/*
	 * Identifiant des option de modif de la gestion des employé
	 */
	final public static int M_ID = 30;
	final public static int M_NOM = 31;
	final public static int M_PRENOM = 32;
	final public static int M_LOGIN = 33;
	final public static int M_MDP = 34;
	final public static int M_ADRESSE = 35;
	final public static int M_CP = 36;
	final public static int M_VILLE = 37;
	final public static int M_DATEE = 38;
	final public static int M_SERVICEID = 39;
	final public static int M_DATED = 40;
	
	/*
	 * Identifiant des type de la fabrique de bouton
	 * */
	//	Genere un bouton de connexion
	final public static int BO_CONNECT = 50;
	
	//	Generere un bouton d'ajout
	final public static int BO_AJOUT = 51;
	
	//	Genere un bouton de modification
	final public static int BO_MODIF = 52;
	
	//	Genere un bouton de reinitialisation
	final public static int BO_REINIT = 53;
	
	//	Genere un bouton de suppression
	final public static int BO_SUPPR = 54;
	
	//	Genere un bouton de retour
	final public static int BO_RETOUR = 55;
	
	/*
	 * Nom des boutons
	 */
	final public static String BOUTON_CONNE = "Se conncter";
	final public static String BOUTON_MODIF = "Modifier";
	final public static String BOUTON_SUPPR = "Supprimer";
	final public static String BOUTON_RETOUR = "Retour";
	final public static String BOUTON_REINIT = "Réinitialiser";
	final public static String BOUTON_AJOUT = "Ajouter";
	
	/*
	 * Titre des fenetres
	 */
	public final static String _TITRE_CONNE = "Connexion utilisateur";
	public final static String _TITRE_MODIF = "Modifier un employé";
	public final static String _TITRE_AJOUT = "Ajouter un employé";
	public final static String _TITRE_LISTE = "Liste des employés";
	
	/*
	 * Code de generation des fenetre
	 */
	final public static int FE_IDENT = 0;
	final public static int FE_LISTE = 1;
	final public static int FE_MODIF = 2;
	final public static int FE_AJOUT = 3;
	
	/*
	 * Code des elements de requete
	 */
	public static final int ID = 0;
	public static final int LOG = 1;
	
	/*
	 * Code des messages d'erreur du formulaire
	 */
	final public static int FO_MESSAGE = 0;
	final public static int FO_ERREUR_CH = 1;
	final public static int FO_ERREUR_MO = 2;
	
	/*
	 * Code iditifiant comparant les formulaires
	 */

	final public static int FO_AJOUT = 0;
	final public static int FO_MODIF = 1;
}
