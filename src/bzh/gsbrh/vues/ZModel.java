package bzh.gsbrh.vues;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

//Classe modèle personnalisée

public class ZModel extends AbstractTableModel{

	public Object[][] data;

	private String[] title;


	//Constructeur

	public ZModel(Object[][] data, String[] title){

		this.data = data;

		this.title = title;

	}


	//Retourne le nombre de colonnes

	public int getColumnCount() {

		return this.title.length;

	}


	//Retourne le nombre de lignes

	public int getRowCount() {

		return this.data.length;

	}


	//Retourne la valeur à l'emplacement spécifié

	public Object getValueAt(int row, int col) {

		return this.data[row][col];

	}            

	
	
	//Retourne la classe de la donnée de la colonne
	public Class getColumnClass(int col){
		//return null;

		//On retourne le type de la cellule à la colonne demandée

		//On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne

		//On choisit donc la première ligne

		return this.data[0][col].getClass();

	}

	//Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
	public boolean isCellEditable(int row, int col){

		//On appelle la méthode getValueAt qui retourne la valeur d'une cellule

		//Et on effectue un traitement spécifique si c'est un JButton

		if(getValueAt(0, col) instanceof JButton)

			return false;

		return true; 

	}
	//Retourne le titre de la colonne à l'indice spécifié

	public String getColumnName(int col) {

		return this.title[col];

	}

}

