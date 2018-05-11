package bzh.gsbrh.controleurs;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Requetes;
import bzh.gsbrh.vues.Fenetre;
import bzh.gsbrh.vues.Formulaire;
import bzh.gsbrh.vues.Login;
import bzh.gsbrh.vues.Message;

public class Controleur{

	private static String[] services;
	private Hashtable<Integer, Component> champs;
	private static Employe unEmploye;
	private Hashtable<Integer, Hashtable> champs2;
	private static Fenetre principale;

	public static String[] getServices(){
		return services;
	}

	public Controleur() {
		lancerAppli();
	}

	public void lancerAppli() {
		Login F = new Login(this);
		F.setVisible(true);
	}

	public void lancerListeEmploye() {
		this.services = Requetes.listerServices();
		Object liste [][] = Requetes.listerEmployer();
		principale = new Fenetre(this, "Lister les employés du", liste);
		principale.setVisible(true);
	}
	

	public void lancerModifierEmploye(String id) {
		unEmploye = Requetes.trouverEmploye(id);
		Formulaire F = new Formulaire(this, "Modifier un employé", unEmploye,true);
		masquerPrincipale();
		F.setVisible(true);
	}
	
	public void lancerAjoutEmploye() {
		unEmploye = new Employe();
		Formulaire F = new Formulaire(this, "Ajouter un employé", unEmploye,false);
		masquerPrincipale();
		F.setVisible(true);
	}


	public Hashtable <Integer, Hashtable> genererChamps(){
		Hashtable <Integer, Hashtable> champs = new Hashtable <Integer, Hashtable>();


		Employe employe = unEmploye;

		champs.put(1, ajouterALHashtable("Id :", employe.getId()));
		champs.put(2, ajouterALHashtable("Nom :", employe.getNom()));
		champs.put(3, ajouterALHashtable("Prenom :", employe.getPrenom()));
		champs.put(4, ajouterALHashtable("Login :", employe.getLogin()));
		champs.put(5, ajouterALHashtable("Mot de passe :", employe.getMotDePasse()));
		champs.put(6, ajouterALHashtable("Adresse :", employe.getAdresse()));
		champs.put(7, ajouterALHashtable("Code Postal :", employe.getCodePostal()));
		champs.put(8, ajouterALHashtable("Ville :", employe.getVille()));
		champs.put(9, ajouterALHashtable("Date d'embauche :", employe.getDateE()));
		champs.put(10, ajouterALHashtable("Service : ", Integer.toString(employe.getServiceId())));

		return champs;
	}
	public Hashtable <String, String> ajouterALHashtable(String text, String value){
		Hashtable <String, String> ligne = new Hashtable <String, String>();
		if(value == null){
			value = "";
		}
		ligne.put(text, value);
		return ligne;
	}

	public static void ajouter(Employe employe) {

		String id = employe.getId();

		String nom = employe.getNom();

		String prenom = employe.getPrenom();

		String login = employe.getLogin();

		String mdp = employe.getMotDePasse();

		String adresse = employe.getAdresse();

		String cp = employe.getCodePostal();

		String ville = employe.getVille();

		String date = employe.getDateE();

		String service = Integer.toString(employe.getServiceId()+1);
		
		

		if(verifId(id) && verifText(nom) && verifText(prenom) && verifText(login) && verifMdp(mdp) && verifText(adresse) && verifCP(cp) && verifText(ville)){
			employe.setDateD(null);
			Requetes.ajouterEmploye(employe);
		}else{
			JOptionPane.showMessageDialog(null, Langues.getMessage("erreur", 0), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static boolean modifier(Employe employe){
		
		boolean flag = false;
		
		String id = employe.getId();

		String nom = employe.getNom();

		String prenom = employe.getPrenom();

		String login = employe.getLogin();

		String mdp = employe.getMotDePasse();

		String adresse = employe.getAdresse();

		String cp = employe.getCodePostal();

		String ville = employe.getVille();

		String date = employe.getDateE();

		String service = Integer.toString(employe.getServiceId()+1);
		int valide = JOptionPane.showConfirmDialog(null, Langues.getMessage("confirme",0), "Confirmation", JOptionPane.YES_NO_OPTION);
		if(valide == 0 && verifText(nom) && verifText(prenom) && verifText(login) && verifMdp(mdp) && verifText(adresse) && verifCP(cp) && verifText(ville)){
			employe.setDateD(null);
			if(Requetes.modifierEmploye(employe) == 1){
				JOptionPane.showMessageDialog(null, Langues.getMessage("message", 0), "Message", JOptionPane.INFORMATION_MESSAGE);
				flag = true;
			}else{
				JOptionPane.showMessageDialog(null, Langues.getMessage("erreur", 1), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, Langues.getMessage("erreur", 0), "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
		
		return flag;
		
	}

	public void masquerPrincipale(){
		principale.setVisible(false);
	}
	
	public void afficherPrincipale(){
		Object liste [][] = Requetes.listerEmployer();
		principale.reinitFenetre(liste);
		principale.setVisible(true);
//		principale.dispose();
//		lancerListeEmploye();
	}



	public void lancerSupprimerEmploye(String id) {
		Employe employe = Requetes.trouverEmploye(id);
		int valide = JOptionPane.showConfirmDialog(null, Langues.getMessage("confirme", 1), "Confirmation", JOptionPane.YES_NO_OPTION);
		if(valide == 0){
			String aujourdui = ""+LocalDate.now();
			employe.setDateD(aujourdui);
			if(Requetes.modifierEmploye(employe) == 1){
				JOptionPane.showMessageDialog(null, Langues.getMessage("message",1), "Message", JOptionPane.INFORMATION_MESSAGE);
				principale.dispose();
				lancerListeEmploye();
			}
		}
		
	}
	public boolean verifierMdp(String login, String mdp){
        return Requetes.verifierMdp(login,mdp);
    }

	private static boolean verifId(String id) {
		boolean flag = false;
		String compar = Requetes.trouverEmploye(id).getId();
		if(!id.isEmpty() && id.length() <= 3 && (compar == null || compar.equals(""))){
			flag = true;
		}
		
		return flag;
	}

	private static boolean verifText(String text){
		boolean flag = false;
		if(!text.isEmpty() && text.length() <= 30){
			flag = true;
		}
		
		return flag;
	}

	private static boolean verifMdp(String mdp){
		boolean flag = false;
		if(!mdp.isEmpty() && mdp.length() <= 20){
			flag = true;
		}
		
		return flag;
	}

	private static boolean verifLogin(String login){
		boolean flag = false;
		if(!login.isEmpty() && login.length() <= 20){
			flag = true;
		}
		
		return flag;
	}

	private static boolean verifCP(String cp){
		boolean flag = false;
		if(!cp.isEmpty() && cp.length() == 5){
			flag = true;
		}
		
		return flag;
	}


	


}
