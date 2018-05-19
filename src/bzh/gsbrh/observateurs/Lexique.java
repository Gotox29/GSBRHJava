package bzh.gsbrh.observateurs;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * 
 * @author Anthony Nizac
 *
 */
public class Lexique {
	
	/*
	 * Couleurs de l'application
	 */
	final public static Color COLOR_BACKGROUNG = new Color(194, 224, 242);
	final public static Color COLOR_BOUTON_VALIDER = new Color(125, 145, 183);
	final public static Color COLOR_BOUTON_REINITIALISER = new Color(120, 120, 114);
	final public static Color COLOR_BOUTON_RETOUR = new Color(120, 118, 90);
	final public static Color COLOR_TEXT_VALIDE = new Color(100,200,10);
	final public static Color COLOR_TEXT_IVALIDE = new Color(255, 80, 80);
	final public static Color COLOR_TEXT_WHITE = Color.WHITE;
	final public static Color COLOR_TEXT_NULL = null;
	
	
	/*
	 * Images de l'application
	 */
	
	public static final String LOGO = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/indexA.png";
	public static final ImageIcon ICONLOGO = new ImageIcon(LOGO);
	public static final String LOGOL = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/index.png";
	public static final ImageIcon ICONLOGOL = new ImageIcon(LOGOL);
	public static final String RETOUR = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/retour.png";
	public static final ImageIcon ICONRETOUR = new ImageIcon(RETOUR);
	public static final String AJOUTER = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/ajouter.png";
	public static final ImageIcon ICONAJOUT = new ImageIcon(AJOUTER);
	public static final String DECONNECT = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/deconnexion.png";
	public static final ImageIcon ICONDECO = new ImageIcon(DECONNECT);
	public static final String ALEATOIR = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/alea.png";
	public static final ImageIcon ICONALEA = new ImageIcon(ALEATOIR);
	public static final String VALIDER = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/valider.png";
	public static final ImageIcon ICONVALI = new ImageIcon(VALIDER);
	public static final String REINIT = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/reinit.png";
	public static final ImageIcon ICONREIN = new ImageIcon(REINIT);
	public static final String CONNECT = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/connexion.png";
	public static final ImageIcon ICONCONNE = new ImageIcon(CONNECT);
	public static final String MODIFIE = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/modifier.png";
	public static final ImageIcon ICONMODIF = new ImageIcon(MODIFIE);
	public static final String PROGRAM = "C:/Users/Anthony/workspace/GSBRH/src/bzh.gsbrh.images/programme.png";
	public static final ImageIcon ICONPROG = new ImageIcon(PROGRAM);
	
	/**
	 * Texte de l'application
	 */

	/*
	 * Nom des boutons
	 */
	final public static String BOUTON_CONNE = "Se connecter";
	final public static String BOUTON_MODIF = "Modifier";
	final public static String BOUTON_SUPPR = "Programmer depart";
	final public static String BOUTON_RETOUR = "Retour";
	final public static String BOUTON_REINIT = "Réinitialiser";
	final public static String BOUTON_AJOUT = "Ajouter";
	final public static String BOUTON_VALIDER = "Valider";
	final public static String BOUTON_DEPROG = "Réintégrer";
	final public static String BOUTON_DECO = "Se deconnecter";
	final public static String BOUTON_GENRER = "Générer mot de passe";
	final public static String BOUTON_DATE = "Aujourd'hui";
	
	/*
	 * Titre des fenetres
	 */
	public static final String APPLI_TITRE = "GSB RH : ";
	public final static String FE_TITRE_CONNE = "Connexion utilisateur";
	public final static String FE_TITRE_MODIF = "Modifier un employé";
	public final static String FE_TITRE_AJOUT = "Ajouter un employé";
	public final static String FE_TITRE_LISTE = "Liste des employés";
	public final static String PP_TITRE_DATE = "Programmation d'une date";
	
	/*
	 * Titre des onglets
	 */
	public static final String ONGLET_1 = "Employés actifs";
	public static final String ONGLET_2 = "Anciens employés";
	
	/*
	 * Titre des messages
	 */
	final public static String M_TITRE_E = "Erreur !";
	final public static String M_TITRE_C = "Confirmation ?";
	final public static String M_TITRE_M = "Message.";
	
	/*
	 * Contenu des messages :
	 */
	final public static String M_ACCUEIL = "Connecté en tant que :";
	final public static String M_CONFIRM_S = "Confirmer la suppresssion de l'employé ?";
	final public static String M_CONFIRM_M = "Confirmer les modifications de l'employé ?";
	final public static String M_CONFIRM_F = "En quittant l'application, vous serez déconnecté.\n Continuer ?";
	final public static Object M_CONFIRM_MODIFDATE = "Confirmer la réintégration de l'employé ?";
	final public static String M_PROGRAM_DATED = "Programmer date de depart :";
	
	final public static String M_MESSAGE_A = "Employe ajouté avec succès.";
	final public static String M_MESSAGE_S = "Employe retiré avec succès.";
	final public static String M_MESSAGE_M = "Employe modifié avec succès.";
	final public static String M_MESSAGE_C = "Connecté avec succès.";
	
	final public static String M_ERREUR_CO = "Erreur de connexion.\nIdentifiant ou mot de passe invalide.";
	final public static Object M_ERREUR_SE = "Vous n'êtes pas autorisé a vous connecter ici.";
	final public static String M_ERREUR_MO = "Erreur dans la modification de l'employé.";
	final public static String M_ERREUR_AJ = "Erreur dans l'enregistrement de l'employé.";
	final public static String M_ERREUR_DA = "Date invalide !";
	
	public static final String M_ERREUR_ID = "Champ id invalide.";
	public static final String M_ERREUR_NOM = "Champ nom invalide.";
	public static final String M_ERREUR_PRE = "Champ prenom invalide.";
	public static final String M_ERREUR_LOG = "Champ identifiant invalide.";
	public static final String M_ERREUR_MDP = "Champ mot de passe invalide.";
	public static final String M_ERREUR_ADR = "Champ adresse invalide.";
	public static final String M_ERREUR_CP = "Champ code postal invalide.";
	public static final Object M_ERREUR_MAIL = "Champ mail invalide.";
	public static final Object M_ERREUR_TEL = "Champ telephone invalide.";
	public static final String M_ERREUR_VILLE = "Champ ville invalide.";
	public static final String M_ERREUR_DATE = "Date invalide.";
	
	public static final String ID = "Code";
	public static final String NOM = "Nom";
	public static final String PRENOM = "Prenom";
	public static final String LOGIN = "Identifiant";
	public static final String MDP = "Mot de passe";
	public static final String ADR = "Adresse";
	public static final String CP = "Code Postal";
	public static final String VILLE = "Ville";
	public static final String MAIL = "Mail";
	public static final String TEL = "Telephone";
	public static final String DATEE = "Date d'embauche";
	public static final String SERVICE = "Service";
	final public static String DATED = "Date depart";
	final public static String PP_DATE_TX = "Programmer date";
	
	
	/*
	 * Code des actions du controleur
	 */
	//	Code d'ajout de l'employé envoyé à la liste
	final static public int LI_VALIDE_AJ = 2;
	//	Code d'e modife de l'employé envoyé à la liste
	final static public int LI_VALIDE_MO = 3;
	
	/*
	 *	Code des type de champ
	 */
	final public static int CHAMP_TEXT = 0;
	final public static int CHAMP_PASS = 1;
	final public static int CHAMP_COMB = 2;
	final public static int CHAMP_ID = 3;
	final public static int CHAMP_DATE = 4;
	final public static int CHAMP_JR = 5;
	final public static int CHAMP_MO = 6;
	final public static int CHAMP_AN = 7;
	final public static int CHAMP_CP = 5;
	final public static int CHAMP_TEL = 8;
	
	/*
	 * Identifiant unique des bouton pour les comparer
	 * 
	 */
	//	ID du bouton de deconnexion
	final public static int AP_DECO = 666;
	
	//	ID bouton connexion
	final static public int ID_CO = 10;
	
	//	ID bouton ajout de liste
	final static public int LI_AJ = 11;
	
	//	ID bouton modifer de liste
	final static public int LI_MO = 12;
	
	//	ID bouton programmer de liste
	final static public int LI_PR = 13;
	
	//	ID bouton retour du formulaire
	final static public int FO_BA = 14;
	
	//	ID bouton reinitialiser du formulaire
	final static public int FO_RE = 15;
	
	//	ID bouton ajouter du formulaire
	final static public int FO_AJ = 16;
	
	//	ID bouton modifier du formulaire
	final static public int FO_MO = 17;
	
	//	ID bouton valider du PopUp date
	final static public int PP_DATE_VA = 18;
	
	//	ID bouton retour du PopUp date
	final static public int PP_DATE_BA = 19;

	//	ID bouton reinitialiser du PopUp date
	final static public int PP_DATE_RE = 20;
	
	/*
	 * Identifiant des option de modif de la gestion des employé
	 * Et des option de requetes
	 */
	final public static int M_ID = 0;
	final public static int M_NOM = 1;
	final public static int M_PRENOM = 2;
	final public static int M_LOGIN = 3;
	final public static int M_ADRESSE = 4;
	final public static int M_CP = 5;
	final public static int M_VILLE = 6;
	final public static int M_MAIL = 7;
	final public static int M_TEL = 8;
	final public static int M_DATEE = 9;
	final public static int M_SERVICEID = 10;
	final public static int M_DATED = 11;
	final public static int A_DATED = 12;
	final public static int M_MDP = 13;
	
	/*
	 * Identifiant des type de la fabrique de bouton
	 * */
//	Genere un bouton de deconnexion
	final public static int BO_DECONNECT = 999;
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
	
	//	Genere un bouton programmer
	public static final int BO_VALIDER = 56;
	
	//	Genere un bouton pour les date
	final public static int BO_DATE_NOW = 57;
	
	//	Genere un bouton pour generer mot de passe
	final public static int BO_GENE_PASS = 58;
	
	/*
	 * Code de generation des fenetre
	 */
	final public static int FE_IDENT = 0;
	final public static int FE_LISTE = 1;
	final public static int FE_MODIF = 2;
	final public static int FE_AJOUT = 3;
	final public static int FE_BACK = 4;

	/*
	 * Code iditifiant comparant les formulaires
	 */
	final public static int FO_AJOUT = 0;
	final public static int FO_MODIF = 1;

	
	/*
	 * Code de la fabrique de message
	 */
	/*
	 * Code des messages d'erreur du formulaire
	 */
	//final public static int FO_MESSAGE = 0;
	final public static int FO_ERREUR_CH = 1;
	final public static int FO_ERREUR_MO = 2;
	final public static int FO_MESSAGE_MO = 3;
	final public static int FO_MESSAGE_A = 4;
	final public static int FO_CONFIRM_M = 5;
	final public static int LI_ERREUR_MO = 6;
	final public static int FO_ERREUR_AJ = 7;
	final public static int LI_CONFIRM_MODIFDATE = 19;
	
	/*
	 * Code des message de connexion
	 */
	final public static int ID_ERREUR_CO = 20;
	final public static int ID_MESSAGE_CO = 21;
	
	/*
	 * Code des messages d'erreur de la liste des employé
	 */
	final public static int LI_MESSAGE_S = 30;
	final public static int LI_CONFIRM_S = 31;
	
	/*
	 * Code de message sur Fenetre 
	 */
	//	Code de confirmation de fermeture de l'appli
	final public static int AP_CONFIRM_Q = 40;
	//	Code de fermeture d'un formulaire
	final public static int FO_FERMETURE = 5;
	
	/*
	 * Codes de l'action de connexion
	 */
	//	lance le test sur login et mdp
	final public static int CONNEXION = 1;
	
	//	valide la connexion, lance la suite du programme
	final public static int ID_VALIDE_CO = 7;
	
	//	Code de l'action de generation de login
	final public static int GENERE_LOG = 2;

	//	Code de l'action de generation d'id
	final public static int FO_ATTRID = 10;
	
	//	Code de l'action d'attribution de liste à ComboBox des services
	final public static int FO_ATTRCOMB = 4;
	
//	Code de teste de coloration du login dans le formulaire
	final public static int COLOR_LOG = 1;
	final public static int COLOR_MDP = 2;
	final public static int COLOR_ID = 3;
	final public static int TESTCHAMP = 0;
	
	final public static int FO_ERREUR_ID = 10;
	final public static int FO_ERREUR_NOM = 11;
	final public static int FO_ERREUR_PRE = 12;
	final public static int FO_ERREUR_LOG = 13;
	final public static int FO_ERREUR_MDP = 14;
	final public static int FO_ERREUR_ADR = 15;
	final public static int FO_ERREUR_CP = 16;
	final public static int FO_ERREUR_VILLE = 17;
	final public static int FO_ERREUR_MAIL = 18;
	final public static int FO_ERREUR_TEL = 22;
	final public static int FO_ERREUR_DATE = 23;
	final public static int ID_ERREUR_SE = 1;
}
