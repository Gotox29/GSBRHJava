package bzh.gsbrh.controleurs;


import java.time.LocalDate;
import java.util.ArrayList;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.GenerateurMDP;
import bzh.gsbrh.modeles.GestionEmploye;
import bzh.gsbrh.modeles.Requetes;
import bzh.gsbrh.modeles.formDate;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.vues.Champ;
import bzh.gsbrh.vues.FactFenetre;
import bzh.gsbrh.vues.FactMessage;
import bzh.gsbrh.vues.PopUp;

/**
 * Controleur principale de l'application
 * 
 * Le controleur est le moteur principale de l'application,
 * il capte les notifications des éléments,
 * traite les données reçus en les passants aux différents modèle
 * et envois les ordres aux vues en fonctions des besoins.
 * 
 * @author Anthony Nizac
 * @version 2.0
 */
public class Controleur implements Observateur{

	private Fenetre fenetre;
	private Fenetre principale;
	private Fenetre connexion;
	private Employe unEmploye;
	private Employe lUtilisateur;
	GestionEmploye lesEmployes = new GestionEmploye(Requetes.listerEmployer(), Requetes.listerServices());

	/**
	 * Constructeur du controleur
	 * 
	 * à son instanciation le controleur lance l'application
	 */
	public Controleur(){
		
	}

	/**
	 * Methode qui lance la fenetre d'identification de l'utilisateur
	 * 
	 * Instancie en parallèle la liste des employés
	 * Elle est appelé au lancement de l'application mais aussi a la deconnexion de l'utilisateur.
	 */
	public void lancerAppli(){
		this.connexion = FactFenetre.ouvrirFenetre(this, Lexique.FE_IDENT, null, null, null, null);
		connexion.setVisible(true);
	}

	/**
	 * 
	 */
	private void appliListe(){
		unEmploye = new Employe();
		if(fenetre == null)
			this.fenetre = FactFenetre.ouvrirFenetre(this, Lexique.FE_AJOUT, unEmploye, null, null, null);
		else
			fenetre.setVisible(false);
		if(this.principale == null){
			this.principale = FactFenetre.ouvrirFenetre(this, Lexique.FE_LISTE, null, lesEmployes.getListe(), lesEmployes.getListeI(), lesEmployes.getEntete());
		}
		this.principale.setVisible(true);

	}

	private void appliAjout(){
		principale.setVisible(false);
		unEmploye = new Employe();
		this.fenetre.setForm(unEmploye, Lexique.FE_TITRE_AJOUT);
		fenetre.setVisible(true);
	}

	private void appliModif(String id){
		principale.setVisible(false);
		unEmploye = lesEmployes.getEmploye(id);
		this.fenetre.setForm(unEmploye, Lexique.FE_TITRE_MODIF);
		fenetre.setVisible(true);
	}

	private void fermetureFenetre(){
		if(principale.fermeture == 0){
			this.fenetre.finalize();
			this.principale.finalize();
			System.exit(0);
		} else {
			appliListe();
		}
	}

	public void actualiser(Observable o) {
		if(o instanceof Fenetre){
			fermetureFenetre();
		}
	}

	public void fermetureAppli(Fenetre f){
		if(f.getFermeture()){
			if(fenetre != null)
				this.fenetre.finalize();
			if(principale != null)
				this.principale.finalize();
			this.principale = null;
			this.connexion.finalize();
			this.connexion = null;
			unEmploye = null;
			lUtilisateur = null;
			System.exit(0);
		}
	}

	public void actualiser(Observable o, int id) {
		switch(id){
		case Lexique.AP_DECO:
			lUtilisateur = null;
			principale.finalize();
			principale = null;
			fenetre.finalize();
			fenetre = null;
			lancerAppli();
			break;
		case Lexique.LI_AJ:
			appliAjout();
			break;
		case Lexique.LI_VALIDE_AJ:
			fenetre.reinitialiser();
			break;
		case Lexique.AP_CONFIRM_Q:
			fermetureAppli((Fenetre) o);
			break;
		case Lexique.ID_VALIDE_CO:
			connexion.setVisible(false);
		case Lexique.FO_FERMETURE:
		case Lexique.FO_BA:
			appliListe();
			break;
		}
	}

	public void actualiser(Observable o, String valeur, int code) {
		switch(code){
		case Lexique.LI_MO:
			appliModif(valeur);
			break;
		case Lexique.LI_PR:
			// Programmer date depart d'un employe
			unEmploye = Requetes.trouverEmploye(valeur, Lexique.M_ID);
			PopUp pop = new PopUp(principale, Lexique.PP_TITRE_DATE, true, this, unEmploye);
			pop.lancePopUp();
			break;
		case Lexique.PP_DATE_RE:
			unEmploye.setDateD(null);
			if(((PopUp) o).valider()){
				unEmploye.setDateE(LocalDate.now().toString());
				int [] codeModif = {Lexique.M_DATEE, Lexique.M_DATED};
				Requetes.modifierEmploye(unEmploye, codeModif);
				lesEmployes.ajouterEmploye(unEmploye);
				principale.actualiserListeEmpActif(lesEmployes.getListe());
				principale.actualiserListeEmpInactif(lesEmployes.getListeI());
				((PopUp) o).setOk(true);
			}
			break;
		}
	}

	public boolean verifierMdp(String login, String mdp) {
		return Requetes.verifierMdp(login,mdp);
	}
	
	public void actualiser(Observable o, String valeur) {}

	public void actualiser(Observable o, int code, Champ[] champs){
		
		switch(code){
		case Lexique.CONNEXION:
			String login = null;
			String mdp = null;
			for(int i = 0; i < champs.length; i++){
				int type = champs[i].getType();
				switch (type){
				case Lexique.CHAMP_TEXT:
					login = champs[i].getText();
					break;
				case Lexique.CHAMP_PASS:
					mdp = champs[i].getText();
					break;
				default:
					break;
				}
			}
			if(verifierMdp(login, mdp)){
				connexion.validerLogin(true);
				connexion.validerMdp(true);
				lUtilisateur = Requetes.trouverEmploye(login,Lexique.M_LOGIN);
				if(lUtilisateur.getServiceId() == 2 && !formDate.dateDepasse(lUtilisateur.getDateD())){
					this.principale = FactFenetre.ouvrirFenetre(this, Lexique.FE_LISTE, null, lesEmployes.getListe(), lesEmployes.getListeI(), lesEmployes.getEntete());
					connexion.validerService(true);
					principale.setNomU(lUtilisateur.getPrenom() + " " + lUtilisateur.getNom());;
				}else
					lUtilisateur = null;
			}
			connexion.connexion();
			break;
		case Lexique.GENERE_LOG:
			String log = champs[1].getText().substring(0, 1).toLowerCase() + champs[0].getText().toLowerCase();
			if(!lesEmployes.verifLogin(log,unEmploye)){
				if(!log.equals(unEmploye.getLogin()))
					log = lesEmployes.formaterLog(log);
				champs[2].setText(log);
			}else{
				if(!log.equals(unEmploye.getLogin()))
					champs[2].setText(lesEmployes.formaterLog(log));
			}
			break;
		}		
	}

	public void actualiser(Observable o, int code, Employe employe){}

	public void actualiser(Observable o,int code, Champ champ){
		String id;
		switch(code){
		case Lexique.COLOR_LOG:
			String log = champ.getText();
			if(!lesEmployes.verifLogin(log, unEmploye)){
				champ.setCouleur(false);
			}else{
				champ.setCouleur(true);
			}
			break;
		case Lexique.COLOR_MDP:
			String mdp = champ.getText();
			if(!lesEmployes.verifMdp(mdp)){
				champ.setCouleur(false);
			}else{
				champ.setCouleur(true);
			}
			break;
		case Lexique.COLOR_ID:
			id = champ.getText();
			if(!lesEmployes.verifId(id, unEmploye)){
				champ.setCouleur(false);
			}else
				champ.setCouleur(true);
			break;
		case Lexique.M_DATED:
			String date = formDate.formatDate(champ.getText());
			if(lesEmployes.verifDate(date)){
				unEmploye.setDateD(date);
				int [] codeModif = {Lexique.M_DATED};
				Requetes.modifierEmploye(unEmploye, codeModif);
				lesEmployes.ajouterEmploye(unEmploye);
				principale.actualiserListeEmpActif(lesEmployes.getListe());
				principale.actualiserListeEmpInactif(lesEmployes.getListeI());
				((PopUp) o).setOk(true);
			}else
				((PopUp) o).erreur();
			break;
		case Lexique.FO_ATTRID:
			id = lesEmployes.formaterId("1");
			champ.setValeur(id);
			champ.initialiserSaisie();
			break;
		case Lexique.FO_ATTRCOMB:
			String [] services = Requetes.listerServices();
			for(int i = 0; i < services.length;i++){
				champ.setBox(services[i]);
			}
			champ.initialiserSaisie();
			break;
		case Lexique.BO_DATE_NOW:
			champ.setText(LocalDate.now().toString());
			break;
		case Lexique.BO_GENE_PASS:
			String pass = GenerateurMDP.generer(6, 12, false);
			System.out.println(pass);
			champ.setText(pass);
			break;
		}
	}

	public void actualiser(Observable o, int code, Employe employe, Champ[] champs) {
		switch(code){
		//	Modification d'un employé
		case Lexique.FO_MODIF:
			//	Ajout d'un employé
		case Lexique.FO_AJOUT:
			if(!lesEmployes.verifId(champs[0].getText(), unEmploye)){
				champs[0].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_ID,null);
			} else if(!lesEmployes.verifText(champs[1].getText())){
				champs[1].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_NOM,null);
			} else if(!lesEmployes.verifText(champs[2].getText())){
				champs[2].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_PRE,null);
			} else if(!lesEmployes.verifLogin(champs[3].getText(),unEmploye)){
				champs[3].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_LOG,null);
			} else if(!lesEmployes.verifMdp(champs[4].getText())){
				champs[4].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_MDP,null);
			} else if(!lesEmployes.verifText(champs[5].getText())){
				champs[5].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_ADR,null);
			} else if(!lesEmployes.verifCP(champs[6].getText())){
				champs[6].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_CP,null);
			} else if(!lesEmployes.verifText(champs[7].getText())){
				champs[7].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_VILLE,null);
			} else if(!lesEmployes.verifText(champs[8].getText())){
				champs[8].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_MAIL,null);
			} else if(!lesEmployes.verifTel(champs[9].getText())){
				champs[9].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_TEL,null);
			} else if(!lesEmployes.verifDate(champs[10].getText())){
				champs[10].setCouleur(false);
				fenetre.afficher(Lexique.FO_ERREUR_DATE,null);
			} else{
				switch(code){				
				//	Modification d'un employé
				case Lexique.FO_MODIF:
					employe.setDateD(formDate.formatDate(unEmploye.getDateD()));
					employe.setDateE(formDate.formatDate(employe.getDateE()));
					int [] codeModif = lesEmployes.comparerEmploye(unEmploye, employe);		
					if(codeModif.length > 0 && FactMessage.message(Lexique.FO_CONFIRM_M) == 0){
						if(Requetes.modifierEmploye(employe, codeModif) == 1){
							fenetre.afficher(Lexique.FO_MESSAGE_MO, employe);
							lesEmployes.ajouterEmploye(employe);
							principale.actualiserListeEmpActif(lesEmployes.getListe());
							principale.actualiserListeEmpInactif(lesEmployes.getListeI());
							unEmploye = employe;
						}else{
							fenetre.afficher(Lexique.FO_ERREUR_MO, employe);
						}
					}
					break;
					//	Ajout d'un employé
				case Lexique.FO_AJOUT:
					employe.setDateD(null);
					if(Requetes.ajouterEmploye(employe) == 1){
						lesEmployes.ajouterEmploye(employe);
						principale.actualiserListeEmpActif(lesEmployes.getListe());
						principale.actualiserListeEmpInactif(lesEmployes.getListeI());
						fenetre.afficher(Lexique.FO_MESSAGE_A, employe);

					}else{
						fenetre.afficher(Lexique.FO_ERREUR_AJ, employe);
					}
					break;
				}
			}	
			break;
		}
	}
}