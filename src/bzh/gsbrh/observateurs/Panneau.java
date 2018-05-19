package bzh.gsbrh.observateurs;

import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

public abstract class Panneau extends JPanel implements Observateur,Observable {

	private ArrayList<Observateur> tabObservateur;
	public JScrollPane getScrollpane(){
		return null;
	}

	public Panneau(SpringLayout layout){
		super(layout);
		tabObservateur = new ArrayList<Observateur>();
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
	
	public void actualiser(Observable o, String valeur){
		notifierObservateur(valeur);
	}
	
	@Override
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
		notifierObservateur();
	}
	
	public void notifierObservateur(String valeur){
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this, valeur);
		}
	}
	
	@Override
	public void actualiser(Observable o, String valeur, int code) {
		// TODO Auto-generated method stub
		notifierObservateur(valeur, code);
	}

	@Override
	public void notifierObservateur(String valeur, int code) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this, valeur, code);
		}
		
	}
	@Override
	public void actualiser(Observable o, int id) {
		// TODO Auto-generated method stub
		notifierObservateur(id);
	}

	@Override
	public void notifierObservateur(int id) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this, id);
		}
	}
	public void notifierObservateur(int code, Champ[] champs) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,code, champs);
		}
	}
	@Override
	public void notifierObservateur(int code, Employe employe) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,code,employe);
		}
	}
	
	public void actualiser(Observable o, int code, Champ[] champs){
		notifierObservateur(code,champs);
	}

	public void actualiser(Observable o, int code, Employe employe){
		notifierObservateur(code,employe);
	}
	
	public void notifierObservateur(int code, Champ champs){}
	public void actualiser(Observable o, int code, Champ champs){}
	public void notifierObservateur(int code, Employe employe, Champ[] champs){}
	public void actualiser(Observable o, int code, Employe employe, Champ[] champs){}

}
