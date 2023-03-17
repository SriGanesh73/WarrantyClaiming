package warrantyPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class CustomerDao extends CustomerActions {
	Connection con=Db.getConnection();
	public  boolean insertCustDetails() {
	  try {	
		  String q="Insert into Customer values(?,?,?,?,?,?);";
		  PreparedStatement ps=con.prepareStatement(q);
		  ps.setString(1,this.getEmail());
		  ps.setString(2,this.getInvoice());
		  ps.setString(3, this.getCustName());
		  ps.setString(4, this.getAddress());
		  ps.setInt(5,this.getPinCode() );
		  ps.setString(6, this.getPhone());
		   ps.executeUpdate(); 
		 
		   
		   return true;
	  		}
	  catch(Exception e)
	  {
		  System.out.println(e);
		  return false;
	  }
	}
	
	
	public void deleteCustomer(String invoice)
	{ 
		
		try {
		
			  Statement st=con.createStatement();
			 String  q1="delete from customer where invoice= '"+invoice+"';";
			
			int r1=st.executeUpdate(q1);
			if(r1==1)
				System.out.println("Customer Details also Deleted");
			else
				System.out.println("Customer Deatils not deleted");
			 
			
	
	   }
		catch(Exception e) {
			System.out.println(e);
		    
		}
	}
	
}
