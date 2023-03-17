package warrantyPack;
import java.sql.*;
public class UserDao {
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
   
   
 public  boolean login(String uname,String pwd,String type)
  { try {
	 con=Db.getConnection();
	   Statement st=con.createStatement();
	 String q="select * from user where userName= '"+uname+"';";
	 ResultSet rs=st.executeQuery(q);
	 while(rs.next())
	 {
		String u=rs.getString(1);
		String p=rs.getString(2);
		String t=rs.getString(3);
		if(u.equals(uname)&& p.equals(pwd) && t.equals(type))
			return true;
	 }
	 return false;
   }
   catch(Exception e) {
      System.out.println(e);
      return false;
   }
 }
   public boolean login(String uname, String pwd)
   {
	   try {
			 con=Db.getConnection();
			   Statement st=con.createStatement();
			 String q="select username,pwd from user where userName= '"+uname+"';";
			 ResultSet rs=st.executeQuery(q);
			 while(rs.next())
			 {
				String u=rs.getString(1);
				String p=rs.getString(2);
				if(u.equals(uname)&& p.equals(pwd) )
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
