package bzh.gsbrh.observers;

public interface Observateur {
	// Methode appelé automatiquement lorsque l'etat change
	public void actualiser(Observable o);
}
