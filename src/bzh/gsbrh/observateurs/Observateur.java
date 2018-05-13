package bzh.gsbrh.observateurs;

public interface Observateur {
	// Methode appelé automatiquement lorsque l'etat change
	public void actualiser(Observable o);

	// Méthode appelé par les observables pour passé un id à l'observateur
	public void actualiser(Observable o, int id);
	
	// Méthode appelé par les observables pour passé un string à l'observateur
	public void actualiser(Observable o, String valeur);

	// Méthode appelé par les observables pour passé un string et un int à l'observateur
	public void actualiser(Observable o, String valeur, int code);
}
