package bzh.gsbrh.observateurs;

import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

public abstract class ZoneText extends JTextField implements Observable, DocumentListener, FocusListener{
	

	private ArrayList<Observateur> tabObservateur;
	public String contenu;
	private LimitText document;
	public boolean etat = false;
	
	public ZoneText() {
		addFocusListener(this);
	}
	
	public ZoneText(Observateur o) {
		tabObservateur = new ArrayList<Observateur>();
		ajouterObservateur(o);		
		getDocument().addDocumentListener(this);
	}
	public ZoneText(Observateur o, String valeur) {
		super(valeur);
		tabObservateur = new ArrayList<Observateur>();
		ajouterObservateur(o);
		getDocument().addDocumentListener(this);
	}
	public ZoneText(Observateur o, String valeur, int colonne, boolean nombre) {
		super(valeur);
		tabObservateur = new ArrayList<Observateur>();
		ajouterObservateur(o);
		this.setDocument(this.document = new LimitText(colonne, nombre));
		getDocument().addDocumentListener(this);
		this.setText(valeur);
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
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		notifierObservateur();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		notifierObservateur();
	}

	@Override
	public void notifierObservateur(String valeur) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,valeur);
		}
	}
	
	@Override
	public void notifierObservateur(String valeur, int code) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,valeur,code);
		}
	}

	@Override
	public void notifierObservateur(int id) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,id);
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
	public void notifierObservateur(int code, Champ champs){}
	public void notifierObservateur(int code, Employe employe, Champ[] champs){}
	public void actualiser(Observable o, int code, Employe employe, Champ[] champs){}

}
