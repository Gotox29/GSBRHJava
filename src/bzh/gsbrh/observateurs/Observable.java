package bzh.gsbrh.observateurs;

import java.util.ArrayList;

public interface Observable {

	// Methode permettant d'ajouter un observateur
	public void ajouterObservateur(Observateur o);
	
	// Methode permettant de supprimer un observateur
	public void supprimerObservateur(Observateur o);
	
	// Methode permettant d'avertir tous les observateur d'un changement d'etat
	public void notifierObservateur();
	
	public void notifierObservateur(String valeur);
	
	public void notifierObservateur(String valeur, int code);
	
	
}
