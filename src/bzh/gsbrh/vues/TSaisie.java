package bzh.gsbrh.vues;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.ZoneText;

public class TSaisie extends ZoneText{

	
	public TSaisie() {
		// TODO Auto-generated constructor stub
		super();
		
	}
	
	public TSaisie(Observateur o) {
		// TODO Auto-generated constructor stub
		super(o);
		
	}

	public TSaisie(Observateur o, String valeur) {
		super(o,valeur);
		
		// TODO Auto-generated constructor stub
	}
	public TSaisie(Observateur o, String valeur, int colonne) {
		super(o,valeur,colonne);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		notifierObservateur();
	}

	
}
