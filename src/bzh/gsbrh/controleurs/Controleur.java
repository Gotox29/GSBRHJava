package bzh.gsbrh.controleurs;

import javax.swing.JOptionPane;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Requetes;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.vues.FFenetre;
import bzh.gsbrh.vues.Formulaire;
import bzh.gsbrh.vues.Identification;

public class Controleur implements Observateur{
	private Fenetre fenetre;

	public Controleur(){
		//		Employe employe = new Employe();
		//		this.fenetre = FactoryFenetre.ouvrirFenetre(this, FactoryFenetre._AJOUT, employe);
		this.fenetre = FFenetre.ouvrirFenetre(this, FFenetre._IDENT, null);
		fenetre.setVisible(true);
	}

	@Override
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
		System.out.println("Controler");
		if(o instanceof Identification){
			this.fenetre.finalize();
			Employe employe = new Employe();
			this.fenetre = FFenetre.ouvrirFenetre(this, FFenetre._MODIF, employe);
			fenetre.setVisible(true);
		}
		if(o instanceof Formulaire){
			this.fenetre.finalize();
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
		case 0:
			if(verifId(id) && verifText(nom) && verifText(prenom) && verifText(login) && verifMdp(mdp) && verifText(adresse) && verifCP(cp) && verifText(ville)){
				employe.setDateD(null);
				Requetes.ajouterEmploye(employe);
				result = 0;
			}
			break;
		case 1:
			if(verifText(nom) && verifText(prenom) && verifText(login) && verifMdp(mdp) && verifText(adresse) && verifCP(cp) && verifText(ville)){
				employe.setDateD(null);
				if(Requetes.modifierEmploye(employe) == 1){
					result = 0;
				}
			}else{
				result = 2;
			}
			break;
		}
		return result;

	}


	private boolean verifId(String id) {
		boolean flag = false;
		String compar = Requetes.trouverEmploye(id).getId();
		if(!id.isEmpty() && id.length() <= 3 && (compar == null || compar.equals(""))){
			flag = true;
		}

		return flag;
	}

	private boolean verifText(String text){
		boolean flag = false;
		if(!text.isEmpty() && text.length() <= 30){
			flag = true;
		}

		return flag;
	}

	private boolean verifMdp(String mdp){
		boolean flag = false;
		if(!mdp.isEmpty() && mdp.length() <= 20){
			flag = true;
		}

		return flag;
	}

	private boolean verifLogin(String login){
		boolean flag = false;
		if(!login.isEmpty() && login.length() <= 20){
			flag = true;
		}

		return flag;
	}

	private boolean verifCP(String cp){
		boolean flag = false;
		if(!cp.isEmpty() && cp.length() == 5){
			flag = true;
		}

		return flag;
	}

}
