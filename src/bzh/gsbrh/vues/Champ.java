package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.Panneau;

public class Champ extends Panneau {

	public static final int _ID = 4;
	private JLabel label;
	private TSaisie saisie;
	private JPasswordField pass;
	private String valeur;
	private int index;
	private JComboBox box;
	private static SpringLayout layout = new SpringLayout();
	private Fenetre fenetre;
	private int type;
	public String getDansSaisie(){
		return this.saisie.getText();
	}

	public Champ(Observateur o, String label, int type){
		super(o, layout);
		this.fenetre = (Fenetre) o;
		this.label = new JLabel(label);
		this.type = type;
		switch(type){
		case _TEXT:
			this.saisie = new TSaisie(this);
			placementChamps(this.label, this.saisie);
			break;
		case _PASS:
			this.pass = new JPasswordField();
			placementChamps(this.label, this.pass);
			break;
		}

	}
	public Champ(Observateur o, String label, String valeur, int type){
		super(o, layout);
		this.fenetre = (Fenetre) o;
		this.label = new JLabel(label);
		this.saisie = new TSaisie(this,valeur);
		this.valeur = valeur;
		this.type = type;
		placementChamps(this.label, this.saisie);

	}
	public Champ(Observateur o, String label, int index, String [] liste){
		super(o, layout);
		this.fenetre = (Fenetre) o;
		this.label = new JLabel(label);
		this.box = new JComboBox(liste);
		this.index = index;
		this.type = _COMB;
		this.box.setSelectedIndex(this.index);
		placementChamps(this.label, this.box);
	}

	public void effacerSaisie(){
		this.saisie.setText("");
	}

	public void initialiserSaisie(){
		if(this.box != null){
			this.box.setSelectedIndex(index);
		}else{
			this.saisie.setText(this.valeur);
		}
	}

	public JLabel getLabel(){
		return this.label;
	}

	public String getText(){
		switch(this.type){
		case _TEXT:
			return this.saisie.getText();
		case _PASS:
			char[] pass = this.pass.getPassword();
			return new String(pass);
		case _COMB:
			return (String) this.box.getSelectedItem();
		}
		return null;

	}

	public void setText(String text){
		switch(this.type){
		case _TEXT:
			this.saisie.setText(text+"");
		}
	}
	
	public Component getCompo(){
		switch(this.type){
		case _TEXT:
			return this.saisie;
		case _PASS:
			return this.pass;
		case _COMB:
			return this.box;
		}
		return null;		
	}

	public int getBox(){
		return this.box.getSelectedIndex();
	}

	public String getValeur(){
		return this.valeur;
	}
	public int getIndex(){
		return this.index;
	}

	public void setValeur(Object val){
		if(this.type == _COMB)
			this.index = (int) val;
		else
			this.valeur = (String) val;
		
	}

	public void bloquerChamp(){
		this.saisie.setEditable(false);
	}

	@Override
	public void placementChamps(JComponent label, JComponent zone) {
		// TODO Auto-generated method stub
		if(this.fenetre instanceof Fenetre){
			label.setPreferredSize(new Dimension(100,20));
			zone.setPreferredSize(new Dimension(100,20));
			this.fenetre.placementComposant(label, zone);
		}
	}

	


	
}
