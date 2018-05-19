package bzh.gsbrh.vues;

import bzh.gsbrh.observateurs.Bouton;
import bzh.gsbrh.observateurs.Fabrique;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;

public class FactBouton extends Fabrique{
	

	public static Bouton FactoryBouton(Observateur o, int type){
		switch(type){
		case BO_CONNECT:
			return new BValider(o,BOUTON_CONNE, ID_CO);
		case BO_DECONNECT:
			return new BValider(o,BOUTON_DECO, AP_DECO);
		case BO_AJOUT:
			if(o instanceof FenFormulaire){
				return new BValider(o, BOUTON_AJOUT, FO_AJ);
			}
			if(o instanceof FenListeEmployes){
				return new BValider(o,BOUTON_AJOUT, LI_AJ);
			}
		case BO_MODIF:
			if(o instanceof FenFormulaire)
				return new BValider(o,BOUTON_MODIF, FO_MO);

			if(o instanceof Onglet)
				return new BRetour(o,BOUTON_MODIF, LI_MO);

		case BO_REINIT:
			if(o instanceof PopUp)
				return new BReinitialiser(o,BOUTON_DEPROG, PP_DATE_RE);
			else
				return new BReinitialiser(o,BOUTON_REINIT, FO_RE);

		case BO_SUPPR:
			return new BReinitialiser(o,BOUTON_SUPPR, LI_PR);

		case BO_RETOUR:
			if(o instanceof PopUp)
				return new BRetour(o,BOUTON_RETOUR, PP_DATE_BA);
			else
				return new BRetour(o,BOUTON_RETOUR, FO_BA);
		
		case BO_VALIDER:
			return new BValider(o,BOUTON_VALIDER, PP_DATE_VA);
		case BO_DATE_NOW:
			return new BValider(o, BOUTON_DATE, Lexique.BO_DATE_NOW);
		case BO_GENE_PASS:
			return new BValider(o, BOUTON_GENRER , Lexique.BO_GENE_PASS);
		}

		return null;
	}
}
