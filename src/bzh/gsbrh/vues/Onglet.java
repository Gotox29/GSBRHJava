package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.Panneau;

public class Onglet extends Panneau implements Observable{
	private JTable table;
	private ZModel model;
	private JScrollPane scrollpane;
	private Object[][]liste;
	private String[]entete;
	private Dimension dimension = new Dimension(1480,450);

	public Onglet(Observateur o, SpringLayout layout, Object[][]liste, String []entete) {
		super(o, layout);
		this.liste = liste;
		this.entete = entete;
		creerTableau();
		
	}
	
	public JScrollPane getScrollpane(){
		return scrollpane;
	}
	
	public void actualiserListeEmploye(Object[][]liste){
		this.liste = ajouterBoutons(liste);
		model.setData(ajouterBoutons(this.liste));
		model.modifierEmploye();
	}
	
	public Object [][] ajouterBoutons(Object [][] liste){
		System.out.println(liste.length);
		int row = liste.length;
		for(int i = 0; i < row; i++){
			int col = liste[i].length;
			Bouton modifier = FactBouton.FactoryBouton(this, Lexique.BO_MODIF);
			modifier.setIcon(Lexique.ICONMODIF);
			modifier.setToolTipText(Lexique.BOUTON_MODIF + " " + liste[i][1] + " " + liste[i][2]);
			Bouton supprimer = FactBouton.FactoryBouton(this, Lexique.BO_SUPPR);
			supprimer.setIcon(Lexique.ICONPROG);
			liste[i][col - 2] = modifier;
			liste[i][col - 1] = supprimer;
		}
		return liste;
	}
	public void creerTableau(){
		liste = ajouterBoutons(liste);
		model = new ZModel(liste, entete)
		{
			public boolean isCellEditable(int row, int column)
			{
				return (column == 12 || column == 13);
			}
		};

		JTable table = new JTable(model);

		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(30);
		table.setGridColor(new Color(50,90,230));

		table.setDefaultRenderer(JButton.class, new TableComponent());
		// Place en ecoute les colonnes avec des boutons grace au methodes de ClientsTableRenderer
		table.getColumnModel().getColumn(12).setCellEditor(new ClientsTableRenderer(new JCheckBox(), this));
		table.getColumnModel().getColumn(13).setCellEditor(new ClientsTableRenderer(new JCheckBox(), this));

		scrollpane = new JScrollPane(table);
		
		scrollpane.setPreferredSize(dimension);
		
	}
	@Override
	public void placementChamps(JComponent label, JComponent zone) {
		// TODO Auto-generated method stub
		
	}

}
