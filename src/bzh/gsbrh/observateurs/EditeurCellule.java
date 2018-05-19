package bzh.gsbrh.observateurs;

import java.util.ArrayList;


import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.vues.Champ;

public abstract class EditeurCellule extends DefaultCellEditor implements Observable{

	private ArrayList<Observateur> tabObservateur;
	public EditeurCellule(JTextField textField) {
		super(textField);
		tabObservateur = new ArrayList<Observateur>();
		// TODO Auto-generated constructor stub
	}

	public EditeurCellule(JCheckBox checkBox, Observateur o) {
		super(checkBox);
		// TODO Auto-generated constructor stub
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
	}

	public EditeurCellule(JComboBox comboBox) {
		super(comboBox);
		// TODO Auto-generated constructor stub
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
	// Methode permettant d'avertir tous les observateur lors d'un changement d'etat
	public void notifierObservateur(int id){
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
	public void notifierObservateur(int code,Champ champs){}
	public void notifierObservateur(int code, Employe employe, Champ[] champs){}
	public void actualiser(Observable o, int code, Employe employe, Champ[] champs){}
}
