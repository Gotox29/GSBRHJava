package bzh.gsbrh.observateurs;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

public interface Observateur {
	// Methode appelé automatiquement lorsque l'etat change
	public void actualiser(Observable o);

	public void actualiser(Observable o, int id);
	
	// Oservateur
	public void actualiser(Observable o, String valeur);
	
	public void actualiser(Observable o, String valeur, int code);
	
	public void actualiser(Observable o, int code, Champ[] champs);
	
	public void actualiser(Observable o, int code, Employe employe, Champ[] champs);
	
	public void actualiser(Observable o, int code, Champ champs);
	
	public void actualiser(Observable o, int code, Employe employe);
	
}
