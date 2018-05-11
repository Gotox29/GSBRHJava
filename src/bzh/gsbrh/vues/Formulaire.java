package bzh.gsbrh.vues;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import bzh.gsbrh.controleurs.Controleur;
import bzh.gsbrh.controleurs.Langues;
import bzh.gsbrh.modeles.Employe;

public class Formulaire extends JFrame implements ActionListener{

	private String titre;
	private Hashtable <Integer,Champ> lesChamps;
	private Hashtable<Integer, Hashtable> champs;
	private Employe unEmploye;
	private boolean modifier;
	public static Controleur cont;

	public Formulaire(Controleur controleur, String titre, Employe employe,boolean modifier){
		this.titre = titre;
		this.cont = controleur;
		unEmploye = employe;
		champs = genererChamps();
		lesChamps = new Hashtable <Integer,Champ>();
		this.modifier = modifier;
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
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);


	}

	public void iniComposants() {
		// Panneau principale de la fenêtre 
		//Set up the content pane.
		Container contentPane = this.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);

		// Creation et ajout des composant au panneau		
		JLabel intro = new JLabel(titre + " :");
		contentPane.add(intro);

		layout.putConstraint(SpringLayout.WEST, intro,
				50,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, intro,
				30,
				SpringLayout.NORTH, contentPane);

		int pos = 70; 
		int spaceLab = 60;
		int spaceText = 50;
		int space = 30;

		Champ ligne;

		for(int i = 1; i <= this.champs.size();i++){
			Hashtable<String, String> elements = champs.get(i);
			String label = null;
			String valeur = null;
			Enumeration<String> keys = elements.keys();
			JComboBox comp = null;
			while (keys.hasMoreElements()){
				label = keys.nextElement();

				valeur = elements.get(label);
			}

			if(label.substring(0, 4).equals("Serv")){				
				String[] servList = cont.getServices();
				comp = new JComboBox(servList);
				comp.setSelectedIndex(Integer.parseInt(valeur));

				ligne = new Champ(label, valeur, comp);
				addElement(contentPane, ligne.getComp(), ligne.getLabel(), layout, spaceLab, spaceText, pos);

			}else{
				ligne = new Champ(label, valeur);
				addElement(contentPane, ligne.getText(), ligne.getLabel(), layout, spaceLab, spaceText, pos);
				if(this.modifier && i == 1){
					ligne.bloquerChamp();
				}
			}
			lesChamps.put(i, ligne);
			pos += space;
		}
		pos += space;
		JButton envoyer;
		if(this.modifier){
			envoyer = new JButton("Modifier");
			envoyer.addActionListener(this);
		}else{
			envoyer = new JButton("Ajouter");
			envoyer.addActionListener(this);
		}
		JButton annuler = new JButton("Réinitialiser");
		annuler.addActionListener(this);

		addBouton(contentPane, annuler, envoyer, layout, spaceLab, spaceText, pos);

		// Ajout d'un bouton retour pour revenir a la liste des employes
		JButton retour = new JButton("Retour");
		retour.addActionListener(this);
		contentPane.add(retour);

		layout.putConstraint(SpringLayout.WEST, retour,
				5,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, retour,
				3,
				SpringLayout.NORTH, contentPane);

	}

	public void addPane(Container contentPane,Champ ligne, SpringLayout layout, int spaceLab, int spaceText, int pos){

		contentPane.add(ligne);

		layout.putConstraint(SpringLayout.WEST, ligne,
				150,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, ligne,
				pos,
				SpringLayout.NORTH, contentPane);


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
	public Hashtable <Integer, Hashtable> genererChamps(){
		Hashtable <Integer, Hashtable> champs = new Hashtable <Integer, Hashtable>();


		Employe employe = unEmploye;
		//	        Hashtable <String, String> ligne = new Hashtable <String, String>();
		//	        ligne = ;
		champs.put(1, ajouterALHashtable("Id :", employe.getId()));
		champs.put(2, ajouterALHashtable("Nom :", employe.getNom()));
		champs.put(3, ajouterALHashtable("Prenom :", employe.getPrenom()));
		champs.put(4, ajouterALHashtable("Login :", employe.getLogin()));
		champs.put(5, ajouterALHashtable("Mot de passe :", employe.getMotDePasse()));
		champs.put(6, ajouterALHashtable("Adresse :", employe.getAdresse()));
		champs.put(7, ajouterALHashtable("Code Postal :", employe.getCodePostal()));
		champs.put(8, ajouterALHashtable("Ville :", employe.getVille()));
		champs.put(9, ajouterALHashtable("Date d'embauche :", employe.getDateE()));
		champs.put(10, ajouterALHashtable("Service :", Integer.toString(employe.getServiceId())));

		return champs;
	}



	public void uploaderChamps(Employe employe){
		//Employe employe = unEmploye;
		lesChamps.get(1).setValeur(employe.getId());
		lesChamps.get(2).setValeur(employe.getNom());
		lesChamps.get(3).setValeur(employe.getPrenom());
		lesChamps.get(4).setValeur(employe.getLogin());
		lesChamps.get(5).setValeur(employe.getMotDePasse());
		lesChamps.get(6).setValeur(employe.getAdresse());
		lesChamps.get(7).setValeur(employe.getCodePostal());
		lesChamps.get(8).setValeur(employe.getVille());
		lesChamps.get(9).setValeur(employe.getDateE());
		lesChamps.get(10).setValeur(Integer.toString(employe.getServiceId()));
	}

	public Hashtable <String, String> ajouterALHashtable(String text, String value){
		Hashtable <String, String> ligne = new Hashtable <String, String>();
		if(value == null){
			value = "";
		}
		ligne.put(text, value);
		return ligne;
	}

	public void reinitialiser(){
		for(int i = 1; i <= lesChamps.size(); i++){
			lesChamps.get(i).initialiserSaisie();
		}
	}


	public Employe lireEmploye(){
		Employe employe = new Employe();
		employe.setId(lesChamps.get(1).getDansSaisie());
		employe.setNom(lesChamps.get(2).getDansSaisie());
		employe.setPrenom(lesChamps.get(3).getDansSaisie());
		employe.setLogin(lesChamps.get(4).getDansSaisie());
		employe.setMotDePasse(lesChamps.get(5).getDansSaisie());
		employe.setAdresse(lesChamps.get(6).getDansSaisie());
		employe.setCodePostal(lesChamps.get(7).getDansSaisie());
		employe.setVille(lesChamps.get(8).getDansSaisie());
		employe.setDateE(lesChamps.get(9).getDansSaisie());
		employe.setServiceId(Integer.parseInt(lesChamps.get(10).getValeur()));
		return employe;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String action = event.getActionCommand();
		Employe employe = new Employe(); 
		switch(action){
		case "Ajouter":
			employe = lireEmploye();
			cont.ajouter(employe);
			break;
		case "Modifier":
			employe = lireEmploye();
			boolean flag = false;

			flag = cont.modifier(employe);
			if(flag){
				uploaderChamps(employe);
			}

			break;
		case "Réinitialiser":
			reinitialiser();
			break;
		case "Retour":
			this.dispose();
			cont.afficherPrincipale();
			break;
		}

	}
}