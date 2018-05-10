package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controleurs.ButtonEditor;
import Modeles.ConnexionBDD;
import Modeles.Employe;
import Modeles.Requetes;
import Vues.ClientsTableRenderer;

public class Fenetre extends JFrame implements ActionListener{

	private String titre;
	
	private ZModel model;

	public Fenetre(String titre,Object liste[][]) throws SQLException, ClassNotFoundException, ParseException{

		this.titre = titre;
		String entete[] = {"id", "nom", "prenom", "login", "adresse", "cp", "ville", "dateEmbauche", "service_id", "dateDepart", "Modifier", "Supprimer" };
		ZModel model = new ZModel(liste, entete){
		    private static final long serialVersionUID = 1L;

		    public boolean isCellEditable(int row, int column)
		    {
		      return column == 10;
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
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);

	}


	public void iniComposants() throws ClassNotFoundException, SQLException, ParseException{

//		TableModel dataModel = new AbstractTableModel() {
//			public int getColumnCount() { return 10; }
//			public int getRowCount() { return 10;}
//			public Object getValueAt(int row, int col) { return new Integer(row*col); }
//		};
//		Object tabModel[][] = this.toObject(Requetes.listerEmployer());
//		String titre[] = {"id", "nom", "prenom", "login", "mdp", "adresse", "cp", "ville", "dateEmbauche", "service_id", "dateDepart", "Test d'un bouton" };
		//ZModel model = this.enObjet(Requetes.listerEmployer()); 
		
		//System.out.println("test = " + model.data[0][10].getClass());
		
		//TableModel dataModel = this.toTableModel(Requetes.listerEmployer());
		JTable table = new JTable(this.model);
		table.setRowHeight(30);
		//System.out.println(table.getWidth());
		table.setDefaultRenderer(JButton.class, new TableComponent());
		table.getColumnModel().getColumn(10).setCellRenderer(new ClientsTableButtonRenderer());
		table.getColumnModel().getColumn(10).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
		//table.getColumnModel().getColumn(12).setCellRenderer(new ClientsTableButtonRenderer());
		JScrollPane scrollpane = new JScrollPane(table);

		this.getContentPane().add(new JScrollPane(scrollpane), BorderLayout.CENTER);      
		this.getContentPane().add(scrollpane);
		//this.modifierEmploye();
		//	      int[] selection = table.getSelectedRows();
		//	      for (int i = 0; i < selection.length; i++) {
		//	        selection[i] = table.convertRowIndexToModel(selection[i]);
		//	      }
		// selection is now in terms of the underlying TableModel

	}

	public void modifierEmploye() throws ClassNotFoundException, SQLException{
		String nom;
		String prenom;

		//		Object[][] donnees = {
		//                {"Johnathan", "Sykes", Color.red, true, "TENNIS"},
		//                {"Nicolas", "Van de Kampf", Color.black, true, "FOOTBALL"},
		//                {"Damien", "Cuthbert", Color.cyan, true, "RIEN"},
		//                {"Corinne", "Valance", Color.blue, false, "NATATION"},
		//                {"Emilie", "Schrödinger", Color.magenta, false, "FOOTBALL"},
		//                {"Delphine", "Duke", Color.yellow, false, "TENNIS"},
		//                {"Eric", "Trump", Color.pink, true, "FOOTBALL"},
		//        };



//		Hashtable<String, Employe> resultat = Requetes.listerEmployer();
//		String columnValue = resultat.getString(1);
//        System.out.print(columnValue);
//		System.out.println(resultat.getString("nom"));
//		while(resultat.next()){
//			nom = resultat.getString("nom");
//			//visiteurs.ajouterVisiteur(new Visiteur());
//			System.out.println(resultat.getString("nom")+" "+resultat.getString("prenom"));
//		}

		// String[] entetes = {"id", "nom", "prenom", "adresse", "cp", "ville", "date d\'embauche"};
		//        String[] entetes = {"nom", "prenom", "Coleur favorite", "Sport"};
		// 
		//        JTable tableau = new JTable(donnees, entetes);
		//        JPanel panneau2 = new JPanel();
		//        panneau2.add(new JScrollPane(tableau), BorderLayout.CENTER);


		//pack();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
}
