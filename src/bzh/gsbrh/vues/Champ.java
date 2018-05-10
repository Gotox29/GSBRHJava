package bzh.gsbrh.vues;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Champ extends JPanel  {
	private JLabel label;
	private JTextField saisie;
	private String valeur;
	private JComboBox composant;
	
	public String getDansSaisie(){
		return this.saisie.getText();
	}
	
	public Champ(String label){
		this.label = new JLabel(label);
		this.saisie = new JTextField();
		
		this.saisie.setPreferredSize(new Dimension(150,20));
		this.add(this.label);
		this.add(this.saisie);
	}
	public Champ(String label, String valeur){
		this.label = new JLabel(label);
		this.saisie = new JTextField();
		this.valeur = valeur;
		this.saisie.setText(valeur);
		
		
		
		this.saisie.setPreferredSize(new Dimension(150,20));
		this.add(this.label);
		this.add(this.saisie);
		
	}
	public Champ(String label, String valeur, JComboBox composant){
		this.label = new JLabel(label);
		this.composant = composant;
		this.valeur = valeur;
		
		this.composant.setPreferredSize(new Dimension(150,30));
		this.add(this.label);
		this.add(this.composant);
		
	}
	
	public void effacerSaisie(){
		this.saisie.setText("");
	}
	
	public void initialiserSaisie(){
		if(composant != null){
			composant.setSelectedIndex(Integer.parseInt(valeur));
		}else{
			this.saisie.setText(this.valeur);
		}
	}
	
	public JLabel getLabel(){
		return this.label;
	}
	
	public JTextField getText(){
		return this.saisie;
	}
	
	public Component getComp(){
		return this.composant;
	}
	
	public String getValeur(){
		return this.valeur;
	}
	
	public void setValeur(String val){
		this.valeur = val;
	}
	
	public void bloquerChamp(){
		this.saisie.setEditable(false);
	}
	
	
}
