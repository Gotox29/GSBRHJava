package bzh.gsbrh.modeles;

import java.util.Random;

public class GenerateurMDP {
	private static final Random rand = new Random();
	private static final String min = "abcdefghijklmnopqrstuvwxyz";
	private static final String maj = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	private static final String num = "1234567890";
	private static final String sym = "&~#|`-_)('/?,;:.";
	private static int taille;
	
	public static String generer(int debut, int fin, boolean spe){
		String pass = "";
		while((taille = rand.nextInt(fin)) < debut);
		for(int i = 0 ; i < taille; i++){
			int type = rand.nextInt(20);
			if(type < 3 && spe){
				pass += sym.charAt(rand.nextInt(15));
			} else if(type < 6){
				pass += num.charAt(rand.nextInt(10));
			} else if(type < 10){
				pass += maj.charAt(rand.nextInt(15));
			} else{
				pass += min.charAt(rand.nextInt(15));
			}
		}
		return pass;
	}
}
