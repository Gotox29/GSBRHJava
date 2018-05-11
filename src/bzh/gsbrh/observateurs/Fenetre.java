package bzh.gsbrh.observateurs;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;

public abstract class Fenetre extends JFrame implements Observable{

	public Controleur cont;
	private ArrayList<Observateur> tabObservateur;
	protected SpringLayout layout = new SpringLayout();
	public final static String _TITRE_CONNE = "Connexion utilisateur";
	public final static String _TITRE_MODIF = "Modifier un employé";
	public final static String _TITRE_AJOUT = "Ajouter un employé";
	
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
	}
	public abstract void initComposant();
	public abstract void placementComposant(JComponent label, JComponent zone);

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
	public void finalize(){
		this.dispose();
	}

}
