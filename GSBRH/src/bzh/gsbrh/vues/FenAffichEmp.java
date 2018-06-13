package bzh.gsbrh.vues;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;

public class FenAffichEmp extends FenFormulaire {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4660605379669446120L;

	protected FenAffichEmp(Observateur o, Employe employe) {
		super(CONSULTER, o, employe);

	}
	
	public static FenFormulaire creerFenetre( Observateur o, Employe employe) {
		if (moi == null)
			moi = new FenAffichEmp(o, employe);
		else
			moi.setForm(employe, Lexique.CONSULTER);
		moi.bloquerChamps();
		return moi;
	}

}
