package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

import bzh.gsbrh.observateurs.LimitText;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.ZoneText;

public class TSaisie extends ZoneText{

	private LimitText document;
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
	public TSaisie(Observateur o, String valeur, int colonne, boolean nombre) {
		super(o,valeur, colonne, nombre);
		// TODO Auto-generated constructor stub
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

	public void restitu(){
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(etat)
			restitu();
		System.out.println("Focus");
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


}
