package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

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

/**
 * 
 * @author Anthony Nizac
 *
 */
public class FenFormulaire extends Fenetre implements Observateur{

	private Bouton reinitialise;
	private Bouton valider;
	private JLabel intro;
	private Bouton retour;
	protected Champ[] champs;
	private Employe unEmploye;
	private int spaceLab = 85;
	private int spaceText = 30;
	private int space = 30;
	private int yValide = -30;
	private int yTitre = 50;
	private int departChamp = 95;
	private static Fenetre moi;

	/**
	 * Constructeur privé d'une fenetre de formulaire pour ajouter ou modifier un employé passé en paramètre
	 * 
	 * @param titre : Titre du formulaire
	 * @param o	: Observateur de la fenetre
	 * @param employe : L'employé à modifier, il sera vide si c'est un nouvel employé
	 */
	private FenFormulaire(String titre, Observateur o, Employe employe) {
		super(titre, o);
		unEmploye = employe;
		fermeture = 1;
		initComposant();
	}

	/**
	 * Methode singleton pour intancier un formulaire 
	 * 
	 * @param titre : titre du formulaire
	 * @param o : Observateur de la fenêtre
	 * @param employe : L'employé à modifier, il sera vide si c'est un nouvel employé
	 * @return l'unique instance de la fenetre
	 */
	public static Fenetre creerFenetre(String titre, Observateur o, Employe employe){
		if(moi == null)
			moi = new FenFormulaire(titre, o, employe);
		return moi;
	}

	/**
	 * Methode qui modifie les informations du formulaire : son titre et l'employé à modifier
	 * 
	 * Un switch test le titre, si il s'agit d'une modification on verrouille le champ date d'embauche
	 * sinon il le dévérouille, 
	 * 
	 * @param employe : L'employe à modifier, il sera vide si c'ets un nouvel employé
	 * @para titre : titre du formulaire
	 */
	public void setForm(Employe employe, String titre){
		this.titre = titre;
		setTitle(Lexique.APPLI_TITRE + titre);
		unEmploye = employe;
		majChamps();
		reinitialiser();
		switch(titre){
		case Lexique.FE_TITRE_AJOUT:
			valider.setText(Lexique.BOUTON_AJOUT);
			valider.setId(Lexique.FO_AJ);
			champs[10].deBloquerChamp();
			break;
		case Lexique.FE_TITRE_MODIF:
			valider.setText(Lexique.BOUTON_MODIF);
			valider.setId(Lexique.FO_MO);
			champs[10].bloquerChamp();
			break;
		}
		valider.setIcon(Lexique.ICONVALI);
	}

	/**
	 * 
	 */
	public void placementComposant() {
		// TODO Auto-generated method stub
		layout.putConstraint(SpringLayout.WEST, retour,
				5,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, retour,
				5,
				SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.EAST, image,
				-10,
				SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, image,
				10,
				SpringLayout.NORTH, contentPane);

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

		layout.putConstraint(SpringLayout.WEST, reinitialise,
				60,
				SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, reinitialise,
				yValide,
				SpringLayout.SOUTH, contentPane);

	}

	/**
	 * 
	 */
	public void initComposant(){
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);

		contentPane.setBackground(Lexique.COLOR_BACKGROUNG);

		retour = FactBouton.FactoryBouton(this, Lexique.BO_RETOUR);
		retour.setIcon(Lexique.ICONRETOUR);
		// Creation et ajout des composant au panneau		
		intro = new JLabel(titre + " :");


		valider = null; 
		switch(titre){
		case Lexique.FE_TITRE_AJOUT:
			valider = FactBouton.FactoryBouton(this, Lexique.BO_AJOUT);
			break;
		case Lexique.FE_TITRE_MODIF:
			valider = FactBouton.FactoryBouton(this, Lexique.BO_MODIF);
			break;
		}

		reinitialise = FactBouton.FactoryBouton(this, Lexique.BO_REINIT);
		reinitialise.setIcon(Lexique.ICONREIN);

		genererChamps();
		contentPane.add(intro);
		contentPane.add(image);
		contentPane.add(valider);
		contentPane.add(reinitialise);
		contentPane.add(retour);

		placementComposant();
	}

	/**
	 * 
	 */
	public void genererChamps(){
		champs = new Champ[12];

		champs[0] = new ChampID(this, Lexique.ID, "");
		champs[0].bloquerChamp();
		champs[1] = new Champ(this, Lexique.NOM, unEmploye.getNom(), Lexique.CHAMP_TEXT);
		champs[2] = new Champ(this, Lexique.PRENOM, unEmploye.getPrenom(), Lexique.CHAMP_TEXT);
		champs[3] = new Champ(this, Lexique.LOGIN, unEmploye.getLogin(), Lexique.CHAMP_TEXT);
		champs[4] = new ChampPass(this,Lexique.MDP,unEmploye.getMotDePasse(), true);
		champs[5] = new Champ(this, Lexique.ADR, unEmploye.getAdresse(), Lexique.CHAMP_TEXT);
		champs[6] = new Champ(this, Lexique.CP, unEmploye.getCodePostal(), Lexique.CHAMP_CP);
		if(unEmploye.getDateE() == null)
			unEmploye.setDateE(LocalDate.now().toString());
		champs[7] = new Champ(this, Lexique.VILLE, unEmploye.getVille(), Lexique.CHAMP_TEXT);
		champs[8] = new Champ(this, Lexique.MAIL, unEmploye.getMail(), Lexique.CHAMP_TEXT);
		champs[9] = new Champ(this, Lexique.TEL, unEmploye.getTelephone(), Lexique.CHAMP_TEL);
		champs[10] = new ChampDate(this, "Date d'embauche :", unEmploye.getDateE());
		champs[11] = new Champ(this, Lexique.SERVICE, unEmploye.getServiceId(), Lexique.CHAMP_COMB);
		this.notifierObservateur(Lexique.FO_ATTRCOMB, champs[11]);
	}

	/**
	 * 
	 */
	public void majChamps(){
		champs[0].setValeur(unEmploye.getId());
		champs[1].setValeur(unEmploye.getNom());
		champs[2].setValeur(unEmploye.getPrenom());
		champs[3].setValeur(unEmploye.getLogin());
		champs[4].setValeur(unEmploye.getMotDePasse());
		champs[5].setValeur(unEmploye.getAdresse());
		champs[6].setValeur(unEmploye.getCodePostal());
		champs[7].setValeur(unEmploye.getVille());
		champs[8].setValeur(unEmploye.getMail());
		champs[9].setValeur(unEmploye.getTelephone());
		champs[10].setValeur(unEmploye.getDateE());
		champs[11].setValeur(unEmploye.getServiceId());
	}

	/**
	 * 
	 */
	public void placementComposant(JComponent label, JComponent zone) {
		// TODO Auto-generated method stub
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

	/**
	 * 
	 */
	public void reinitialiser(){
		for(Champ unChamp : champs){
			if (Lexique.FE_TITRE_AJOUT.equals(titre) && unChamp.getType() == Lexique.CHAMP_ID) {
				this.notifierObservateur(Lexique.FO_ATTRID, champs[0]);
			} else// if(!(unChamp.getType() == Lexique.CHAMP_ID && Lexique.FE_TITRE_MODIF.equals(titre)))
			{
				unChamp.initialiserSaisie();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public Employe lireEmploye(){
		Employe employe = new Employe();
		employe.setId(champs[0].getText());
		employe.setNom(champs[1].getText());
		employe.setPrenom(champs[2].getText());
		employe.setLogin(champs[3].getText());
		employe.setMotDePasse(champs[4].getText());
		employe.setAdresse(champs[5].getText());
		employe.setCodePostal(champs[6].getText());
		employe.setVille(champs[7].getText());
		employe.setMail(champs[8].getText());
		employe.setTelephone(champs[9].getText());
		employe.setDateE(champs[10].getText());
		employe.setServiceId(champs[11].getText());
		return employe;
	}

	/**
	 * 
	 */
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
		if(o == champs[0]){
			this.notifierObservateur(Lexique.COLOR_ID, champs[0]);
		} else if(o == champs[1] || o == champs[2]){
			if(!champs[2].getText().isEmpty() && 
					!champs[1].getText().isEmpty()){
				Champ[] lesChamps = new Champ[3];
				lesChamps[0] = champs[1];
				lesChamps[1] = champs[2];
				lesChamps[2] = champs[3];	
				this.notifierObservateur(Lexique.GENERE_LOG, lesChamps);
			}
		} else if(o == champs[3]){
			this.notifierObservateur(Lexique.COLOR_LOG, champs[3]);
		} else if(o == champs[4]){
			this.notifierObservateur(Lexique.COLOR_MDP, champs[4]);
		} 

	}

	/**
	 * 
	 */
	public void afficher(int code, Employe employe){	//	Le controleur demande à afficher un message
		switch(code){
		case Lexique.FO_MESSAGE_A:
			FactMessage.message(Lexique.FO_MESSAGE_A);		
			reinitialiser();
			notifierObservateur(Lexique.LI_VALIDE_AJ);
			break;
		case Lexique.FO_MESSAGE_MO:
			FactMessage.message(Lexique.FO_MESSAGE_MO);
			unEmploye = employe;
			majChamps();
			notifierObservateur(Lexique.LI_VALIDE_MO);
			break;
		case Lexique.FO_ERREUR_AJ:
		case Lexique.FO_ERREUR_ID:
		case Lexique.FO_ERREUR_NOM:
		case Lexique.FO_ERREUR_PRE:
		case Lexique.FO_ERREUR_LOG:
		case Lexique.FO_ERREUR_MDP:
		case Lexique.FO_ERREUR_ADR:
		case Lexique.FO_ERREUR_CP:
		case Lexique.FO_ERREUR_VILLE:
		case Lexique.FO_ERREUR_MAIL:
		case Lexique.FO_ERREUR_TEL:
		case Lexique.FO_ERREUR_DATE:
			FactMessage.message(code);
			break;
		}
	}

	/**
	 * 
	 */
	public void actualiser(Observable o, int code){
		Employe employe = lireEmploye();
		int retour;
		switch(code){
		case Lexique.FO_AJ:	//	L'utilisateur ajoute un employé
			this.notifierObservateur(Lexique.FO_AJOUT, employe, champs);
			break;
		case Lexique.FO_MO:	//	L'utilisateur modifie un employé
			this.notifierObservateur(Lexique.FO_MODIF, employe, champs);
			break;
			//	L'utilisateur réinitialise le formulaire
		case Lexique.FO_RE:
			reinitialiser();
			break;
			//	L'utitilsateur quitte le formulaire
		case Lexique.FO_BA:
			notifierObservateur(Lexique.FO_BA);
			break;
		case Lexique.BO_DATE_NOW:
			notifierObservateur(Lexique.BO_DATE_NOW, champs[10]);
			break;
		case Lexique.BO_GENE_PASS:
			notifierObservateur(Lexique.BO_GENE_PASS, champs[4]);
			break;
		}
	}

	/**
	 * 
	 */
	public void windowClosing(WindowEvent arg0) {
		notifierObservateur(Lexique.FO_FERMETURE);
	}
}
