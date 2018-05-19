package bzh.gsbrh.observateurs;

import java.util.ArrayList;
import javax.swing.JButton;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

public abstract class Bouton extends JButton implements Observable{

	private ArrayList<Observateur> tabObservateur;
	protected int id = -1;

	public Bouton(){

	}

	public Bouton(Observateur o, String titre, int id){
		tabObservateur = new ArrayList<Observateur>();
		ajouterObservateur(o);
		this.setText(titre);
		this.id = id;
	}

	public void setId(int id){
		this.id = id;
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
	@Override
	public void notifierObservateur(int id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this, id);
		}
	}

	public void notifierObservateur(String valeur){
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this, valeur);
		}
	}

	@Override
	public void notifierObservateur(String valeur, int code) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this, valeur, code);
		}
	}
	public void notifierObservateur(int code, Champ[] champs) {}
	public void notifierObservateur(int code, Employe employe) {}
	public void notifierObservateur(int code, Employe employe, Champ[] champs){}
	public void notifierObservateur(int code, Champ champs){}

}
