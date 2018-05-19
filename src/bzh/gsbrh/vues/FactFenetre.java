package bzh.gsbrh.vues;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.GestionEmploye;
import bzh.gsbrh.observateurs.Fabrique;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;

public class FactFenetre extends Fabrique {
	
	public static Fenetre ouvrirFenetre(Observateur o, int code, Employe employe, Object[][] listeA, Object[][] listeI, String[]entete){
		switch(code){
		case FE_IDENT:
			return new FenIdentification(Lexique.FE_TITRE_CONNE, o);
		case FE_LISTE:
			return new FenListeEmployes(Lexique.FE_TITRE_LISTE, o, listeA, listeI, entete);
		case FE_MODIF:
			return FenFormulaire.creerFenetre(Lexique.FE_TITRE_MODIF, o, employe);
		case FE_AJOUT:
			return FenFormulaire.creerFenetre(Lexique.FE_TITRE_AJOUT, o, employe);
		default:
			return null;
		}
		
		
		
	}
}
