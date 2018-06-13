package bzh.gsbrh.vues;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Entete;
import bzh.gsbrh.modeles.Images;
import bzh.gsbrh.modeles.formDate;
import bzh.gsbrh.observateurs.Lexique;

/**
 * Classe de modèle personnalisée
 * 
 * @author Anthony Nizac
 * @version 1.0
 */
public class ZModel extends AbstractTableModel {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = -3163109800641869333L;

	/**
	 * Tableau des données à intégrer dans le modèle
	 */
	public List<Employe> data = new ArrayList<Employe>();

	/**
	 * Entête des colonnes à intégrer dans le model
	 */
	private Entete title;
	public ZModel() {
		super();
	}
	
	/**
	 * Constructeur surchargé
	 * 
	 * @param data
	 *            Tableau des données à intégrer dans le modèle
	 * @param entete
	 *            Entête des colonnes à intégrer dans le model
	 */
	public ZModel(List<Employe> data, Entete entete) {
		super();

		this.data = data;

		this.title = entete;

	}

	/**
	 * Mutateur du tableau des données à intégrer dans le modèle
	 * 
	 * @param data
	 *            Nouveau tableau des données
	 */
	public void setData(List<Employe> data) {
		this.data = data;
	}

	/**
	 * Met à jour l'affichage des données
	 */
	public void modifierEmploye() {
		this.fireTableDataChanged();
	}

	/**
	 * Accesseur du nombre de colonnes du tableau de données
	 */
	public int getColumnCount() {

		return this.title.getEntete().length;

	}

	/**
	 * Accesseur du nombre de ligne du tableau de données
	 */
	public int getRowCount() {

		return this.data.size();

	}

	/**
	 * Accesseur de la valeur du tableau à la position recherché
	 * 
	 * @param row
	 *            Indice de la ligne
	 * @param col
	 *            Indice de la colonne
	 * @return La valeur à la position recherché
	 */
	public Object getValueAt(int row, int col) {
		System.out.println(this.data.get(row).getInfos(Lexique.SERVICE).getValeur());
		if (this.title.getEntete()[col].equals(Lexique.COLMODIFIE)) {
			Bouton modifier = FactBouton.fabriqueBouton(null, FactBouton.BO_MODIF, Images.MODIFIE.getIcon());
			modifier.setToolTipText(Lexique.BOUTON_MODIF + " "
					+ this.data.get(row).getInfos(title.indiceDEntete(Lexique.PRENOM)).getValeur() + " "
					+ this.data.get(row).getInfos(title.indiceDEntete(Lexique.NOM)).getValeur());
			return modifier;

		} else if (this.title.getEntete()[col].equals(Lexique.CONSULTER)) {
			Bouton afficher = FactBouton.fabriqueBouton(null, Lexique.BO_CONSULT, Images.MODIFIE.getIcon());
			afficher.setToolTipText(
					"Afficher " + this.data.get(row).getInfos(title.indiceDEntete(Lexique.PRENOM)).getValeur() + " "
							+ this.data.get(row).getInfos(title.indiceDEntete(Lexique.NOM)).getValeur());
			return afficher;

		} else if (this.title.getEntete()[col].equals(Lexique.PP_DATE_TX))
			return FactBouton.fabriqueBouton(null, FactBouton.BO_SUPPR, Images.PROGRAM.getIcon());
		else if (this.title.getEntete()[col].equals(Lexique.DATEE) || this.title.getEntete()[col].equals(Lexique.DATED)) {
			if(formDate.estAffichable(this.data.get(row).getInfos(title.getEntete()[col]).getValeur()))
				return this.data.get(row).getInfos(title.getEntete()[col]).getValeur();
			else
				return formDate.dateAffichable(this.data.get(row).getInfos(title.getEntete()[col]).getValeur());
		}
		else if (this.title.getEntete()[col].equals(Lexique.SERVICE)) {
			return title.getLesServices()[Integer.parseInt(this.data.get(row).getInfos(Lexique.SERVICE).getValeur())];
		} else
			return this.data.get(row).getInfos(title.getEntete()[col]).getValeur();

	}

	/**
	 * Retourne la classe de la donnée de la colonne
	 */
	public Class<? extends Object> getColumnClass(int col) {
		// On retourne le type de la cellule à la colonne demandée

		// On se moque de la ligne puisque les types de données sont les mêmes quelle
		// que soit la ligne
		if (this.title.getEntete()[col].equals(Lexique.COLMODIFIE)
				|| this.title.getEntete()[col].equals(Lexique.PP_DATE_TX)
				|| this.title.getEntete()[col].equals(Lexique.CONSULTER))
			return Bouton.class;
		if (this.title.getEntete()[col].equals("check"))
			return Boolean.class;
		// On choisit donc la première ligne
		return this.data.get(0).getInfos(title.getEntete()[col]).getClass();
	}

	/**
	 * Retourne vrai si la cellule est éditable
	 */
	public boolean isCellEditable(int row, int col) {

		// On appelle la méthode getValueAt qui retourne la valeur d'une cellule

		// Et on effectue un traitement spécifique si c'est un JButton

		if (getValueAt(0, col) instanceof Bouton)

			return false;
		return true;

	}

	/**
	 * Retourne le titre de la colonne à l'indice spécifié
	 */
	public String getColumnName(int col) {

		return this.title.getEntete()[col];

	}
}
