package bzh.gsbrh.vues;

import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Fabrique;
import bzh.gsbrh.observateurs.Observateur;

public class FactBouton extends Fabrique{
	

	public static Bouton FactoryBouton(Observateur o, int type){
		switch(type){
		case BO_CONNECT:
			return new BValider(o,BOUTON_CONNE, ID_CO);

		case BO_AJOUT:
			if(o instanceof FenFormulaire)
				return new BValider(o,BOUTON_AJOUT, FO_AJ);

			if(o instanceof FenListeEmployes)
				return new BValider(o,BOUTON_AJOUT, LI_AJ);
		case BO_MODIF:
			if(o instanceof FenFormulaire)
				return new BValider(o,BOUTON_MODIF, FO_MO);

			if(o instanceof FenListeEmployes)
				return new BValider(o,BOUTON_MODIF, LI_MO);

		case BO_REINIT:
			return new BReinitialiser(o,BOUTON_REINIT, FO_RE);

		case BO_SUPPR:
			return new BReinitialiser(o,BOUTON_SUPPR, LI_SU);

		case BO_RETOUR:
			return new BRetour(o,BOUTON_RETOUR, FO_BA);

		}

		return null;
	}
}
