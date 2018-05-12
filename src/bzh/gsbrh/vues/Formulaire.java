package bzh.gsbrh.vues;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class Formulaire extends Fenetre implements Observateur{

	protected Champ[] champs;
	private Employe unEmploye;
	private int spaceLab = 70;
	private int spaceText = 30;
	private int space = 30;
	private Dimension dimensionForm = new Dimension(150,20);
	private int yValide = -30;
	private int yTitre = 50;
	private int departChamp = 95; 
	final public static int _AJOUT = 0;
	final public static int _MODIF = 1;
	final public static int _MESSAGE = 0;
	final public static int _ERREUR_CH = 1;
	final public static int _ERREUR_MO = 2;

	public Formulaire(String titre, Controleur controleur, Observateur o, Employe employe) {
		super(titre, controleur, o);

		unEmploye = employe;
		//Définir la taille de la fenêtre.
		this.setSize(500, 500);

		//Pour une autre position : this.setLocation(x,y);
		this.setLocationRelativeTo(null);

		//La fenêtre ne peut pas être redimensionnée.
		this.setResizable(false);

		//On peux la fermer
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); // DO_NOTHING_ON_CLOSE
		
		initComposant();
	}

	public void initComposant(){
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);

		Bouton retour = FBouton.FactoryBouton(this, FBouton._RETOUR);
		contentPane.add(retour);
		layout.putConstraint(SpringLayout.WEST, retour,
				5,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, retour,
				5,
				SpringLayout.WEST, contentPane);

		// Creation et ajout des composant au panneau		
		JLabel intro = new JLabel(titre + " :");

		contentPane.add(intro);

		layout.putConstraint(SpringLayout.WEST, intro,
				50,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, intro,
				yTitre,
				SpringLayout.NORTH, contentPane);
		Bouton valider = null; 
		switch(titre){
		case Fenetre._TITRE_AJOUT:
			valider = FBouton.FactoryBouton(this, FBouton._AJOUT);
			break;
		case Fenetre._TITRE_MODIF:
			valider = FBouton.FactoryBouton(this, FBouton._MODIF);
			break;
		}
		contentPane.add(valider);
		layout.putConstraint(SpringLayout.EAST, valider,
				-20,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, valider,
				yValide,
				SpringLayout.SOUTH, contentPane);

		Bouton reinistialise = FBouton.FactoryBouton(this, FBouton._REINIT);
		contentPane.add(reinistialise);
		layout.putConstraint(SpringLayout.WEST, reinistialise,
				60,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, reinistialise,
				yValide,
				SpringLayout.SOUTH, contentPane);
		genererChamps();

	}

	public void genererChamps(){
		champs = new Champ[10];

		champs[0] = new Champ(this, "Id :", unEmploye.getId());
		champs[1] = new Champ(this, "Nom :", unEmploye.getNom());
		champs[2] = new Champ(this, "Prenom :", unEmploye.getPrenom());
		champs[3] = new Champ(this, "Login :", unEmploye.getLogin());
		champs[4] = new Champ(this, "Mot de passe :", unEmploye.getMotDePasse());
		champs[5] = new Champ(this, "Adresse :", unEmploye.getAdresse());
		champs[6] = new Champ(this, "Code Postal :", unEmploye.getCodePostal());
		champs[7] = new Champ(this, "Ville :", unEmploye.getVille());
		champs[8] = new Champ(this, "Date d'embauche :", unEmploye.getDateE());
		champs[9] = new Champ(this, "Service :", unEmploye.getServiceId(), getControleur().listeService());

	}

	public void majChamps(){

		champs[0].setValeur(unEmploye.getId());
		champs[1].setValeur(unEmploye.getNom());
		champs[2].setValeur(unEmploye.getPrenom());
		champs[3].setValeur(unEmploye.getLogin());
		champs[4].setValeur(unEmploye.getMotDePasse());
		champs[5].setValeur(unEmploye.getAdresse());
		champs[6].setValeur(unEmploye.getCodePostal());
		champs[7].setValeur(unEmploye.getVille());
		champs[8].setValeur(unEmploye.getDateE());
		champs[9].setValeur(unEmploye.getServiceId());


	}

	@Override
	public void placementComposant(JComponent label, JComponent zone) {
		// TODO Auto-generated method stub
		label.setMaximumSize(dimensionForm);
		label.setMinimumSize(dimensionForm);
		label.setPreferredSize(dimensionForm);

		zone.setMaximumSize(dimensionForm);
		zone.setMinimumSize(dimensionForm);
		zone.setPreferredSize(dimensionForm);

		this.add(label);
		this.add(zone);

		layout.putConstraint(SpringLayout.WEST, label,
				spaceLab,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, label,
				departChamp,
				SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, zone,
				spaceText,
				SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, zone,
				departChamp,
				SpringLayout.NORTH, this);
		departChamp += space;
	}

	public void reinitialiser(){
		for(Champ unChamp : champs){
			unChamp.initialiserSaisie();
		}
	}

	public Employe lireEmploye(){
		Employe employe = new Employe();
		employe.setId(champs[0].getDansSaisie());
		employe.setNom(champs[1].getDansSaisie());
		employe.setPrenom(champs[2].getDansSaisie());
		employe.setLogin(champs[3].getDansSaisie());
		employe.setMotDePasse(champs[4].getDansSaisie());
		employe.setAdresse(champs[5].getDansSaisie());
		employe.setCodePostal(champs[6].getDansSaisie());
		employe.setVille(champs[7].getDansSaisie());
		employe.setDateE(champs[8].getDansSaisie());
		employe.setServiceId(champs[9].getIndex());
		return employe;
	}

	@Override
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
		if(o instanceof BValider){
			Employe employe = lireEmploye();
			int retour;
			switch(titre){
			case Fenetre._TITRE_AJOUT:
				retour = getControleur().validerEmploye(employe, _AJOUT);
				switch(retour){
				case _MESSAGE:
					FMessage.message(FMessage._MESSAGE_A);
					break;
				case _ERREUR_CH:
					FMessage.message(FMessage._ERREUR_CH);
				}
				break;
			case Fenetre._TITRE_MODIF:
				//	On confirme que l'utilisateur souhaite modifier cette employé
				if(FMessage.message(FMessage._CONFIRM_M) == 0){
					retour = getControleur().validerEmploye(employe, _MODIF);
					switch(retour){
					case _MESSAGE:
						FMessage.message(FMessage._MESSAGE_M);
						unEmploye = employe;
						majChamps();
						break;
					case _ERREUR_CH:
						FMessage.message(FMessage._ERREUR_CH);
					case 2:
						FMessage.message(FMessage._ERREUR_MO);
					}
					break;
				}
				FMessage.message(FMessage._MESSAGE_M);
				break;
			}
		}else if(o instanceof BReinitialiser){
			reinitialiser();
		}else if(o instanceof BRetour){
			notifierObservateur();
		}
	}

}
