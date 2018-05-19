package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;

public class ChampDate extends Champ {

	private Bouton now;
	
	public ChampDate(Observateur o, String label, String valeur) {
		super(o, label, Lexique.CHAMP_DATE);
		if(valeur == null || valeur.equals(null))
			valeur = LocalDate.now().toString();
		String splitDate[] = valeur.split("-");
		String mois = splitDate[1];
		String jours = splitDate[2];
		String annee = splitDate[0];
		this.saisieJours = new TSaisie(this,jours,2, true);
		this.saisieJours.setPreferredSize(this.dimensionDate_JM);
		this.saisieMois = new TSaisie(this, mois,2, true);
		this.saisieMois.setPreferredSize(this.dimensionDate_JM);
		this.saisie = new TSaisie(this,annee,4,true);
		this.saisie.setPreferredSize(this.dimensionDate_AA);
		this.saisie.setText(annee);
		this.saisieMois.setText(mois);
		this.saisieJours.setText(jours);
		JPanel date = new JPanel(new GridLayout(1,4));
		JPanel moisJours = new JPanel(new GridLayout(1,2));
		moisJours.add(this.saisieJours);
		moisJours.add(this.saisieMois);
		date.add(moisJours);
		
		date.add(this.saisie);
		now = FactBouton.FactoryBouton(o, Lexique.BO_DATE_NOW);
		now.setText("...");
		date.add(now);
		placementChamps(this.label, date);
	}
	
	public void bloquerChamp(){
		this.saisie.setEditable(false);
		this.saisie.setBackground(Lexique.COLOR_TEXT_NULL);
		this.saisieMois.setEditable(false);
		this.saisieMois.setBackground(Lexique.COLOR_TEXT_NULL);
		this.saisieJours.setEditable(false);
		this.saisieJours.setBackground(Lexique.COLOR_TEXT_NULL);
		this.now.setEnabled(false);
	}
	
	public void deBloquerChamp(){
		this.saisie.setEditable(true);
		this.saisie.setBackground(Color.WHITE);
		this.saisieMois.setEditable(true);
		this.saisieMois.setBackground(Color.WHITE);
		this.saisieJours.setEditable(true);
		this.saisieJours.setBackground(Color.WHITE);
		this.now.setEnabled(true);
	}

	public void initialiserSaisie(){
		System.out.println("Champ date = "+valeur);
		if(valeur == null)
			valeur = LocalDate.now().toString();
		String splitDate[] = valeur.split("-");
		String annee = splitDate[0];
		String mois = splitDate[1];
		String jours = splitDate[2];
		this.saisie.setText(annee);
		this.saisieMois.setText(mois);
		this.saisieJours.setText(jours);
		this.razCouleur();
	}

	public String getText(){
		return this.saisie.getText() + "-" + this.saisieMois.getText() + "-" +this.saisieJours.getText();
	}

	public void setValeur(Object val){
		this.valeur = (String) val;
	}
	
	public void setText(String val){
		String splitDate[] = val.split("-");
		String annee = splitDate[0];
		String mois = splitDate[1];
		String jours = splitDate[2];
		this.saisie.setText(annee);
		this.saisieMois.setText(mois);
		this.saisieJours.setText(jours);
	}

	public void setCouleur(boolean etat){
		if(etat){
			saisieJours.setBackground(Lexique.COLOR_TEXT_VALIDE);
			saisieMois.setBackground(Lexique.COLOR_TEXT_VALIDE);
			saisie.setBackground(Lexique.COLOR_TEXT_VALIDE);
		}else{
			saisieJours.setBackground(Lexique.COLOR_TEXT_IVALIDE);
			saisieMois.setBackground(Lexique.COLOR_TEXT_IVALIDE);
			saisie.setBackground(Lexique.COLOR_TEXT_IVALIDE);
		}
	}

	public void razCouleur(){
		saisieJours.setBackground(Lexique.COLOR_TEXT_WHITE);
		saisieMois.setBackground(Lexique.COLOR_TEXT_WHITE); 
		saisie.setBackground(Lexique.COLOR_TEXT_WHITE);
	}

	public void placementChamps(JComponent label, JComponent zone) {
		// TODO Auto-generated method stub
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