package bzh.gsbrh.observers;

import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JFrame;

import bzh.gsbrh.controleurs.Controleur;

public abstract class Fenetre extends JFrame implements Observateur, Observable{

	private Controleur cont;
	private ArrayList tabObservateur;

	public Fenetre(){

	}

	public Fenetre(Controleur controleur){
		tabObservateur = new ArrayList();
		
		
	}

	
	// Methode permettant d'ajouter/aboner un observateur
	public void ajouterObservateur(Observateur o){
		tabObservateur.add(o);
	}

	// Methode permettant de supprimer/resilier un observateur
	public void supprimerObservateur(Observateur o){
		tabObservateur.remove(o);
	}

	// Methode permettant d'avertir tous les observateur lors d'un changement d'etat
	public void notifierObservateur(){
		for(int i = 0; i<tabObservateur.size(); i++){
			Observateur o = (Observateur) tabObservateur.get(i);
			o.actualiser(this);
		}
	}

}
