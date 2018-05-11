package bzh.gsbrh.vues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
//import Vues.Bouton;
//import bzh.gsbrh.controleurs.ButtonEditor;
import bzh.gsbrh.modeles.ConnexionBDD;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Requetes;
import bzh.gsbrh.observers.Bouton;


public class Fenetre extends JFrame implements ActionListener{

	private String titre;
	private String entete[] = {"id", "nom", "prenom", "login", "adresse", "cp", "ville", "dateEmbauche", "service_id", "dateDepart", "Modifier", "Supprimer" };
	
	private ZModel model;
	private static Controleur cont;

	public Fenetre(Controleur controleur, String titre,Object liste[][]){
		this.cont = controleur;
		this.titre = titre;
		liste = ajouterBoutons(liste);
		
		ZModel model = new ZModel(liste, entete){
		    //private static final long serialVersionUID = 1L;

		    public boolean isCellEditable(int row, int column)
		    {
		      return (column == 10 || column == 11);
		    }
		  };
		this.model = model;

		//Définir la taille de la fenêtre.
		this.setSize(1300, 500);

		//Pour une autre position : this.setLocation(x,y);
		this.setLocationRelativeTo(null);

		//ajout des éléments de la fenêtre
		this.iniComposants();

		//Définir un titre pour la fenêtre.
		this.setTitle("Interface : " + titre);

		//La fenêtre ne peut pas être redimensionnée.
		this.setResizable(false);

		//On peux la fermer
		//this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});

	}
	
	public void reinitFenetre(Object liste[][]){
		getContentPane().removeAll();
		liste = ajouterBoutons(liste);
		ZModel model = new ZModel(liste, entete){
		    //private static final long serialVersionUID = 1L;

		    public boolean isCellEditable(int row, int column)
		    {
		      return (column == 10 || column == 11);
		    }
		  };
		this.model = model;
		iniComposants();
	}

	public void iniComposants(){
		
		ImageIcon imIc = new ImageIcon("c:/workspaceBis/Projet_GSBRH/src/bzh/gsbrh/images/index.png");
		//Image im = imIc.getImage();
		JLabel image = new JLabel(imIc);
		
		JTable table = new JTable(this.model);
		table.setRowHeight(30);
		
		table.setDefaultRenderer(Bouton.class, new TableComponent());
		

		// Place en ecoute les colonnes avec des boutons grace au methodes de ClientsTableRenderer
		table.getColumnModel().getColumn(10).setCellEditor(new ClientsTableRenderer(new JCheckBox(), cont));
		table.getColumnModel().getColumn(11).setCellEditor(new ClientsTableRenderer(new JCheckBox(), cont));
		
		JScrollPane scrollpane = new JScrollPane(table);
		
		Container contentPane = this.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		contentPane.add(scrollpane);
		contentPane.add(image);
		Dimension dimension = new Dimension(1295,400);
		scrollpane.setPreferredSize(dimension);
		layout.putConstraint(SpringLayout.WEST, scrollpane,
				0,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, scrollpane,
				50,
				SpringLayout.NORTH, contentPane);
		
		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(this);
		contentPane.add(ajouter);
		
		layout.putConstraint(SpringLayout.WEST, ajouter,
				1200,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, ajouter,
				10,
				SpringLayout.NORTH, contentPane);
		
		JLabel label = new JLabel("Informations");
		contentPane.add(label);
		
		layout.putConstraint(SpringLayout.WEST, label,
				10,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label,
				3,
				SpringLayout.SOUTH, scrollpane);

	}
	
	public static Controleur getControleur(){
		return cont;
	}
	
	public Object [][] ajouterBoutons(Object [][] liste){
		
		int row = liste.length;
		for(int i = 0; i < row; i++){
			int col = liste[i].length;
			JButton modifier = new JButton("Modifier");
			JButton supprimer = new JButton("Supprimer");
			liste[i][col - 2] = modifier;
			liste[i][col - 1] = supprimer;
		}
		return liste;
	}
	


	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String action = event.getActionCommand();
		switch(action){
		case "Ajouter":
			cont.lancerAjoutEmploye();
		}
		

	}


	public void init(Object[][] liste) {
		String entete[] = {"id", "nom", "prenom", "login", "adresse", "cp", "ville", "dateEmbauche", "service_id", "dateDepart", "Modifier", "Supprimer" };
		liste = ajouterBoutons(liste);
		ZModel model = new ZModel(liste, entete){
		    //private static final long serialVersionUID = 1L;

		    public boolean isCellEditable(int row, int column)
		    {
		      return (column == 10 || column == 11);
		    }
		  };
		this.model = model;
		
	}
	
	
}
