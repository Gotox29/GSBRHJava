package bzh.gsbrh.vues;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bzh.gsbrh.observateurs.Observateur;

public class ChampMail extends Champ {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 1796801169102630240L;

	public ChampMail(Observateur o, String label) {
		super(o, label, CHAMP_MAIL, false);
		JPanel zone = new JPanel(new GridLayout(1, 3));
		zone.setBackground(COLOR_BACKGROUNG);
		JLabel domaine = new JLabel(MAIL_DOMAINE);
		domaine.setToolTipText(MAIL_DOMAINE);
		this.saisie = new TSaisie(this);
		this.saisie.compterTaille(33, false);
		this.setPreferredSize(new Dimension(340,20));
		domaine.setPreferredSize(new Dimension(100,20));
		zone.add(this.saisie);
		zone.add(domaine);
		placementChamps(this.label, zone);
	}

}
