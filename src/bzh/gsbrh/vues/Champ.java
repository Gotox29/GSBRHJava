package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.LimitText;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.Panneau;

public class Champ extends Panneau {

	protected JLabel label;
	protected TSaisie saisieJours;
	protected TSaisie saisieMois;
	protected TSaisie saisie;
	protected JPasswordField pass;
	protected String valeur;
	protected int index;
	protected JComboBox<Object> box;
	protected static SpringLayout layout = new SpringLayout();
	protected Fenetre fenetre;
	protected PopUp dialog;
	protected int type;
	protected Dimension dimensionID = new Dimension(50,20);
	protected Dimension dimensionDate_JM = new Dimension(50,20);
	protected Dimension dimensionDate_AA = new Dimension(100,20);
	protected Dimension dimensionText = new Dimension(170,20);
	protected Bouton genererPass;
	
	
	
	public int getType(){
		return this.type;
	}
	
	public void razCouleur(){
		if(type == Lexique.CHAMP_ID)
			saisie.setBackground(null);
		else
			saisie.setBackground(Color.WHITE);
	}
	
	public void setCouleur(boolean etat){
		if(etat){
			saisie.setBackground(new Color(100,200,10));
		}else{
			saisie.setBackground(new Color(255, 80, 80));
		}
	}
	
	public void setEtat(boolean etat){
		saisie.etat = etat;
	}
	public Champ(Observateur o, int type){
		super(layout);
		if(o instanceof Fenetre)
			this.fenetre = (Fenetre) o;
		if(o instanceof PopUp)
			this.dialog = (PopUp) o;
	}
	
	public Champ(Observateur o, String label, int type){
		super(o, layout);
		if(o instanceof Fenetre)
			this.fenetre = (Fenetre) o;
		if(o instanceof PopUp)
			this.dialog = (PopUp) o;
		this.label = new JLabel(label);
		this.type = type;
		switch(type){
		case Lexique.CHAMP_TEXT:
			this.saisie = new TSaisie(this);
			placementChamps(this.label, this.saisie);
			break;
		}
	}
	
	public Champ(Observateur o, String label, String valeur, int type){
		super(o, layout);
		if(o instanceof Fenetre)
			this.fenetre = (Fenetre) o;
		this.label = new JLabel(label);
		switch(type){
		
		case Lexique.CHAMP_CP:
			this.saisie = new TSaisie(this,valeur,5,true);
			placementChamps(this.label, this.saisie);
			this.saisie.setPreferredSize(this.dimensionText);
			break;
		case Lexique.CHAMP_TEL:
			this.saisie = new TSaisie(this,valeur,10,true);
			placementChamps(this.label, this.saisie);
			this.saisie.setPreferredSize(this.dimensionText);
			break;
		case Lexique.CHAMP_TEXT:
			this.saisie = new TSaisie(this,valeur);
			this.saisie.setPreferredSize(this.dimensionText);
			placementChamps(this.label, this.saisie);
			break;

		}
		this.valeur = valeur;
		this.type = type;
	}
	public Champ(Observateur o, String label, int index, int type){
		super(o, layout);
		if(o instanceof Fenetre)
			this.fenetre = (Fenetre) o;
		if(o instanceof PopUp)
			this.dialog = (PopUp) o;
		this.label = new JLabel(label);
		this.box = new JComboBox<Object>();
		this.index = index;
		this.type = type;
		// this.box.setSelectedIndex(0);
		placementChamps(this.label, this.box);
	}

	public void effacerSaisie(){
		this.saisie.setText("");
	}

	public void initialiserSaisie(){
		switch(this.type){
		case Lexique.CHAMP_COMB:
			this.box.setSelectedIndex(index);
			System.out.println(index);
			break;
		default:
			this.saisie.setText(this.valeur);
			this.razCouleur();
		}

	}

	public JLabel getLabel(){
		return this.label;
	}

	public String getText(){
		switch(this.type){
		case Lexique.CHAMP_PASS:
			char[] pass = this.pass.getPassword();
			return new String(pass);
		case Lexique.CHAMP_COMB:
			return Integer.toString(this.box.getSelectedIndex());
		default:
			return this.saisie.getText();
		}
	}

	public void setText(String text){
		switch(this.type){
		case Lexique.CHAMP_TEXT:
			this.saisie.setText(text+"");
		}
	}

//	public Component getCompo(){
//		switch(this.type){
//		case Lexique.CHAMP_PASS:
//			return this.pass;
//		case Lexique.CHAMP_COMB:
//			return this.box;
//		case Lexique.CHAMP_TEXT:
//			return this.saisie;
//		default:
//			return this.saisie;
//		}
//	}

	public void setBox(Object item){
		this.box.addItem(item);
	}
	
	public int getBox(){
		return this.box.getSelectedIndex();
	}

	public String getValeur(){
		return this.valeur;
	}
	
	public int getIndex(){
		return this.box.getSelectedIndex();
	}

	public void setValeur(Object val){
		switch(type){
		case Lexique.CHAMP_COMB:
			if(val instanceof Integer)
				this.index = (int) val;
			if(val instanceof JComboBox)
				this.box = (JComboBox<Object>) val;
			break;
		case Lexique.CHAMP_TEL:
		case Lexique.CHAMP_CP:
		case Lexique.CHAMP_ID:
		case Lexique.CHAMP_TEXT:
			this.valeur = (String) val;
			break;
		}
	}

	public void bloquerChamp(){
		this.saisie.setEditable(false);
		this.saisie.setBackground(null);
	}
	public void deBloquerChamp(){
		this.saisie.setEditable(true);
		this.saisie.setBackground(Color.WHITE);
	}
	
	public void placementChamps(JComponent label, JComponent zone) {
		// TODO Auto-generated method stub
		if(this.fenetre instanceof FenIdentification){
			label.setPreferredSize(new Dimension(100,20));
			zone.setPreferredSize(new Dimension(100,20));
			this.fenetre.placementComposant(label, zone);
		}
		if(this.fenetre instanceof FenFormulaire){
			label.setPreferredSize(dimensionText);
			zone.setPreferredSize(dimensionText);
			this.fenetre.placementComposant(label, zone);
		}
		if(this.dialog instanceof PopUp){
			label.setPreferredSize(new Dimension(100,20));
			zone.setPreferredSize(new Dimension(100,20));
			this.dialog.placementComposant(label, zone);
		}
	}
}
