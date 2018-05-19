package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class FenIdentification extends Fenetre implements Observateur{

	private Champ saisieLogin;
	private Champ saisieMdp;
	private Bouton connect;
	private boolean loginOk = false;
	private boolean mdpOk = false;
	private boolean serviceOk = false;
	private int nord = 20;
	private int fermeture = 0;

	public FenIdentification(String titre, Observateur o) {
		super(titre, o);
		Employe unEmploye = new Employe();
		initComposant();
	}
	
	public void validerLogin(boolean result){
		loginOk=result;
	}
	
	public void validerMdp(boolean result){
		mdpOk=result;
	}
	
	public void validerService(boolean result){
		serviceOk=result;
	}
	
	public void initComposant() {
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);
		connect = FactBouton.FactoryBouton(this, Lexique.BO_CONNECT);
		connect.setIcon(Lexique.ICONCONNE);
		contentPane.add(image);
		contentPane.setBackground(Lexique.COLOR_BACKGROUNG);

		this.saisieLogin = new Champ(this, Lexique.LOGIN, Lexique.CHAMP_TEXT);
		this.saisieMdp = new ChampPass(this, Lexique.MDP);

		this.contentPane.add(connect);
		
		placementComposant();
	}

	public void placementComposant(JComponent label, JComponent zone) {
		this.add(label);
		this.add(zone);

		layout.putConstraint(SpringLayout.WEST, label,
				100,
				SpringLayout.WEST, this.contentPane);
		layout.putConstraint(SpringLayout.NORTH, label,
				this.nord,
				SpringLayout.NORTH, this.contentPane);

		layout.putConstraint(SpringLayout.WEST, zone,
				5,
				SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, zone,
				this.nord,
				SpringLayout.NORTH, this.contentPane);
		this.nord += 25;
	}
	
	public void connexion(){
		if(loginOk && mdpOk){
			if(serviceOk){
				FactMessage.message(Lexique.ID_MESSAGE_CO);
				notifierObservateur(Lexique.ID_VALIDE_CO);
			}else
				FactMessage.message(Lexique.ID_ERREUR_SE);
		} else
			FactMessage.message(Lexique.ID_ERREUR_CO);
	}
	
	public void actualiser(Observable o) {
		if(o == connect){
			Champ[] champs = new Champ[2];
			champs[0] = saisieLogin;
			champs[1] = saisieMdp;
			this.notifierObservateur(Lexique.CONNEXION, champs);
		}
	}
	
	public void placementComposant() {
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, connect,
				0,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, connect,
				-25,
				SpringLayout.SOUTH, contentPane);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, image,
				50,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, image,
				0,
				SpringLayout.VERTICAL_CENTER, contentPane);
	}
	
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		notifierObservateur(Lexique.AP_CONFIRM_Q);
	}
}
