package bzh.gsbrh.vues;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class PopUp extends JDialog implements Observateur, Observable {

	private Employe unEmploye;
	private Container contentPane;
	protected SpringLayout layout = new SpringLayout();
	private int nord = 15;
	private Dimension dimensionFormLab = new Dimension(200,20);
	private Dimension dimensionFormCham = new Dimension(125,20);
	private Dimension dimensionBout = new Dimension(95,25);
	private Bouton valider;
	private Bouton annuler;
	private Bouton deprog;
	private JPanel boutons;
	private Champ date;
	private boolean ok;
	private ArrayList<Observateur> tabObservateur;


	public PopUp(JFrame parent, String title, boolean modal, Observateur o, Employe employe) {
		// TODO Auto-generated constructor stub
		super(parent, title, modal);
		tabObservateur = new ArrayList();
		ajouterObservateur(o);
		this.setSize(300,160);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		unEmploye = employe;
		this.initComposant();
	}

	public void lancePopUp(){
		this.setVisible(true);
		if(ok) this.setVisible(false);
	}

	public void initComposant(){
		//this.getContentPane()
		contentPane = getContentPane();
		contentPane.setLayout(layout);
		valider = FactBouton.FactoryBouton(this, Lexique.BO_VALIDER);
		annuler = FactBouton.FactoryBouton(this, Lexique.BO_RETOUR);
		deprog = FactBouton.FactoryBouton(this, Lexique.BO_REINIT);

		String valeur;
		if(unEmploye.getDateD().equals(null) || unEmploye.getDateD() == null || unEmploye.getDateD().equals("")){
			valeur = LocalDate.now().toString();
		}else
			valeur = unEmploye.getDateD();
		placementComposant();


		date = new ChampDate(this, Lexique.M_PROGRAM_DATED, valeur);

		// Creation et ajout des composant au panneau       

	}
	public void placementComposant(){
		boutons = new JPanel(new GridLayout(1,3));
		valider.setPreferredSize(dimensionBout);
		annuler.setPreferredSize(dimensionBout);
		deprog.setPreferredSize(dimensionBout);
		boutons.add(valider);
		boutons.add(annuler);
		if(!(unEmploye.getDateD().equals(null) || unEmploye.getDateD() == null || unEmploye.getDateD().equals("")))
			boutons.add(deprog);
		contentPane.add(boutons);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boutons,
				0,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, boutons,
				-50,
				SpringLayout.SOUTH, contentPane);
	}
	public void placementComposant(JComponent label, JComponent zone) {

		label.setMaximumSize(dimensionFormLab);
		label.setMinimumSize(dimensionFormLab);
		label.setPreferredSize(dimensionFormLab);

		zone.setMaximumSize(dimensionFormCham);
		zone.setMinimumSize(dimensionFormCham);
		zone.setPreferredSize(dimensionFormCham);

		contentPane.add(label);
		contentPane.add(zone);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label,
				0,
				SpringLayout.HORIZONTAL_CENTER, this.contentPane);
		layout.putConstraint(SpringLayout.NORTH, label,
				this.nord,
				SpringLayout.NORTH, this.contentPane);
		this.nord += 25;
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, zone,
				0,
				SpringLayout.HORIZONTAL_CENTER, this.contentPane);
		layout.putConstraint(SpringLayout.NORTH, zone,
				this.nord,
				SpringLayout.NORTH, this.contentPane);
		this.nord += 25;
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
	@Override
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actualiser(Observable o, int id) {
		// TODO Auto-generated method stub
		switch(id){
		case Lexique.PP_DATE_VA:
			this.notifierObservateur(Lexique.M_DATED, date);
			break;
		case Lexique.PP_DATE_RE:
			date.initialiserSaisie();
			this.notifierObservateur(unEmploye.getId(), Lexique.PP_DATE_RE);
			break;
		case Lexique.PP_DATE_BA:
			this.dispose();
		}
	}

	@Override
	public void actualiser(Observable o, String valeur) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actualiser(Observable o, String valeur, int code) {
		// TODO Auto-generated method stub
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
	
	public void actualiser(Observable o, int code, Champ[] champs){
		notifierObservateur(code,champs);
	}

	public void actualiser(Observable o, int code, Employe employe){
		notifierObservateur(code,employe);
	}
	public void notifierObservateur(int code, Champ champs){
		for(int i = 0; i<tabObservateur.size(); i++){
			tabObservateur.get(i).actualiser(this,code,champs);
		}
	}
	public void actualiser(Observable o,int code, Champ champs){
		
	}
	public void setOk(boolean valide){
		ok = valide;
		if(ok) this.setVisible(false);
	}
	public boolean getOk() {
		// TODO Auto-generated method stub
		return ok;
	}
	public void erreur(){
		System.out.println("erreur");
		FactMessage.message(Lexique.LI_ERREUR_MO);
	}
	public void notifierObservateur(int code, Employe employe, Champ[] champs){}
	public void actualiser(Observable o, int code, Employe employe, Champ[] champs){}

	public boolean valider(){
		int confirme = FactMessage.message(Lexique.LI_CONFIRM_MODIFDATE);
		if(confirme == 0)
			return true;
		return false;
	}
}
