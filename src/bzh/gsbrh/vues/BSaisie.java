package bzh.gsbrh.vues;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.ZoneText;

public class BSaisie extends ZoneText implements KeyListener{

	public BSaisie() {
		// TODO Auto-generated constructor stub
		super();
		addKeyListener(this);
	}
	
	public BSaisie(Observateur o) {
		// TODO Auto-generated constructor stub
		super(o);
		addKeyListener(this);
	}

	public BSaisie(Observateur o, String valeur) {
		super(o,valeur);
		addKeyListener(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		notifierObservateur();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
