package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;

public class ChampPass extends Champ {

	private Bouton generer;
	private boolean generable = false;

	public ChampPass(Observateur o, String label, String valeur, boolean generable) {
		super(o, label, Lexique.CHAMP_PASS);
		this.generable = generable;
		// TODO Auto-generated constructor stub
		this.pass = new JPasswordField();
		if(generable){
			JPanel zonPass = new JPanel(new GridLayout(1,2));
			generer = FactBouton.FactoryBouton(o, Lexique.BO_GENE_PASS);
			generer.setText("");
			generer.setIcon(Lexique.ICONALEA);
			zonPass.add(this.pass);
			zonPass.add(generer);
			placementChamps(this.label, zonPass);
		}else{
			placementChamps(this.label, this.pass);
		}
	}
	
	public ChampPass(Observateur o, String label) {
		super(o, label, Lexique.CHAMP_PASS);
		this.generable = generable;
		// TODO Auto-generated constructor stub
		this.pass = new JPasswordField();
		placementChamps(this.label, this.pass);
	}

	public String getText(){
		return new String(this.pass.getPassword());
	}
	
	public void setValeur(Object val){
		this.valeur = (String) val;
	}

	public void setText(String val){
		pass.setText(val);
	}
	
	public void initialiserSaisie(){
		pass.setText(valeur);
		this.razCouleur();
	}

	public void bloquerChamp(){
		pass.setEditable(false);
		pass.setBackground(Lexique.COLOR_TEXT_NULL);
	}
	
	public void deBloquerChamp(){
		pass.setEditable(true);
		pass.setBackground(Lexique.COLOR_TEXT_WHITE);
	}

	public void setCouleur(boolean etat){
		if(etat){
			pass.setBackground(Lexique.COLOR_TEXT_VALIDE);
		}else{
			pass.setBackground(Lexique.COLOR_TEXT_IVALIDE);
		}
	}

	public void razCouleur(){
		pass.setBackground(Lexique.COLOR_TEXT_WHITE);
	}
}
