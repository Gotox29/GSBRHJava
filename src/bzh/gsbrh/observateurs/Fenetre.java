package bzh.gsbrh.observateurs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;
import bzh.gsbrh.vues.FactMessage;
import bzh.gsbrh.vues.PopUp;
import bzh.gsbrh.observateurs.Lexique;

public abstract class Fenetre extends JFrame implements Observateur, Observable, WindowListener{

	private ArrayList<Observateur> tabObservateur;
	protected SpringLayout layout = new SpringLayout();
	public int fermeture = 0;

	protected Container contentPane;
	protected String titre;
	protected JLabel image = new JLabel(Lexique.ICONLOGO);
	protected JLabel imageL = new JLabel(Lexique.ICONLOGOL);
	protected JLabel nomU;
	
	public void setNomU(String nom){
		nomU.setText(nom);
	}
	
	public boolean getFermeture(){
		if(fermeture == 0){
			return true;
		}
		return false;
	}
	
	public void actualiser(Employe employe){}
	
	public void afficher(int code, Employe employe){}

	public Fenetre(String titre, Observateur o){
		tabObservateur = new ArrayList<Observateur>();	
		ajouterObservateur(o);
		setTitle(Lexique.APPLI_TITRE + titre);
		this.titre = titre;
		addWindowListener(this);
		Image icone = Toolkit.getDefaultToolkit().getImage(Lexique.LOGO);
		setIconImage(icone);
		switch(titre){
		case Lexique.FE_TITRE_CONNE:
			setSize(350,160);
			break;
		case Lexique.FE_TITRE_MODIF:
		case Lexique.FE_TITRE_AJOUT:
			setSize(500, 550);
			break;
		case Lexique.FE_TITRE_LISTE:
			setSize(1500, 610);
			break;
		}
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public abstract void initComposant();
	public abstract void placementComposant(JComponent label, JComponent zone);
	public abstract void placementComposant();
	public static Fenetre creerFenetre(String titre, Observateur o, Employe employe){
		return null;
		
	}
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
	public void notifierObservateur(int id) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,id);
		}
	}

	@Override
	public void notifierObservateur(String valeur, int code) {
		// TODO Auto-generated method stub
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,valeur,code);
		}
	}

	public void notifierObservateur(String valeur){
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this, valeur);
		}
	}

	public void actualiser(Observable o, String valeur){

	}

	@Override
	public void actualiser(Observable o, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiser(Observable o, String valeur, int code) {
		// TODO Auto-generated method stub
		
	}

	@Override
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
	
	public void actualiser(Observable o, int code, Champ[] champs){
		notifierObservateur(code,champs);
	}

	public void actualiser(Observable o, int code, Employe employe){
		notifierObservateur(code,employe);
	}
	public void actualiser(Observable o, int code, Employe employe, Champ[] champs){
		notifierObservateur(code,employe,champs);
	}
	public void notifierObservateur(int code, Champ champs){
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,code,champs);
		}
	}
	public void notifierObservateur(int code, Employe employe, Champ[] champs){
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,code,employe,champs);
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
	
	public void actualiser(Observable o, int code, Champ champs){
		notifierObservateur(code, champs);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		fermeture = FactMessage.message(Lexique.AP_CONFIRM_Q);
		notifierObservateur(Lexique.AP_CONFIRM_Q);
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
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void validerLogin(boolean b) {}

	public void validerMdp(boolean b) {}

	public void validerService(boolean b) {}
	
	public void connexion() {}

	public void reinitialiser() {}

	public void actualiserListeEmpActif(Object[][]liste){}
	
	public void actualiserListeEmpInactif(Object[][]liste){}
	
	public void setForm(Employe unEmploye, String feTitreAjout) {}
}
