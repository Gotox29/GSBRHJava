package bzh.gsbrh.modeles;

import java.util.ArrayList;

public class gestionEmploye {
	private ArrayList<Employe> listeEmployes;
//	private Object [][] liste;
	private int nbEmployes = 0;

	public gestionEmploye(){
		listeEmployes = new ArrayList<Employe>();
	}

//	public Object [][] getListe(){
//		int row = listeEmployes.size();
//		liste = new Object[row][11];
//		int i = 0;
//		//  Boucle qui génère les employés un à un 
//		for(Employe unEmploye : listeEmployes){
//			liste[i][1] =  unEmploye.getId();
//            liste[i][2] =  unEmploye.getNom();
//            liste[i][0] =  unEmploye.getPrenom();
//            liste[i][3] =  unEmploye.getLogin();
//            liste[i][4] =  unEmploye.getAdresse();
//            liste[i][5] =  unEmploye.getCodePostal();
//            liste[i][6] =  unEmploye.getVille();
//            liste[i][7] =  unEmploye.getDateE();
//            liste[i][8] =  unEmploye.getServiceId();
//		}
//		return liste;
//	}

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
		case 0:
			unEmploye.setNom(valeur);
			break;
		case 1:
			unEmploye.setPrenom(valeur);
			break;
		case 2:
			unEmploye.setLogin(valeur);
			break;
		case 3:
			unEmploye.setMotDePasse(valeur);
			break;
		case 4:
			unEmploye.setAdresse(valeur);
			break;
		case 5:
			unEmploye.setCodePostal(valeur);
			break;
		case 6:
			unEmploye.setVille(valeur);
			break;
		case 7:
			unEmploye.setDateE(valeur);
			break;
		case 8:
			unEmploye.setServiceId(Integer.parseInt(valeur));
			break;
		case 9:
			unEmploye.setDateD(valeur);
			break;

		default:
			break;
		}
	}

}
