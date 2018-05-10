package Vues;

import javax.swing.JButton;

public class Bouton extends JButton {
	
	private String idEmploye;
	
	public Bouton(String name, String idEmploye){
		super(name);
		this.idEmploye = idEmploye;
	}
	public String getIdEmploye(){
		return this.idEmploye;
	}
}
