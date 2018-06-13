package bzh.gsbrh.modeles;

import bzh.gsbrh.observateurs.Lexique;

/**
 * Class définissant l'entête du tableau de la liste des employés
 * 
 * @author Anthony Nizac
 * @version 1.0
 */
public class Entete implements Lexique {

	/**
	 * Tableau des libellés de l'entête du tableau
	 */
	private String entete[] = { ID, NOM, PRENOM, LOGIN, ADR, CP, VILLE, MAIL, TEL, DATEE, SERVICE, DATED, COLMODIFIE,
			PP_DATE_TX };

	private String[] lesServices;
	
	/**
	 * Constructeur de l'entête
	 */
	public Entete() {

	}

	/**
	 * Retourne l'indice dans le tableau d'entête du libellé recherché
	 * 
	 * @param lib
	 *            Libellé recherché
	 * @return L'indice de libellé recherché
	 */
	public int indiceDEntete(String lib) {
		int retour = 0;
		for (int i = 0; i < entete.length; i++)
			if (entete[i].equals(lib))
				return i;
		return retour;
	}

	/**
	 * Vérifie que le libellé demandé est à l'indice demandé
	 * 
	 * @param lib
	 *            Libellé recherché
	 * @param indice
	 *            Indice ou trouver le libellé
	 * @return Bouléen qui confirme ou non que le libellé est bien à l'indice
	 *         demandé
	 */
	public boolean estALIndice(String lib, int indice) {
		for (int i = 0; i < entete.length; i++)
			if (entete[i].equals(lib) && i == indice)
				return true;
		return false;
	}

	/**
	 * Accesseur du tableau des libellés d'entêtes
	 * 
	 * @return Le tableau des libellés d'entête
	 */
	public String[] getEntete() {
		return entete;
	}

	/**
	 * Mutateur du tableau des libellés d'entêtes
	 * 
	 * @param entete
	 *            Nouveau tableau de libellé des entête
	 */
	public void setEntete(String[] entete) {
		this.entete = entete;
	}

	/**
	 * Recherche si un libellé est dans le tableau des libellés
	 * 
	 * @param lib
	 *            Libellé recherché
	 * @return Bouléen qui confirme ou non que le libellé est dans le tableau des
	 *         libellés
	 */
	public boolean estDansEntete(String lib) {
		for (int i = 0; i < this.entete.length; i++)
			if (this.entete[i].equals(lib))
				return true;
		return false;
	}

	public String[] getLesServices() {
		return lesServices;
	}

	public void setLesServices(String[] lesServices) {
		this.lesServices = lesServices;
	}
}
