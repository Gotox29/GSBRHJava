package bzh.gsbrh.controleurs;

import java.util.Hashtable;

public class Langues {
	private static Hashtable <Integer, String> messages;
	private static Hashtable <Integer, String> erreurs;
	private static Hashtable <Integer, String> confirmations;
	private Langues(){
		messages = new Hashtable <Integer, String>();
		messages.put(0, "Employe modifié");
		messages.put(1, "Employe retiré");
		
		erreurs = new Hashtable <Integer, String>();
		erreurs.put(0, "Champ invalide, \nvérifiez les champs de saisie");
		erreurs.put(1, "Erreur dans la modification employé");
		
		confirmations = new Hashtable <Integer, String>();
		confirmations.put(0, "Confirmer les modifications de l'utilisateur ?");
		confirmations.put(1, "Confirmer la suppresssion de l'utilisateur ?");
	}
	
	public static String getMessage(String type, int code){
		String message = "";
		Langues langue = new Langues();
		switch(type){
		case"message":
			message = messages.get(code);
			break;
		case"erreur":
			message = erreurs.get(code);
			break;
		case "confirme":
			message = confirmations.get(code);
			break;
		}
		
		return message;
	}
}
