package bzh.gsbrh.vues;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Fabrique;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;

public class FactFenetre extends Fabrique {
	
	public static Fenetre ouvrirFenetre(Controleur controleur, int code, Employe employe){
		switch(code){
		case FE_IDENT:
			return new FenIdentification(Lexique._TITRE_CONNE, controleur,controleur);
		case FE_LISTE:
			return new FenListeEmployes(Lexique._TITRE_LISTE, controleur, controleur);
		case FE_MODIF:
			return new FenFormulaire(Lexique._TITRE_MODIF, controleur, controleur, employe);
		case FE_AJOUT:
			return new FenFormulaire(Lexique._TITRE_AJOUT, controleur, controleur, employe);
		default:
			return null;
		}
		
		
		
	}
}
