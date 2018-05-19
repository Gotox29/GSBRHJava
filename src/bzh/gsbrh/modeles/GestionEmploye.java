package bzh.gsbrh.modeles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import bzh.gsbrh.observateurs.Lexique;

public class GestionEmploye extends Lexique{

	private ArrayList<Employe> listeEmployes;
	private Object [][] liste;
	private Object [][] listeI;     
	private String entete[] = {Lexique.ID, Lexique.NOM, Lexique.PRENOM, Lexique.LOGIN, Lexique.ADR,
			Lexique.CP, Lexique.VILLE, Lexique.MAIL, Lexique.TEL, Lexique.DATEE, Lexique.SERVICE, Lexique.DATED, "", Lexique.PP_DATE_TX };
	private String lesServices[];
	private int nbEmployes = 0;
	private int rowActif = 0;
	private int rowInactif = 0;
	private int codeComp = Lexique.M_NOM;
	private boolean inverse = false;

	public GestionEmploye(ArrayList<Employe> lesEmployes, String [] services){
		listeEmployes = lesEmployes;
		lesServices = services;
		importerListe();
	}
	
	public int getActif(){
		return rowActif;
	}
	
	public int getInactif(){
		return rowInactif;
	}
	
	private void importerListe(){
		rowActif = rowInactif = 0;
		for(Employe unEmploye : listeEmployes){
			if(formDate.dateDepasse(unEmploye.getDateD()) && !unEmploye.getDateD().isEmpty())
				rowInactif++;
			else
				rowActif++;
		}
		nbEmployes = listeEmployes.size();
		genererListe();
	}

	public void genererListe(){
		liste = new Object[rowActif][14];
		listeI = new Object[rowInactif][14];
		int i = 0;
		int a = 0;
		//  Boucle qui génère les employés un à un 
		System.out.println("Actif = "+rowActif+"; Inactif = "+rowInactif+"; total = "+ nbEmployes);
		for(int t = 0; t < listeEmployes.size(); t++){
			if(formDate.dateDepasse(listeEmployes.get(t).getDateD()) && !listeEmployes.get(t).getDateD().isEmpty()){
				listeI[i][0] =  listeEmployes.get(t).getId();
				listeI[i][1] =  listeEmployes.get(t).getNom();
				listeI[i][2] =  listeEmployes.get(t).getPrenom();
				listeI[i][3] =  listeEmployes.get(t).getLogin();
				listeI[i][4] =  listeEmployes.get(t).getAdresse();
				listeI[i][5] =  listeEmployes.get(t).getCodePostal();
				listeI[i][6] =  listeEmployes.get(t).getVille();
				listeI[i][7] =  listeEmployes.get(t).getMail();
				listeI[i][8] =  listeEmployes.get(t).getTelephone();
				listeI[i][9] =  formDate.dateAffichable(listeEmployes.get(t).getDateE());
				listeI[i][10] =  lesServices[listeEmployes.get(t).getServiceId()];
				listeI[i][11] =  formDate.dateAffichable(listeEmployes.get(t).getDateD());
				i++;
			}else{
				liste[a][0] =  listeEmployes.get(t).getId();
				liste[a][1] =  listeEmployes.get(t).getNom();
				liste[a][2] =  listeEmployes.get(t).getPrenom();
				liste[a][3] =  listeEmployes.get(t).getLogin();
				liste[a][4] =  listeEmployes.get(t).getAdresse();
				liste[a][5] =  listeEmployes.get(t).getCodePostal();
				liste[a][6] =  listeEmployes.get(t).getVille();
				liste[a][7] =  listeEmployes.get(t).getMail();
				liste[a][8] =  listeEmployes.get(t).getTelephone();
				liste[a][9] =  formDate.dateAffichable(listeEmployes.get(t).getDateE());
				liste[a][10] =  lesServices[listeEmployes.get(t).getServiceId()];
				liste[a][11] =  formDate.dateAffichable(listeEmployes.get(t).getDateD());
				a++;
			}
		}
		liste = lancerTri(liste,codeComp,inverse);
		listeI = lancerTri(listeI,codeComp,inverse);
	}
	
	public Object[][] lancerTri(Object[][] liste, int codeComp, boolean inverse){
		liste = triRapide(liste, codeComp, inverse);
		return liste;
	}

	public Object[][] triRapide(Object[][] liste, int codeComp,boolean inverse){
		int longueur = liste.length;
		liste = triRapide(liste,0,longueur-1, codeComp, inverse);
		return liste;
	}

	public Object[][] triRapide(Object[][] liste, int debut, int fin, int codeComp, boolean inverse){
		if(debut<fin){
			int positionPivot = partition(liste,debut,fin,codeComp, inverse);
			liste = triRapide(liste,debut,positionPivot - 1, codeComp,inverse);
			liste = triRapide(liste,positionPivot+1,fin, codeComp, inverse);
		}
		return liste;
	}

	public int partition(Object[][] liste, int debut, int fin, int codeComp, boolean inverse){
		int compt = debut;
		Object pivot = ((String)liste[debut][codeComp]).toLowerCase();
		for(int i = debut+1; i <= fin; i++){
			if(inverse){
				if((((String) liste[i][codeComp]).toLowerCase()).compareTo((String) pivot) > 0){
					compt++;
					echanger(liste, compt, i);
				}
			}else{
				if(-(((String) liste[i][codeComp]).toLowerCase()).compareTo((String) pivot) > 0){
					compt++;
					echanger(liste, compt, i);
				}
			}
		}
		echanger(liste, debut, compt);
		return compt;
	}

	public Object[][] echanger(Object[][]liste, int a, int b){
		Object[][]temp = new Object[1][14];
		temp[0] = liste[b];
		liste[b] = liste[a];
		liste[a] = temp[0];
		return liste;
	}

	public void ajouterEmploye(Employe unNouvelEmploye){
		Employe employe = getEmploye(unNouvelEmploye.getId());
		if(employe != null){	// Si l'employé éxiste dans l'ArrayList
			int indice = 0;	//	Son indice dans l'un des 2 tableaux
			int index = getEmployeIndex(unNouvelEmploye.getId());	// Son index dans l'ArrayList
			int[] codesModif = comparerEmploye(unNouvelEmploye, listeEmployes.get(index));	//	Le programme cherche les modifs à effectué dans l'ArrayList
			boolean avant = formDate.dateDepasse(listeEmployes.get(index).getDateD());	//	Le programme test si l'ancienne date de départ est dépassé
			boolean apres = formDate.dateDepasse(unNouvelEmploye.getDateD());	//	Le programme test si la nouvelle date de départ est dépassé
			System.out.println("Avant = " + avant + "; Apres = "+apres+";"); 
			if((avant && apres) || (!avant && !apres)){	//	Le programme test qu'il n'y a pas besoin de changer de liste à l'employé
				if(avant && apres){	//	Si les dates de départ sont dépassé le programme met à jour l'employé dans la liste des inactifs
					indice = getIndexListeI(unNouvelEmploye.getId());
					listeI = modifier(unNouvelEmploye, codesModif, listeI, index, indice);
					System.out.println("L'employé " + unNouvelEmploye.getId() +" n'a pas changé de liste, il reste inactif");
				}else{	//	Sinon, les dates ne sont pas dépassé, le programme met à jour l'employé dans la liste des actifs
					indice = getIndexListeA(unNouvelEmploye.getId());
					liste = modifier(unNouvelEmploye, codesModif, liste, index, indice);
					System.out.println("L'employé " + unNouvelEmploye.getId() +" n'a pas changé de liste, il reste actif");
				}
			}else{
				listeEmployes.set(index, unNouvelEmploye);
				if(avant && !apres){	//	Si l'employé est passé d'inactif à actif on le met à jour dans l'ArrayListe
					System.out.println("L'employé " + unNouvelEmploye.getId() +" a changé de liste, il est maintenant actif");
					rowActif++;
					rowInactif--;
				}else{	//	Si l'employé est passé d'actif à inactif on le met à jour dans l'ArrayListe
					System.out.println("L'employé " + unNouvelEmploye.getId() +" a changé de liste, il est maintenant inactif");
					rowActif--;
					rowInactif++;
				}
				genererListe();
			}
		}else{
			System.out.println(unNouvelEmploye.getId() +" est un nouvel employé, il est maintenant actif");
			listeEmployes.add(unNouvelEmploye);
			rowActif++;
			nbEmployes++;
			genererListe();
		}
	}

	public Object[][] modifier(Employe unNouvelEmploye, int [] codesModif, Object[][]uneListe, int index, int indice){
		for(int i = 0; i < codesModif.length; i++){
			switch(codesModif[i]){
			case M_NOM:
				listeEmployes.get(index).setNom(unNouvelEmploye.getNom());
				uneListe[indice][1] = unNouvelEmploye.getNom();
				break;
			case M_PRENOM:
				listeEmployes.get(index).setPrenom(unNouvelEmploye.getPrenom());
				uneListe[indice][2] = unNouvelEmploye.getPrenom();
				break;
			case M_LOGIN:
				listeEmployes.get(index).setLogin(unNouvelEmploye.getLogin());
				uneListe[indice][3] = unNouvelEmploye.getLogin();
				break;
			case M_MDP:
				listeEmployes.get(index).setMotDePasse(unNouvelEmploye.getMotDePasse());
				break;
			case M_ADRESSE:
				listeEmployes.get(index).setAdresse(unNouvelEmploye.getAdresse());
				uneListe[indice][4] = unNouvelEmploye.getAdresse();
				break;
			case M_CP:
				listeEmployes.get(index).setCodePostal(unNouvelEmploye.getCodePostal());
				uneListe[indice][5] = unNouvelEmploye.getCodePostal();
				break;
			case M_VILLE:
				listeEmployes.get(index).setVille(unNouvelEmploye.getVille());
				uneListe[indice][6] = unNouvelEmploye.getVille();
				break;
			case M_MAIL:
				listeEmployes.get(index).setMail(unNouvelEmploye.getMail());
				uneListe[indice][7] = unNouvelEmploye.getMail();
				break;
			case M_TEL:
				listeEmployes.get(index).setTelephone(unNouvelEmploye.getTelephone());
				uneListe[indice][8] = unNouvelEmploye.getTelephone();
				break;
			case M_DATEE:
				listeEmployes.get(index).setDateE(unNouvelEmploye.getDateE());
				uneListe[indice][9] = formDate.dateAffichable(unNouvelEmploye.getDateE());
				break;
			case M_SERVICEID:
				listeEmployes.get(index).setServiceId(unNouvelEmploye.getServiceId());
				uneListe[indice][10] = lesServices[unNouvelEmploye.getServiceId()];
				break;
			case M_DATED:
				listeEmployes.get(index).setDateD(unNouvelEmploye.getDateD());
				uneListe[indice][11] = formDate.dateAffichable(unNouvelEmploye.getDateD());
				break;
			default:
				break;
			}
		}
		return uneListe;
	}

	public String[] getEntete(){
		return entete;
	}

	public Object [][] getListeI(){
		return listeI;
	}
	
	public Object [][] getListe(){
		return liste;
	}

	public Employe getEmploye(String id){
		for(Employe unEmploye : listeEmployes){
			if(unEmploye.getId().equals(id))
				return unEmploye;
		}
		return null;
	}

	public int getEmployeIndex(String id){
		for(int i = 0; i < listeEmployes.size(); i++){
			if(listeEmployes.get(i).getId().equals(id))
				return i;
		}
		return -1;
	}

	public int getIndexListeA(String id){
		int size = liste.length;
		int index = 0;
		for(int i = 0; i < size; i++){
			if(liste[i][0].equals(id)){
				index = i;
			}
		}
		return index;
	}

	public int getIndexListeI(String id){
		int size = listeI.length;
		int index = 0;
		for(int i = 0; i < size; i++){
			if(listeI[i][0].equals(id)){
				index = i;
			}
		}
		return index;
	}

	public int[] comparerEmploye(Employe employeA, Employe employeB){
		int[] codeModif = new int[12];
		int i = 0;
		int row = 0;
		for(i = 0; i < codeModif.length; i++){
			codeModif[i] = -1;
		}
		if(employeA.getNom().compareTo(employeB.getNom()) != 0){
			codeModif[0] = M_NOM;
			row++;
		}
		if(employeA.getPrenom().compareTo(employeB.getPrenom()) != 0){
			codeModif[1] = M_PRENOM;
			row++;
		}
		if(employeA.getLogin().compareTo(employeB.getLogin()) != 0){
			codeModif[2] = M_LOGIN;
			row++;
		}
		if(employeA.getMotDePasse().compareTo(employeB.getMotDePasse()) != 0){
			codeModif[3] = M_MDP;
			row++;
		}
		if(employeA.getAdresse().compareTo(employeB.getAdresse()) != 0){
			codeModif[4] = M_ADRESSE;
			row++;
		}
		if(employeA.getCodePostal().compareTo(employeB.getCodePostal()) != 0){
			codeModif[5] = M_CP;
			row++;
		}
		if(employeA.getVille().compareTo(employeB.getVille()) != 0){
			codeModif[6] = M_VILLE;
			row++;
		}
		if(employeA.getMail().compareTo(employeB.getMail()) != 0){
			codeModif[7] = M_MAIL;
			row++;
		}
		if(employeA.getTelephone().compareTo(employeB.getTelephone()) != 0){
			codeModif[8] = M_TEL;
			row++;
		}
		if(employeA.getDateE().compareTo(employeB.getDateE()) != 0){
			codeModif[9] = M_DATEE;
			row++;
		}
		if(employeA.getDateD().compareTo(employeB.getDateD()) != 0){
			codeModif[10] = M_DATED;
			row++;
		}
		if(employeA.getServiceId() != employeB.getServiceId()){
			codeModif[11] = M_SERVICEID;
			row++;
		}
		int[] retour = new int[row];
		row = 0;
		for(i = 0; i < codeModif.length; i++){
			if(codeModif[i] != -1){
				retour[row] = codeModif[i];
				row++;
			}
		}
		return retour;
	}

	public boolean idExiste(String id) {
		boolean flag = false;
		if(employeExiste(id, Lexique.M_ID)){
			flag = true;
		}
		return flag;
	}

	public String formaterId(String id){
		if(idExiste(id))
			id = formaterId(id, 1);
		return id;
	}

	public String formaterId(String id, int i){
		int temp = Integer.parseInt(id) + i;
		i+=1;
		if(idExiste(Integer.toString(temp)))
			temp = Integer.parseInt(formaterId(id, i));
		return Integer.toString(temp);
	}

	public boolean logExiste(String log) {
		boolean flag = false;
		log = log.toLowerCase();
		if(employeExiste(log, Lexique.M_LOGIN)){
			flag = true;
		}
		return flag;
	}

	public String formaterLog(String log){
		log = log.toLowerCase();
		if(logExiste(log))
			log = formaterLog(log, 1);
		return log;
	}

	public String formaterLog(String log, int i){
		String temp = (log + i);
		i+=1;
		if(logExiste(temp))
			temp = formaterLog(log, i);
		return temp;
	}

	public boolean verifId(String id, Employe unEmploye) {
		boolean flag = false;
		if((id.length() > 0 && id.length() <= 4) && !idExiste(id)  || id.equals(unEmploye.getId())){
			flag = true;
		}
		System.out.println("Verif id = "+ id + " = "+ flag);
		return flag;
	}

	public boolean verifText(String text){
		boolean flag = false;
		if(text.length() <= 30){
			flag = true;
		}
		System.out.println("Verif Text = "+ text + " = "+ flag);
		return flag;
	}
	
	public boolean verifTel(String text){
		boolean flag = false;
		if(!text.isEmpty() && text.length() == 10){
			flag = true;
		}
		System.out.println("Verif Tel = "+ text + " = "+ flag);
		return flag;
	}

	public boolean verifMdp(String mdp){
		boolean flag = false;
		if(mdp.length() <= 20 && mdp.length() > 6){
			flag = true;
		}
		System.out.println("Verif mdp = "+ mdp + " = "+ flag);
		return flag;
	}

	public boolean verifLogin(String login, Employe unEmploye){
		boolean flag = false;
		if((login.length() <= 20 && login.length() > 5) && (!logExiste(login) || login.equals(unEmploye.getLogin()))){
			flag = true;
		}
		System.out.println("Verif Login = "+ login + " = "+ flag);
		return flag;
	}

	public boolean verifCP(String cp){
		boolean flag = false;
		if(cp.length() == 5){
			flag = true;
		}
		System.out.println("Verif CP = "+ cp + " = "+ flag);
		return flag;
	}
	
	public boolean verifDate(String date) {
		boolean flag = false;
		System.out.println(date);
		String [] dates = date.split("-");
		if(dates.length < 3)
			return false;
		int jours = Integer.parseInt(dates[2]);
		int mois = Integer.parseInt(dates[1]);
		int annees = Integer.parseInt(dates[0]);
		try{
			LocalDate temp = LocalDate.of(annees, mois, jours);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	
	public boolean employeExiste(String valeur, int code){
		for(Employe unEmploye : listeEmployes){
			switch(code){
				case Lexique.M_LOGIN:
					if(unEmploye.getLogin().equals(valeur))
						return true;
					break;
				case Lexique.M_ID:
					if(unEmploye.getId().equals(valeur))
						return true;
					break;
				default:
					return false;
			}
		}
		return false;
	}
}