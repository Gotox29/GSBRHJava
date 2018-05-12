package bzh.gsbrh.observateurs;

import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public abstract class Panneau extends JPanel implements Observateur,Observable {

	private ArrayList<Observateur> tabObservateur;
	final public static int _TEXT = 0;
	final public static int _PASS = 1;
	final public static int _COMB = 2;

	public Panneau(SpringLayout layout){
		super(layout);
		tabObservateur = new ArrayList();
	}

	public Panneau(Observateur o,SpringLayout layout){
		super(layout);
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
	}

	abstract public void placementChamps(JComponent label, JComponent zone);

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
	
	@Override
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
		notifierObservateur();
	}


}
