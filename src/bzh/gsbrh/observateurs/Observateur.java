package bzh.gsbrh.observateurs;

public interface Observateur {
	// Methode appelé automatiquement lorsque l'etat change
	public void actualiser(Observable o);

	public void actualiser(Observable o, String valeur);
	
	public void actualiser(Observable o, String valeur, int code);
}
