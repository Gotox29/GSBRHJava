package bzh.gsbrh.vues;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.GestionEmploye;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class FenListeEmployes extends Fenetre implements Observateur{

	private JLabel info;
	private JLabel nomU;
	private Bouton ajouter;
	private Bouton deconnect;
	private JTabbedPane onglet = new JTabbedPane(JTabbedPane.BOTTOM);
	private JLabel onglet1;
	private JLabel onglet2;
	Onglet liste;
	Onglet listeI;
	int rowActif;
	int rowInactif;
	Object[][] listeInactif;
	Object[][] listeActif;
	String[] entete;

	public void setNomU(String nom){
		nomU.setText(nom);
	}

	public FenListeEmployes(String titre, Observateur o, Object[][] listeA, Object[][] listeI, String[] entete) {
		super(titre, o);
		this.fermeture = 1;
		rowActif = listeA.length;
		rowInactif = listeI.length;
		listeActif = listeA;
		listeInactif = listeI;
		this.entete = entete;
		initComposant();
	}

	public void actualiserListeEmpActif(Object[][]liste){
		this.liste.actualiserListeEmploye(liste);
		onglet1.setText(Lexique.ONGLET_1+" ("+ listeActif.length +")");
	}

	public void actualiserListeEmpInactif(Object[][]liste){
		this.listeI.actualiserListeEmploye(liste);
		onglet2.setText(Lexique.ONGLET_2+" ("+ listeInactif.length +")");
	}

	public void initComposant() {
		liste = new Onglet(this, layout, listeActif, entete);
		listeI = new Onglet(this, layout, listeInactif, entete);
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);

		contentPane.setBackground(Lexique.COLOR_BACKGROUNG);

		ajouter = FactBouton.FactoryBouton(this, Lexique.BO_AJOUT);
		ajouter.setIcon(Lexique.ICONAJOUT);
		deconnect = FactBouton.FactoryBouton(this, Lexique.BO_DECONNECT);
		deconnect.setIcon(Lexique.ICONDECO);
		info = new JLabel(Lexique.M_ACCUEIL); 
		nomU = new JLabel("");

		contentPane.add(ajouter);
		contentPane.add(info);
		contentPane.add(nomU);
		contentPane.add(deconnect);
		onglet1 = new JLabel(Lexique.ONGLET_1+" ("+ rowActif +")");
		onglet.add("", this.liste.getScrollpane());
		onglet.setTabComponentAt(0, onglet1);
		onglet2 = new JLabel(Lexique.ONGLET_2+" ("+ rowInactif +")");
		onglet.add("", this.listeI.getScrollpane());
		onglet.setTabComponentAt(1, onglet2);
		contentPane.add(onglet);
		contentPane.add(image);
		placementComposant();

	}

	@Override
	public void actualiser(Observable o) {
		notifierObservateur();


	}
	public void actualiser(Observable o, int id) {
		notifierObservateur(id);

	}

	public void actualiser(Observable o, String valeur, int code) {
		notifierObservateur(valeur,code);

	}

	public void placementComposant(JComponent label, JComponent zone) {}

	public void placementComposant() {

		layout.putConstraint(SpringLayout.EAST, ajouter,
				-30,
				SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, ajouter,
				20,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, image,
				10,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, image,
				10,
				SpringLayout.NORTH, contentPane);


		layout.putConstraint(SpringLayout.WEST, onglet,
				1,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, onglet,
				100,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, info,
				80,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, info,
				17,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, nomU,
				15,
				SpringLayout.EAST, info);
		layout.putConstraint(SpringLayout.NORTH, nomU,
				17,
				SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, deconnect,
				15,
				SpringLayout.EAST, info);
		layout.putConstraint(SpringLayout.NORTH, deconnect,
				17,
				SpringLayout.SOUTH, info);
	}
}
