package Controleurs;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Modeles.Employe;
import Modeles.Requetes;
import Vues.Bouton;
import Vues.Fenetre;
import Vues.Formulaire;
import Vues.Message;


public class Controleur implements ActionListener {

	String[] services;
	Hashtable <Integer, Component> champs;
	Employe unEmploye;
	
	public Controleur() throws ClassNotFoundException, SQLException, ParseException {
		
	}

	public void lancerAppli() throws ClassNotFoundException, SQLException, ParseException {
		lancerModifEmploye(Requetes.trouverEmploye("1"));
	}

	public void lancerListeEmploye() throws ClassNotFoundException, SQLException, ParseException{
		Object liste [][] = employesEnObjet(Requetes.listerEmployer());
		Fenetre F = new Fenetre("Lister les employés", liste);
		F.setVisible(true);
	}
	public void lancerAjoutEmploye() throws ClassNotFoundException, SQLException, ParseException{
		this.services = servicesEnObjet(Requetes.listerServices());
		unEmploye = new Employe();
		this.champs = genererChamps();
		Formulaire F = new Formulaire("Ajouter un employé", this.champs);
		F.setVisible(true);
	}
	
	public void lancerModifEmploye(Employe employe) throws ClassNotFoundException, SQLException, ParseException{
		this.services = servicesEnObjet(Requetes.listerServices());
		unEmploye = employe;
		this.champs = genererChamps();
		Formulaire F = new Formulaire("Modifier un employé", this.champs);
		F.setVisible(true);
	}

	public void verifierLogin(String login, String mdp) {
	}

	public void ajouterEmploye(Employe employe) {
	}

	public void afficherEmploye(Employe employe) {
	}

	public void listerServices() {
	}

	public void modifierEmploye() {
	}

	/**
	 * Transforme un hashtable d'employé en temployé d'employé a 2 dimention
	 * 
	 * @param <hastable> employé
	 * @return tableau d'enployés pour l'affichage de la liste des employés 
	 */
	public Object [][] employesEnObjet(Hashtable<String,Employe> employes) throws ClassNotFoundException, SQLException {
		int row = employes.size();
		int col = 12;

		Object liste[][] = new Object[row][col]; 

		String key;
		Enumeration<String> keys = employes.keys();

		String service = null;
		
		for(int i = 0; i<row;i++){
			key = (String) keys.nextElement();
			Bouton modifier = new Bouton("Modifier", employes.get(key).getId());
			//JButton modifier = new JButton("Modifier");
			modifier.addActionListener(this);
			JButton supprimer = new JButton();
			supprimer.setText("Supprimer");

			service = Requetes.trouverService(employes.get(key).getServiceId());

			liste[i][0] =  employes.get(key).getId();
			liste[i][1] =  employes.get(key).getNom();
			liste[i][2] =  employes.get(key).getPrenom();
			liste[i][3] =  employes.get(key).getLogin();
			liste[i][4] =  employes.get(key).getAdresse();
			liste[i][5] =  employes.get(key).getCodePostal();
			liste[i][6] =  employes.get(key).getVille();
			liste[i][7] =  employes.get(key).getDateE();
			liste[i][8] =  service;
			liste[i][9] =  employes.get(key).getDateD();
			liste[i][10] =  modifier;
			liste[i][11] =  supprimer;
		}

		return liste;
	}

	public TableModel toTableModel(Hashtable<String,Employe> employes) {

		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "id", "nom", "prenom", "login", "adresse", "cp", "ville", "dateEmbauche", "service_id", "dateDepart", "Modifier", "Supprimer" }, 0
				);

		String key;
		Enumeration<String> keys = employes.keys();
		while (keys.hasMoreElements()){
			key = (String) keys.nextElement();
			JButton modifier = new JButton("Modifier");
			
			JButton supprimer = new JButton();
			supprimer.setText("Supprimer");
			//				System.out.println(key + " = " + employes.get(key).getNom() );
			model.addRow(new Object[] { employes.get(key).getId(),
					employes.get(key).getNom(),
					employes.get(key).getPrenom(),
					employes.get(key).getLogin(),
					employes.get(key).getAdresse(),
					employes.get(key).getCodePostal(),
					employes.get(key).getVille(),
					employes.get(key).getDateE(),
					employes.get(key).getServiceId(),
					employes.get(key).getDateD(),
					modifier,
					supprimer});

		}
		return model;
	}

	public String[] servicesEnObjet(Hashtable<Integer, String> services) throws ClassNotFoundException, SQLException {
		int row = services.size();

		String liste[] = new String[row]; 

		int key;
		Enumeration<Integer> keys = services.keys();
		for(int i = 0; i<row;i++){
			key = (Integer) keys.nextElement();
			liste[i] =  services.get(key);
		}


		return liste;
	}
	
	public void appliModifier(Employe employe){
		unEmploye = employe;
		this.champs = genererChamps();
	}
	
	public void appliAjouter(){
		
	}

	public Hashtable <Integer, Component> genererChamps(){
		Hashtable <Integer, Component> champs = new Hashtable <Integer, Component>();
		
		Employe employe = unEmploye;
		
		JTextField idText = new JTextField(employe.getId(), 15);
		champs.put(1, idText);
		if(employe.getId() == null){
			
		}

		JTextField nomText = new JTextField(employe.getNom(), 15);
		champs.put(2, nomText);

		JTextField prenomText = new JTextField(employe.getPrenom(), 15);
		champs.put(3,prenomText);

		JTextField loginText = new JTextField(employe.getLogin(), 15);
		champs.put(4,loginText);

		JTextField mdpText = new JTextField(employe.getMotDePasse(), 15);
		champs.put(5,mdpText);

		JTextField adrText = new JTextField(employe.getAdresse(), 15);
		champs.put(6,adrText);

		JTextField cPText = new JTextField(employe.getCodePostal(), 15);
		champs.put(7,cPText);

		JTextField villeText = new JTextField(employe.getVille(), 15);
		champs.put(8,villeText);

		JTextField dateEText = new JTextField(employe.getDateE(), 15);
		champs.put(9,dateEText);

		String[] servList = this.services;
		JComboBox<Object> servListe = new JComboBox<Object>(servList);
		servListe.setSelectedIndex(employe.getServiceId());
		champs.put(10,servListe);

		JButton valider = new JButton();
		valider.setText("Ajouter");
		valider.addActionListener(this);
		champs.put(11, valider);

		JButton annuler = new JButton();
		annuler.setText("Annuler");
		annuler.addActionListener(this);
		champs.put(12, annuler);

		return champs;
	}

	private boolean verifId(String id) throws ClassNotFoundException{
		boolean flag = false;
		String compar = Requetes.trouverEmploye(id).getId();
		if(!id.isEmpty() && id.length() <= 3 && compar == null){
			flag = true;
		}
		return flag;
	}

	private boolean verifText(String text){
		boolean flag = false;
		if(!text.isEmpty() && text.length() <= 30){
			flag = true;
		}
		return flag;
	}
	
	private boolean verifMdp(String mdp){
		boolean flag = false;
		if(!mdp.isEmpty() && mdp.length() <= 20){
			flag = true;
		}
		return flag;
	}
	
	@SuppressWarnings("unused")
	private boolean verifLogin(String login){
		boolean flag = false;
		if(!login.isEmpty() && login.length() <= 20){
			flag = true;
		}
		return flag;
	}
	
	private boolean verifCP(String cp){
		boolean flag = false;
		if(!cp.isEmpty() && cp.length() == 5){
			flag = true;
		}
		return flag;
	}
	
	public void ajouter() throws ClassNotFoundException{

		JTextField champ;
		JComboBox<?> liste;

		String id;
		String nom;
		String prenom;
		String login;
		String mdp;
		String adresse;
		String cp;
		String ville;
		String date;
		String service;
		champ = (JTextField) this.champs.get(1);
		id = champ.getText();
		champ = (JTextField) this.champs.get(2);
		nom = champ.getText();
		champ = (JTextField) this.champs.get(3);
		prenom = champ.getText();
		champ = (JTextField) this.champs.get(4);
		login = champ.getText();
		champ = (JTextField) this.champs.get(5);
		mdp = champ.getText();
		champ = (JTextField) this.champs.get(6);
		adresse = champ.getText();
		champ = (JTextField) this.champs.get(7);
		cp = champ.getText();
		champ = (JTextField) this.champs.get(8);
		ville = champ.getText();
		champ = (JTextField) this.champs.get(9);
		date = champ.getText();
		liste = (JComboBox<?>) this.champs.get(10);
		service = liste.getSelectedItem().toString();
		if(verifId(id) && verifText(nom) && verifText(prenom) && verifText(login) && verifMdp(mdp) && verifText(adresse) && verifCP(cp) && verifText(ville)){
			Employe employe = new Employe();
			employe.setId(id);
			employe.setNom(nom);
			employe.setPrenom(prenom);
			employe.setLogin(login);
			employe.setMotDePasse(mdp);
			employe.setAdresse(adresse);
			employe.setCodePostal(cp);
			employe.setVille(ville);
			employe.setDateE(date);
			try {
				employe.setServiceId(Requetes.trouverService(service));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			employe.setDateD(null);


			try {
				Requetes.ajouterEmploye(employe);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}else{
			Message erreur = new Message("Erreur", "Verifier les champs avant de valider.");
			erreur.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		JTextField champ;
		JComboBox<?> liste;
		switch(action){
		case "Ajouter":
			try {
				ajouter();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "Annuler":
			Employe employe = unEmploye;
			champ = (JTextField) this.champs.get(1);
			champ.setText(employe.getId());
			champ = (JTextField) this.champs.get(2);
			champ.setText(employe.getNom());
			champ = (JTextField) this.champs.get(3);
			champ.setText(employe.getPrenom());
			champ = (JTextField) this.champs.get(4);
			champ.setText(employe.getLogin());
			champ = (JTextField) this.champs.get(5);
			champ.setText(employe.getMotDePasse());
			champ = (JTextField) this.champs.get(6);
			champ.setText(employe.getAdresse());
			champ = (JTextField) this.champs.get(7);
			champ.setText(employe.getCodePostal());
			champ = (JTextField) this.champs.get(8);
			champ.setText(employe.getVille());
			champ = (JTextField) this.champs.get(9);
			champ.setText(employe.getDateE());
			liste = (JComboBox<?>) this.champs.get(10);
			liste.setSelectedIndex(employe.getServiceId());


			break;
		case "Modifier":
			System.out.println("aa");

		}
	}




}
