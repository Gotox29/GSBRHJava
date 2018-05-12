package bzh.gsbrh.controleurs;

import javax.swing.JOptionPane;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Requetes;
import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.vues.FactFenetre;
import bzh.gsbrh.vues.FenFormulaire;
import bzh.gsbrh.vues.FenIdentification;
import bzh.gsbrh.vues.FenListeEmployes;

public class Controleur implements Observateur{
	private Fenetre fenetre;

	public Controleur(){
		//		Employe employe = new Employe();
		//		this.fenetre = FactoryFenetre.ouvrirFenetre(this, FactoryFenetre._AJOUT, employe);
		this.fenetre = FactFenetre.ouvrirFenetre(this, FactFenetre._LISTE, null);
		fenetre.setVisible(true);
	}

	@Override
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
		if(o instanceof Fenetre){
			if(fenetre.fermeture == 0){
				this.fenetre.finalize();
				System.exit(0);
			}else if(fenetre.fermeture == 1){
				fenetre.fermeture = -1;
			}else if(fenetre.fermeture == -1){
				if(o instanceof FenIdentification){
					this.fenetre.finalize();
					Employe employe = new Employe();
					this.fenetre = FactFenetre.ouvrirFenetre(this, FactFenetre._AJOUT, employe);
					fenetre.setVisible(true);
				}else if(o instanceof FenFormulaire){
					this.fenetre.finalize();
				}
			}
		}
	}
	public boolean verifierMdp(String login, String mdp) {
		return Requetes.verifierMdp(login,mdp);
	}

	public String[] listeService() {
		// TODO Auto-generated method stub
		return Requetes.listerServices();

	}

	public int validerEmploye(Employe employe, int option) {
		int result = 1;

		String id = employe.getId();
		String nom = employe.getNom();
		String prenom = employe.getPrenom();
		String login = employe.getLogin();
		String mdp = employe.getMotDePasse();
		String adresse = employe.getAdresse();
		String cp = employe.getCodePostal();
		String ville = employe.getVille();
		String date = employe.getDateE();
		int service = employe.getServiceId()+1;

		switch (option){
		case FenFormulaire._AJOUT:
			if(verifId(id) && verifText(nom) && verifText(prenom) && verifText(login) && verifMdp(mdp) && verifText(adresse) && verifCP(cp) && verifText(ville)){
				employe.setDateD(null);
				Requetes.ajouterEmploye(employe);
				result = FenFormulaire._MESSAGE;
			}
			break;
		case FenFormulaire._MODIF:
			if(verifText(nom) && verifText(prenom) && verifText(login) && verifMdp(mdp) && verifText(adresse) && verifCP(cp) && verifText(ville)){
				employe.setDateD(null);
				if(Requetes.modifierEmploye(employe) == 1){
					result = FenFormulaire._ERREUR_MO;
				}
			}else{
				result = FenFormulaire._ERREUR_CH;
			}
			break;
		}
		return result;

	}
	
	public boolean idExiste(String id) {
		boolean flag = false;
		if(Requetes.employeExiste(id, Requetes.ID)){
			flag = true;
		}
		return flag;
	}
	
	public boolean logExiste(String log) {
		boolean flag = false;
		if(Requetes.employeExiste(log, Requetes.LOG)){
			flag = true;
		}
		return flag;
	}
	
	public String formaterLog(String log){
		if(logExiste(log))
			log = formaterLog(log, 1);
		return log;
	}
	
	public String formaterLog(String log, int i){
		String temp = log + i;
		i+=1;
		if(logExiste(temp))
			temp = formaterLog(log, i);
		return temp;
	}
	
	public boolean verifId(String id) {
		boolean flag = false;
		if((id.length() > 0 && id.length() <= 4) && !idExiste(id)){
			flag = true;
		}
		return flag;
	}

	public boolean verifText(String text){
		boolean flag = false;
		if(!text.isEmpty() && text.length() <= 30){
			flag = true;
		}
		return flag;
	}

	private boolean verifMdp(String mdp){
		boolean flag = false;
		if(mdp.length() <= 20 && mdp.length() > 6){
			flag = true;
		}

		return flag;
	}

	public boolean verifLogin(String login){
		boolean flag = false;
		if((login.length() <= 20 && login.length() > 5) && !logExiste(login)){
			flag = true;
		}

		return flag;
	}

	private boolean verifCP(String cp){
		boolean flag = false;
		if(cp.length() >= 2 && cp.length() < 6){
			flag = true;
		}

		return flag;
	}

	@Override
	public void actualiser(Observable o, String valeur) {
		// TODO Auto-generated method stub
		if(o == fenetre){
			this.fenetre.finalize();
			Employe employe = new Employe();
			this.fenetre = FactFenetre.ouvrirFenetre(this, FactFenetre._AJOUT, employe);
			fenetre.setVisible(true);
		}
	}

	@Override
	public void actualiser(Observable o, String valeur, int code) {
		// TODO Auto-generated method stub
		if(o instanceof FenListeEmployes){
			switch(code){
			case 0:
				// Modification d'un employe
				this.fenetre.finalize();
				Employe employe = Requetes.trouverEmploye(valeur);
				this.fenetre = FactFenetre.ouvrirFenetre(this, FactFenetre._MODIF, employe);
				fenetre.setVisible(true);
				break;
			case 1:
				// Suppression d'un employe
				break;
			}
		}
	}

}
