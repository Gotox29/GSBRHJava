package bzh.gsbrh.vues;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Languages;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class Identification extends Fenetre implements Observateur{

	private Champ saisieLogin;
	private Champ saisieMdp;
	private int nord;
	
	public Identification(String titre, Controleur controleur, Observateur o) {
		super(titre, controleur, o);
		setSize(300,160);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComposant();
	}
	
	public void initComposant() {
		this.nord = 20;
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);
		
		Bouton connect = new BValider(this,Bouton._BOUTON_CONNE);
		connect.setText("Loger");
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, connect,
				0,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, connect,
				-25,
				SpringLayout.SOUTH, contentPane);

		this.saisieLogin = new Champ(this, "Login : ", Champ._TEXT);
		this.saisieMdp = new Champ(this, "mot de passe : ", Champ._PASS);
		
		this.contentPane.add(connect);
	}

	public void placementComposant(JComponent label, JComponent zone) {
		this.add(label);
		this.add(zone);
				
		layout.putConstraint(SpringLayout.WEST, label,
				50,
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
	
	public void actualiser(Observable o) {
		boolean connexion = getControleur().verifierMdp(saisieLogin.getText(), saisieMdp.getText());
		if(connexion){
			FMessage.message(FMessage._MESSAGE_C);
			notifierObservateur();
		}
		else
			FMessage.message(FMessage._ERREUR_CO);
	}
}
