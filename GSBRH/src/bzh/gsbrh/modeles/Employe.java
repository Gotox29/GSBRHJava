package bzh.gsbrh.modeles;

import java.util.Hashtable;

/**
 * Classe permettant d'instancier un employé.
 * 
 * @author Anthony Nizac
 * @version 1.1
 */
public class Employe {

	/**
	 * Collection des informations de l'employé.
	 * 
	 * @see Information
	 */
	private Hashtable<Integer, Information> infos = new Hashtable<Integer, Information>();

	public Employe() {
		for (int i = 0; i < Information.getTypes().length; i++) {
			infos.put(i, new Information(Information.getTypes(i), ""));
		}
	}

	/**
	 * Accesseur de la collection d'information.
	 * 
	 * @return La collection d'information de l'employé.
	 */
	public Hashtable<Integer, Information> getInfos() {
		return infos;
	}

	/**
	 * Accesseur d'un élément de la collection d'information.
	 * 
	 * @param i
	 *            Indice de l'élément demandé.
	 * @return L'information demandé sur l'employé
	 */
	public Information getInfos(int i) {
		return getInfos().get(i);
	}

	/**
	 * Accesseur d'un élément de la collection d'information.
	 * 
	 * @param i
	 *            Type de l'information demandé
	 * @return L'information demandé sur l'employé
	 */
	public Information getInfos(String i) {
		return getInfos().get(Information.getByType(i));
	}

	/**
	 * Mutateur de la collection d'information de l'employé.
	 * 
	 * @param infos
	 *            Collection d'informations remplaçant celui existant.
	 */
	public void setInfos(Hashtable<Integer, Information> infos) {
		this.infos = infos;

	}

}
