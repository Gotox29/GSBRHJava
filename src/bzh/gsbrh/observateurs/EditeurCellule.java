package bzh.gsbrh.observateurs;

import java.util.ArrayList;


import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import bzh.gsbrh.observateurs.Lexique;

public abstract class EditeurCellule extends DefaultCellEditor implements Observable{

	private ArrayList<Observateur> tabObservateur;
	public EditeurCellule(JTextField textField) {
		super(textField);
		tabObservateur = new ArrayList();
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
}
