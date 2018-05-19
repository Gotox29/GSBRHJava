package bzh.gsbrh.observateurs;

import java.util.ArrayList;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

public interface Observable{

	
	// Methode permettant d'ajouter un observateur
	public void ajouterObservateur(Observateur o);
	
	// Methode permettant de supprimer un observateur
	public void supprimerObservateur(Observateur o);
	
	// Methode permettant d'avertir tous les observateur d'un changement d'etat
	public void notifierObservateur();
	
	public void notifierObservateur(int id);
	
	public void notifierObservateur(String valeur);
	
	public void notifierObservateur(String valeur, int code);
	
	public void notifierObservateur(int code, Champ[] champs);
	
	public void notifierObservateur(int code, Champ champs);
	
	public void notifierObservateur(int code, Employe employe);

	public void notifierObservateur(int code, Employe employe, Champ[] champs);
}
