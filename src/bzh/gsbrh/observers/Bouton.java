package bzh.gsbrh.observers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public abstract class Bouton extends JButton implements Observable{

	private ArrayList tabObservateur;
	
	public Bouton(){
		tabObservateur = new ArrayList();
	}

	// Methode permettant d'ajouter/aboner un observateur
	public void ajouterObservateur(Observateur o){
		tabObservateur.add(o);
		System.out.println(o instanceof Fenetre);
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
			System.out.println("notifié");
		}
	}
	
}
