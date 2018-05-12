package bzh.gsbrh.observateurs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.FactMessage;

public abstract class Fenetre extends JFrame implements Observable, WindowListener{

	public Controleur cont;
	private ArrayList<Observateur> tabObservateur;
	protected SpringLayout layout = new SpringLayout();
	public int fermeture = -1;
	public final static String _TITRE_CONNE = "Connexion utilisateur";
	public final static String _TITRE_MODIF = "Modifier un employé";
	public final static String _TITRE_AJOUT = "Ajouter un employé";
	public final static String _TITRE_LISTE = "Liste des employés";
	
	protected Container contentPane;
	protected String titre;

	public Controleur getControleur(){
		return this.cont;
	}

	public Fenetre(String titre, Controleur controleur, Observateur o){
		this.cont = controleur;
		tabObservateur = new ArrayList();	
		ajouterObservateur(o);
		setTitle("Interface : " + titre);
		this.titre = titre;
		addWindowListener(this);
		switch(titre){
		case _TITRE_CONNE:
			setSize(300,160);
			break;
		case _TITRE_MODIF:
		case _TITRE_AJOUT:
			setSize(500, 500);
			break;
		case _TITRE_LISTE:
			setSize(1300, 500);
			break;
		}
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public abstract void initComposant();
	public abstract void placementComposant(JComponent label, JComponent zone);
	public abstract void placementComposant();

	// Methode permettant d'ajouter/aboner un observateur
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
	public void finalize(){
		this.dispose();
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		fermeture = FactMessage.message(Fabrique._CONFIRM_F);
		notifierObservateur();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
