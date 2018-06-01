import bzh.gsbrh.controleurs.Controleur;

/**
 * Classe d'entrée dans l'application
 * 
 * @author Anthony Nizac
 * @version 1.0
 */
public class GSBRH {

	/**
	 * Methode d'entrée dans l'application
	 * 
	 * @param args
	 *            Passe un tableau d'argument a l'application
	 */
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i]);
		}
		Controleur controleur = new Controleur();
		controleur.lancerAppli();
	}

}
