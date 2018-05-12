package bzh.gsbrh.vues;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Fenetre;

public class FactFenetre  {

	final public static int _IDENT = 0;
	final public static int _LISTE = 1;
	final public static int _MODIF = 2;
	final public static int _AJOUT = 3;
	
	public static Fenetre ouvrirFenetre(Controleur controleur, int code, Employe employe){
		switch(code){
		case _IDENT:
			return new FenIdentification(Fenetre._TITRE_CONNE, controleur,controleur);
		case _LISTE:
			return new FenListeEmployes(Fenetre._TITRE_LISTE, controleur, controleur);
		case _MODIF:
			return new FenFormulaire(Fenetre._TITRE_MODIF, controleur, controleur, employe);
		case _AJOUT:
			return new FenFormulaire(Fenetre._TITRE_AJOUT, controleur, controleur, employe);
		default:
			return null;
		}
		
		
		
	}
}
