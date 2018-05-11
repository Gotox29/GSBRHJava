package bzh.gsbrh.observateurs;

public interface Observateur {
	// Methode appelé automatiquement lorsque l'etat change
	public void actualiser(Observable o);
}
