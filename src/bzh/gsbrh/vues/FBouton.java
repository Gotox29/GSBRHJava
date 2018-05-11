package bzh.gsbrh.vues;

import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Observateur;

public class FBouton {
	final public static int _CONNECT = 0;
	final public static int _AJOUT = 1;
	final public static int _MODIF = 2;
	final public static int _REINIT = 3;
	final public static int _SUPPR = 4;
	final public static int _RETOUR = 5;
	
	public static Bouton FactoryBouton(Observateur o, int type){
		switch(type){
		case _CONNECT:
			return new BValider(o,Bouton._BOUTON_CONNE);
			
			case _AJOUT:
			return new BValider(o,Bouton._BOUTON_AJOUT);
			
			case _MODIF:
			return new BValider(o,Bouton._BOUTON_MODIF);
			
			case _REINIT:
			return new BReinitialiser(o,Bouton._BOUTON_REINIT);
			
			case _SUPPR:
			return new BReinitialiser(o,Bouton._BOUTON_SUPPR);
			
			case _RETOUR:
			return new BRetour(o,Bouton._BOUTON_RETOUR);
			
		}
		
		return null;
	}
}
