package Metier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
//<% if(session.getAttribute("admin")==null){ response.sendRedirect("Login.jsp");} %>
public class Login {
																																				//44283216
	String Email = null , Password=null , Role = null;
	private Map<String, String> Erreurs = new HashMap<String, String>();
	

	public  String validation( String pass,String email) throws Exception {
		
		Connection conn = SingletonLogin.getConnection();
		
		System.out.println("validation !! ");
		System.out.println("pass : " + pass);
		System.out.println("email : " + email);
  
		if ( pass != null && email!=null) {
			 
				PreparedStatement ps;

				try {
					ps = conn.prepareStatement("select EmailU,Passwordd,RoleU  from Users where EmailU = ? and Passwordd=? ");
					ps.setString(1,email);
					ps.setString(2,pass);
					ResultSet res = ps.executeQuery();
					System.out.println("test");
					while(res.next()) {
						Email = res.getString("EmailU");
						Password = res.getString("Passwordd");
						Role = res.getString("RoleU");
						System.out.println(Email + Password + Role );
					}	
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		                if(Email == null || Password ==null) {
			 			throw new Exception( " Email ou mot de passe invalide !!" );
			        }
 
		} else {
			System.out.println("password or email is null !!");
			throw new Exception( "Merci de saisir votre mot de passe ou votre email." );
		}
		return Role;
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	public void setErreur( String champ, String message ) {
		Erreurs.put(champ, message);
	}

	public Map<String, String> getErreurs() {
		return Erreurs;
	}
	

}
