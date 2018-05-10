package Vues;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Formulaire extends JFrame {

    private String titre;
    private Hashtable <Integer,String> services;
    private  Hashtable <Integer,Component> champs;
    public Formulaire(String titre, Hashtable <Integer,Component> champs) throws SQLException, ClassNotFoundException, ParseException{

        this.titre = titre;
        this.champs = champs;

        //Définir la taille de la fenêtre.
        this.setSize(500, 500);

        //Pour une autre position : this.setLocation(x,y);
        this.setLocationRelativeTo(null);

        //ajout des éléments de la fenêtre
        this.iniComposants();

        //Définir un titre pour la fenêtre.
        this.setTitle("Interface : " + titre);

        //La fenêtre ne peut pas être redimensionnée.
        this.setResizable(true);

        //On peux la fermer
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);

    }

    public void iniComposants() throws ClassNotFoundException, SQLException, ParseException{
        // Panneau principale de la fenêtre 
        //Set up the content pane.
        Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        //pan.setBorder(javax.swing.BorderFactory.createTitledBorder("Test"));
        
        //Create and add the components.
        JLabel intro = new JLabel(titre + " :");
        
        contentPane.add(intro);
        
       
        
        layout.putConstraint(SpringLayout.WEST, intro,
                50,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, intro,
                20,
                SpringLayout.NORTH, contentPane);
        
        int pos = 70; 
        int spaceLab = 60;
        int spaceText = 50;
        Dimension dimension = new Dimension(150,20);
        int space = 30;
        
//        int row = this.champs.size();
//
//        String key;
//        //Enumeration keys = this.champs.keys();
//        
//        Component champ = null;
//        //Set keys = this.champs.keySet();
//        Iterator i= this.champs.iterator(); // on crée un Iterator pour parcourir notre HashSet
//        while(i.hasNext()) // tant qu'on a un suivant
//        {
//        	JLabel test = new JLabel("sest");
//        	addElement(contentPane, (Component) i.next(), test, layout, spaceLab, spaceText, pos);
//            pos += space;
//        }
        
//        Iterator<String> itr = keys.iterator(); 
//        
//        JLabel text;
//        
//        while (itr.hasNext()) { 
////        for(int i = 0; i<row;i++){
//            key = itr.next();
//            
//            champ = this.champs.get(key);
//
//            text = new JLabel(key);   
//            System.out.println(" = " + key);
//            addElement(contentPane, champ, text, layout, spaceLab, spaceText, pos);
//            pos += space;
//        }

        JLabel idLab = new JLabel("Code d'identifiant : ");
    
        addElement(contentPane, this.champs.get(1), idLab, layout, spaceLab, spaceText, pos);
        pos += space;
        
        JLabel nomLab = new JLabel("Nom : ");
        
        addElement(contentPane, this.champs.get(2), nomLab, layout, spaceLab, spaceText, pos);
        pos += space;
        
        JLabel prenomLab = new JLabel("prenom : ");
        
        addElement(contentPane, this.champs.get(3), prenomLab, layout, spaceLab, spaceText, pos);
        pos += space;
        
        JLabel loginLab = new JLabel("Identifiant de connexion : ");
        
        addElement(contentPane, this.champs.get(4), loginLab, layout, spaceLab, spaceText, pos);
        pos += space;
        
        JLabel mdpLab = new JLabel("Mot de passe : ");
        
        addElement(contentPane, this.champs.get(5), mdpLab, layout, spaceLab, spaceText, pos);
        pos += space;
        
        JLabel adrLab = new JLabel("Adresse : ");
        
        addElement(contentPane, this.champs.get(6), adrLab, layout, spaceLab, spaceText, pos);
        pos += space;

        JLabel cPLab = new JLabel("Code postal : ");
        
        addElement(contentPane, this.champs.get(7), cPLab, layout, spaceLab, spaceText, pos);
        pos += space;

        JLabel villeLab = new JLabel("Ville : ");
       
        addElement(contentPane, this.champs.get(8), villeLab, layout, spaceLab, spaceText, pos);
        pos += space;

        JLabel dateELab = new JLabel("Date d'embauche : ");
        
        addElement(contentPane, this.champs.get(9), dateELab, layout, spaceLab, spaceText, pos);
        pos += space;

        JLabel servLab = new JLabel("Service : ");
        addElement(contentPane, this.champs.get(10), servLab, layout, spaceLab, spaceText, pos);
        pos += space*2;
        
        addBouton(contentPane, this.champs.get(12), this.champs.get(11), layout, spaceLab, spaceText, pos);
        
        
    }
    
    public void addBouton(Container contentPane,Component text, Component lab, SpringLayout layout, int spaceLab, int spaceText, int pos){
    	    	
    	contentPane.add(lab);
            	
        contentPane.add(text);

        layout.putConstraint(SpringLayout.WEST, lab,
                150,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, lab,
                pos,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, text,
                spaceText,
                SpringLayout.EAST, lab);
        layout.putConstraint(SpringLayout.NORTH, text,
                pos,
                SpringLayout.NORTH, contentPane);
    }
    
    public void addElement(Container contentPane,Component text, Component lab, SpringLayout layout, int spaceLab, int spaceText, int pos){
    	Dimension dimension = new Dimension(150,20);
    	
    	contentPane.add(lab);
        
    	lab.setMaximumSize(dimension);
        lab.setMinimumSize(dimension);
        lab.setPreferredSize(dimension);
    	
        contentPane.add(text);

        layout.putConstraint(SpringLayout.WEST, lab,
                spaceLab,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, lab,
                pos,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, text,
                spaceText,
                SpringLayout.EAST, lab);
        layout.putConstraint(SpringLayout.NORTH, text,
                pos,
                SpringLayout.NORTH, contentPane);
    }
}
