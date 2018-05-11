package bzh.gsbrh.modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
	private static Connection connexion;
	private String url ="jdbc:mysql://localhost/slam_gsbjava";
	private String login="root";
	private String password="root";

	private ConnexionBDD(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(this.url, this.login, this.password);

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Base de donnée non trouvé.");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	//Méthode d'accès au singleton
	public static Connection getInstance() throws ClassNotFoundException, SQLException{
		if(connexion == null)
			new ConnexionBDD();
		return connexion;
	}


	public static void close(){
		try {
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connexion = null;
	}

}
