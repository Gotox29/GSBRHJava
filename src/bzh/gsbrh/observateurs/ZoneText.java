package bzh.gsbrh.observateurs;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public abstract class ZoneText extends JTextField implements Observable, DocumentListener{
	

	private ArrayList<Observateur> tabObservateur;
	public String contenu;
	
	public ZoneText() {
		// TODO Auto-generated constructor stub
	}
	
	public ZoneText(Observateur o) {
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
		getDocument().addDocumentListener(this);
	}
	public ZoneText(Observateur o, String valeur) {
		super(valeur);
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
		getDocument().addDocumentListener(this);
	}
	public ZoneText(Observateur o, String valeur, int colonne) {
		super(valeur,colonne);
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
		getDocument().addDocumentListener(this);
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
	

}
