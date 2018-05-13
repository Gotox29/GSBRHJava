package bzh.gsbrh.vues;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.modeles.GestionEmploye;
import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

public class FenListeEmployes extends Fenetre implements Observateur{

	private GestionEmploye lesEmployes;
	private JTable table;
	private JLabel info;
	private ImageIcon imIc;
	private JLabel image;
	private Bouton ajouter;
	private JScrollPane scrollpane;
	private Object[][]liste;

	public FenListeEmployes(String titre, Controleur controleur, Observateur o) {
		super(titre, controleur, o);
		this.lesEmployes = new GestionEmploye();
		initComposant();
	}	


	@Override
	public void initComposant() {
		liste = this.lesEmployes.arrayVersList();

		creerTableau();
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);

		imIc = new ImageIcon("C:/workspaceBis/GSBRH_OBS/src/bzh.gsbrh.images/index.png");
		image = new JLabel(imIc);
		ajouter = FactBouton.FactoryBouton(this, Lexique.BO_AJOUT);
		info = new JLabel("Informations");

		contentPane.add(info);
		contentPane.add(ajouter);
		contentPane.add(scrollpane);
		contentPane.add(image);
		placementComposant();

	}
	public Object [][] ajouterBoutons(Object [][] liste){
		int row = liste.length;
		for(int i = 0; i < row; i++){
			int col = liste[i].length;
			Bouton modifier = FactBouton.FactoryBouton(this, Lexique.BO_MODIF);
			Bouton supprimer = FactBouton.FactoryBouton(this, Lexique.BO_SUPPR);
			liste[i][col - 2] = modifier;
			liste[i][col - 1] = supprimer;
		}
		return liste;
	}

	private void creerTableau(){		
		liste = ajouterBoutons(liste);
		String [] entete = this.lesEmployes.getEntete();
		ZModel model = new ZModel(liste, entete)
		{
			//private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return (column == 10 || column == 11);
			}
		};

		JTable table = new JTable(model);
		table.setRowHeight(30);

		table.setDefaultRenderer(JButton.class, new TableComponent());
		// Place en ecoute les colonnes avec des boutons grace au methodes de ClientsTableRenderer
		table.getColumnModel().getColumn(10).setCellEditor(new ClientsTableRenderer(new JCheckBox(), this));
		table.getColumnModel().getColumn(11).setCellEditor(new ClientsTableRenderer(new JCheckBox(), this));

		scrollpane = new JScrollPane(table);
		Dimension dimension = new Dimension(1295,400);
		scrollpane.setPreferredSize(dimension);
	}


	@Override
	public void actualiser(Observable o) {
		// TODO Auto-generated method stub
		notifierObservateur();

	}
	public void actualiser(Observable o, int id) {
		// TODO Auto-generated method stub
		notifierObservateur(id);
	}
	public void actualiser(Observable o, String valeur) {
		// TODO Auto-generated method stub
	}
	@Override
	public void actualiser(Observable o, String valeur, int code) {
		if(o instanceof EditeurCellule){
			notifierObservateur(valeur,code);
		}
		System.out.println("ici");
	}

	@Override
	public void placementComposant(JComponent label, JComponent zone) {}

	@Override
	public void placementComposant() {
		// TODO Auto-generated method stub


		layout.putConstraint(SpringLayout.WEST, ajouter,
				1200,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, ajouter,
				10,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, scrollpane,
				0,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, scrollpane,
				50,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, info,
				10,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, info,
				-3,
				SpringLayout.SOUTH, contentPane);

	}



}
