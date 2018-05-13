package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class FenFormulaire extends Fenetre implements Observateur{

	private Bouton reinistialise;
	private Bouton valider;
	private JLabel intro;
	private Bouton retour;
	protected Champ[] champs;
	private Employe unEmploye;
	private int spaceLab = 70;
	private int spaceText = 30;
	private int space = 30;
	private Dimension dimensionForm = new Dimension(150,20);
	private int yValide = -30;
	private int yTitre = 50;
	private int departChamp = 95;
	private int index = 1; 

	public FenFormulaire(String titre, Controleur controleur, Observateur o, Employe employe) {
		super(titre, controleur, o);

		unEmploye = employe;

		initComposant();
	}

	private int index(){
		return index++;
	}
	@Override
	public void placementComposant() {
		// TODO Auto-generated method stub
		layout.putConstraint(SpringLayout.WEST, retour,
				5,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, retour,
				5,
				SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.WEST, intro,
				50,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, intro,
				yTitre,
				SpringLayout.NORTH, contentPane);


		layout.putConstraint(SpringLayout.EAST, valider,
				-20,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, valider,
				yValide,
				SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, reinistialise,
				60,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, reinistialise,
				yValide,
				SpringLayout.SOUTH, contentPane);

	}

	public void initComposant(){
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);

		retour = FactBouton.FactoryBouton(this, Lexique.BO_RETOUR);

		// Creation et ajout des composant au panneau		
		intro = new JLabel(titre + " :");

		valider = null; 
		switch(titre){
		case Lexique._TITRE_AJOUT:
			valider = FactBouton.FactoryBouton(this, Lexique.BO_AJOUT);
			break;
		case Lexique._TITRE_MODIF:
			valider = FactBouton.FactoryBouton(this, Lexique.BO_MODIF);
			break;
		}

		reinistialise = FactBouton.FactoryBouton(this, Lexique.BO_REINIT);

		genererChamps();
		contentPane.add(intro,index());
		contentPane.add(valider, index());
		contentPane.add(reinistialise, index());
		contentPane.add(retour, index());

		placementComposant();
	}

	public void genererChamps(){
		champs = new Champ[10];
		champs[0] = new Champ(this, "Id :", unEmploye.getId(), Champ._TEXT);
		if(Lexique._TITRE_MODIF.equals(titre))
			champs[0].bloquerChamp();
		champs[1] = new Champ(this, "Nom :", unEmploye.getNom(), Champ._TEXT);
		champs[2] = new Champ(this, "Prenom :", unEmploye.getPrenom(), Champ._TEXT);
		champs[3] = new Champ(this, "Login :", unEmploye.getLogin(), Champ._TEXT);
		champs[4] = new Champ(this, "Mot de passe :", unEmploye.getMotDePasse(), Champ._TEXT);
		champs[5] = new Champ(this, "Adresse :", unEmploye.getAdresse(), Champ._TEXT);
		champs[6] = new Champ(this, "Code Postal :", unEmploye.getCodePostal(), Champ._TEXT);
		champs[7] = new Champ(this, "Ville :", unEmploye.getVille(), Champ._TEXT);
		champs[8] = new Champ(this, "Date d'embauche :", unEmploye.getDateE(), Champ._TEXT);
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


	public void placementComposant(JComponent label, JComponent zone) {
		// TODO Auto-generated method stub
		label.setMaximumSize(dimensionForm);
		label.setMinimumSize(dimensionForm);
		label.setPreferredSize(dimensionForm);

		zone.setMaximumSize(dimensionForm);
		zone.setMinimumSize(dimensionForm);
		zone.setPreferredSize(dimensionForm);

		this.add(label);
		this.add(zone, index());
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


		if(o == champs[0] && !(Lexique._TITRE_MODIF.equals(titre))){ // Gestion du champ ID
			// Verification de l'existance en base et du respect du format(1 à 4 caractères)
			// et coloration en fonction du resultat
			int i = champs[0].getText().length();
			if(i > 4) i=4;
			String text = champs[0].getText().substring(0, i);
			if(!getControleur().verifId(text)){
				Color rouge = new Color(255, 80, 80);
				champs[0].getCompo().setBackground(rouge);
			}else{
				champs[0].getCompo().setBackground(Color.GREEN);
			}
		}
		if((o == champs[1] || o == champs[2]) && 
				(!champs[2].getText().equals(null) && !champs[2].getText().equals("")) && 
				(!champs[1].getText().equals(null) && !champs[1].getText().equals("")) && 
				(champs[3].getText().equals(null) || champs[3].getText().equals(""))){ 
			// Gestion du champ Login
			// Verification de l'existance en base 
			// generation à partir des nom et prenom automatique
			//Si les champ nom et prénom on été modifié et ne sont pas vide
			String log = champs[2].getText().substring(0, 1) + champs[1].getText();
			if(!getControleur().verifLogin(log)){
				log = getControleur().formaterLog(log);
				champs[3].setText(log);

			}else{
				champs[3].setText(log);
			}
		}
		if(o == champs[3] && !champs[3].getText().equals(unEmploye.getLogin())){
			int i = champs[3].getText().length();
			String log = champs[3].getText().substring(0, i);
			if(!getControleur().verifLogin(log)){
				Color rouge = new Color(255, 80, 80);
				champs[3].getCompo().setBackground(rouge);
			}else{
				champs[3].getCompo().setBackground(Color.GREEN);
			}
		}
	}

	public void actualiser(Observable o, int code){
		if(o instanceof Bouton){
			Employe employe = lireEmploye();
			int retour;
			switch(code){
			case Lexique.FO_AJ:
				retour = getControleur().validerEmploye(employe, Lexique.FO_AJOUT);
				switch(retour){
				case Lexique.FO_MESSAGE:
					FactMessage.message(FactMessage._MESSAGE_A);
					break;
				case Lexique.FO_ERREUR_CH:
					FactMessage.message(FactMessage._ERREUR_CH);
				}
				break;
			case Lexique.FO_MO:
				//	On confirme que l'utilisateur souhaite modifier cette employé
				if(FactMessage.message(FactMessage._CONFIRM_M) == 0){
					retour = getControleur().validerEmploye(employe, Lexique.FO_MODIF);
					switch(retour){
					case Lexique.FO_MESSAGE:
						FactMessage.message(FactMessage._MESSAGE_M);
						unEmploye = employe;
						majChamps();
						break;
					case Lexique.FO_ERREUR_CH:
						FactMessage.message(FactMessage._ERREUR_CH);
					case 2:
						FactMessage.message(FactMessage._ERREUR_MO);
					}
					break;
				}
				FactMessage.message(FactMessage._MESSAGE_M);
				break;
			case Lexique.FO_RE:
				reinitialiser();
				break;
			case Lexique.FO_BA:
				notifierObservateur(Lexique.FO_BA);
				break;
			}
		}
	}
}
