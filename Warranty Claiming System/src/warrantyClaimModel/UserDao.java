package warrantyClaimModel;
import java.sql.*;
import warrantyClaimController.*;
public class UserDao extends User {
   Connection con=null;
   public boolean signup(String uname,String pwd)
   {
	   try {
		   con=Db.getConnection();
	   Statement st=con.createStatement();
	 String q="select * from user where userName= '"+uname+"';";
	 ResultSet  r=st.executeQuery(q);
	 if(r.next())
	 {
		 System.out.println("User Name Already Exists!");
		 return false;
	 }
	 String s="INSERT INTO user values('"+uname+"','"+pwd+"','Customer');";
	 int ra=st.executeUpdate(s);
	 
	 return (ra==1);
	   }
	   catch(Exception e)
	   {
		   System.out.println("Error while signup"+e);
		   return false;
	   }
   }
   
   
 
   public boolean login()
   {
	   try {
			 con=Db.getConnection();
			   Statement st=con.createStatement();
			 String q="select userType from user where userName= '"+this.uName+"' and pwd='"+this.pwd+"';";
			 ResultSet rs=st.executeQuery(q);
			 while(rs.next())
			 {
				
				
					this.uType=rs.getString(1);
					return true;
				
			 }
			 return false;
		   }
		   catch(Exception e) {
		      System.out.println(e);
		      return false;
		      }
   }
   
   
}
