package bzh.gsbrh.vues;

import java.awt.Color;

import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;

public class ChampID extends Champ {

	public ChampID(Observateur o, String label, String valeur) {
		super(o, label, Lexique.CHAMP_ID);
		this.saisie = new TSaisie(this,valeur,4,false);
		this.valeur = valeur;
		placementChamps(this.label, this.saisie);
		this.saisie.setPreferredSize(this.dimensionID);
	}

	public void razCouleur(){
			saisie.setBackground(null);
	}
	
	public void setCouleur(boolean etat){
		if(etat){
			saisie.setBackground(Lexique.COLOR_TEXT_VALIDE);
		}else{
			saisie.setBackground(Lexique.COLOR_TEXT_IVALIDE);
		}
	}
}
