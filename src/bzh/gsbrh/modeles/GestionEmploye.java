package bzh.gsbrh.modeles;

import java.util.ArrayList;

import bzh.gsbrh.observateurs.Lexique;

public class GestionEmploye extends Lexique{
	private ArrayList<Employe> listeEmployes;
	private Object [][] liste;
	private String entete[] = {"Code", "Nom", "Prenom", "Login", "Adresse", "Code Postal", "ville", "Date d'embauche", "Service", "Date depart", "", "" };
	private int nbEmployes = 0;


	public GestionEmploye(){
		listeEmployes = new ArrayList<Employe>();
		importerListe();
	}

	private void importerListe(){
		listeEmployes = Requetes.listerEmployer();
		nbEmployes = listeEmployes.size();
	}
	
	public String[] getEntete(){
		return entete;
		
	}

	public Object [][] arrayVersList(){
		int row = listeEmployes.size();
		liste = new Object[row][12];
		int i = 0;
		//  Boucle qui génère les employés un à un 
		for(Employe unEmploye : listeEmployes){
			liste[i][0] =  unEmploye.getId();
			liste[i][1] =  unEmploye.getNom();
			liste[i][2] =  unEmploye.getPrenom();
			liste[i][3] =  unEmploye.getLogin();
			liste[i][4] =  unEmploye.getAdresse();
			liste[i][5] =  unEmploye.getCodePostal();
			liste[i][6] =  unEmploye.getVille();
			liste[i][7] =  unEmploye.getDateE();
			liste[i][8] =  Requetes.trouverService(unEmploye.getServiceId());
			liste[i][9] =  unEmploye.getDateD();
			i++;
		}
		return liste;
	}

	public Employe getEmploye(String id){
		for(Employe unEmploye : listeEmployes){
			if(unEmploye.getId().equals(id))
				return unEmploye;
		}

		return null;
	}

	public void ajouterEmploye(Employe employe){
		if(getEmploye(employe.getId()) != null){
			listeEmployes.add(employe);
			nbEmployes++;
		}
	}

	public void modifierEmploye(String id, int i, String valeur){
		Employe unEmploye = getEmploye(id);
		switch(i){
		case M_NOM:
			unEmploye.setNom(valeur);
			break;
		case M_PRENOM:
			unEmploye.setPrenom(valeur);
			break;
		case M_LOGIN:
			unEmploye.setLogin(valeur);
			break;
		case M_MDP:
			unEmploye.setMotDePasse(valeur);
			break;
		case M_ADRESSE:
			unEmploye.setAdresse(valeur);
			break;
		case M_CP:
			unEmploye.setCodePostal(valeur);
			break;
		case M_VILLE:
			unEmploye.setVille(valeur);
			break;
		case M_DATEE:
			unEmploye.setDateE(valeur);
			break;
		case M_SERVICEID:
			unEmploye.setServiceId(Integer.parseInt(valeur));
			break;
		case M_DATED:
			unEmploye.setDateD(valeur);
			break;

		default:
			break;
		}
	}

}
