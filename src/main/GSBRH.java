package main;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Hashtable;

import Controleurs.Controleur;
import Modeles.Employe;
import Modeles.Requetes;
import Vues.Fenetre;
import Vues.Formulaire;
import Vues.Message;

public class GSBRH {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
				
				Controleur cont = new Controleur();
				cont.lancerAppli();

		
	}


}
