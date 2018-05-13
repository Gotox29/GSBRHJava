package bzh.gsbrh.observateurs;

public interface Observateur {
	// Methode appel� automatiquement lorsque l'etat change
	public void actualiser(Observable o);

	// M�thode appel� par les observables pour pass� un id � l'observateur
	public void actualiser(Observable o, int id);
	
	// M�thode appel� par les observables pour pass� un string � l'observateur
	public void actualiser(Observable o, String valeur);

	// M�thode appel� par les observables pour pass� un string et un int � l'observateur
	public void actualiser(Observable o, String valeur, int code);
}
