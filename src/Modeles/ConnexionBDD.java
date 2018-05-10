package Modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
	public static class ConnexionBdd {
		private static Connection connexion;
		private String url ="jdbc:mysql://localhost/slam_gsbjava";
		private String login="root";
		private String password="root";
		
		private ConnexionBdd() throws ClassNotFoundException, SQLException{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				ConnexionBdd.connexion = DriverManager.getConnection(this.url, this.login, this.password);
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		//Méthode d'accès au singleton
		public static Connection getInstance() throws ClassNotFoundException, SQLException{
			if(connexion == null)
				new ConnexionBdd();
			return connexion;
		}
	}	

}
