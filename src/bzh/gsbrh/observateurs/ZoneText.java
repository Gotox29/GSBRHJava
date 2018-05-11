package bzh.gsbrh.observateurs;

import java.util.ArrayList;
import javax.swing.JTextField;

public abstract class ZoneText extends JTextField implements Observable{
	

	private ArrayList<Observateur> tabObservateur;
	
	public ZoneText() {
		// TODO Auto-generated constructor stub
	}
	
	public ZoneText(Observateur o) {
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
	}
	public ZoneText(Observateur o, String valeur) {
		super(valeur);
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
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
