package bzh.gsbrh.observateurs;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public abstract class Bouton extends JButton implements Observable{

	private ArrayList<Observateur> tabObservateur;
	final public static String _BOUTON_CONNE = "Se conncter";
	final public static String _BOUTON_MODIF = "Modifier";
	final public static String _BOUTON_SUPPR = "Supprimer";
	final public static String _BOUTON_RETOUR = "Retour";
	final public static String _BOUTON_REINIT = "Réinitialiser";
	final public static String _BOUTON_AJOUT = "Ajouter";
	
	
	public Bouton(Observateur o, String titre){
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
		this.setText(titre);
	}
		
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
			tabObservateur.get(i).actualiser(this);
		}
	}
	
	
}
