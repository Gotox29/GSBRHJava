package bzh.gsbrh.vues;

import bzh.gsbrh.modeles.Entete;
import bzh.gsbrh.observateurs.Lexique;

public class Model extends ZModel {

	public Object[][] data;

	private Entete title;
	
	public Model(Object[][] data, Entete entete) {
		super();
		this.data = data;

		this.title = entete;
	}

	@Override
	public int getColumnCount() {
		return title.getEntete().length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
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
		System.out.println(data[0][col]);
		// On choisit donc la première ligne
		return data[0][col].getClass();
	}
	
	public boolean isCellEditable(int row, int col) {

		// On appelle la méthode getValueAt qui retourne la valeur d'une cellule

		// Et on effectue un traitement spécifique si c'est un JButton
		if (getValueAt(0, col) instanceof Bouton)

			return false;
		return true;

	}
	
	public String getColumnName(int col) {

		return this.title.getEntete()[col];

	}

}
