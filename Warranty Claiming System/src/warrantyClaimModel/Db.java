package warrantyClaimModel;
import java.sql.*;
public class Db {
	private static Connection con=null;
public static Connection getConnection()
 {
	if(con==null)
		setConnection();
	
	return con;
 }

	public static void setConnection()
	{
		try{

			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/WarrantyClaim","root","1234");
		}catch(Exception e){System.out.println(e);}
	}
	public static void close() 
	{ try {
		if(con!=null)
		con.close();
		}
		catch(Exception e) {
	        System.out.println("");
		}
	}
}