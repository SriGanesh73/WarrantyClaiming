package warrantyPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


public class WarrantyDao extends Warranty {

	Connection con=Db.getConnection();
	
	private String claimSts;
	WarrantyDao()
	{
		super();
		this.claimSts=null;
	}
	WarrantyDao(String invoice)
	{
		this.invoice=invoice;
	}
	WarrantyDao(String invoice,String userName )
	{
		super(invoice,userName);
		this.claimSts="In Progress";
		
	}
	public String getClaimSts() {
		return claimSts;
	}
	public void setClaimSts(String claimSts) {
		this.claimSts = claimSts;
	}
	
	
	public boolean addDesc()
	{ 
		
		try {
		
			String q="Insert into Warranty (invoiceNo,userName,claimSts,probDesc) values(?,?,?,?);";
			  PreparedStatement ps=con.prepareStatement(q);
			  ps.setString(1,this.invoice);
			  ps.setString(2,this.userName);
			  ps.setString(3, this.claimSts);
			  ps.setString(4, this.desc);
			  
			  ps.executeUpdate(); 
			 ProductDao p=new ProductDao();
			  p.updateStatus(this.invoice,this.claimSts );
	
		return true;
	   }
		catch(Exception e) {
			System.out.println(e);
		    return false;
		}
	}
	public boolean trackClaim()
	{
		try
		{
			Statement st=con.createStatement();
			String q="Select * from Warranty where invoiceNo = '"+this.invoice+"';";
			ResultSet rs=st.executeQuery(q);
			boolean b=true;
			while(rs.next())
			{  
				b=false;
				System.out.println("-----Displaying Claim Info-----");
				System.out.println("Invoice no        : "+rs.getString(1) );
				System.out.println("Claimed By User   : "+rs.getString(2));
				System.out.println("Issue Description : "+ rs.getString(4));
				System.out.println("Current Status    : "+rs.getString(3));
				System.out.println("Remarks           : "+rs.getString(5));
				
			}
			return b;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return true;
		}
	}

	public  void showPendingTickets()
	{
		try
		{
			Statement st=con.createStatement();
			String q="Select * from Warranty where claimSts not in ('Rejected','Closed');";
			ResultSet rs=st.executeQuery(q);
			
			System.out.println("\n-----Pending Tickets-----\n");
			int i=1;
			while(rs.next())
			{  
				System.out.println("\t Ticket Serial No: "+i++ );
				System.out.println("Invoice no        : "+rs.getString(1) );
				System.out.println("Claimed By User   : "+rs.getString(2));
				System.out.println("Issue Description : "+ rs.getString(4));
				System.out.println("Current Status    : "+rs.getString(3));
				System.out.println("Remarks           : "+rs.getString(5));
				System.out.println("\n");
				
			}
			if(i==1)
				System.out.println(" No Pending Tickets As Of Now !! ");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
	}
	
	
	public void updateTicket(String invoice,String sts,String rem)
	{ 
		
		try {
		
			String q="update warranty set claimSts='"+sts+"',remarks='"+rem+"' where invoiceNo='"+invoice+"' and claimSts not in('Closed','Rejected')";
			  Statement st=con.createStatement();
			int r=  st.executeUpdate(q);
			if(r==1)
				{System.out.println("Claim Status Updated");
				ProductDao p=new ProductDao();
				p.updateStatus(invoice,sts);
				}
			else
				System.out.println("Please Enter a valid open ticked");
			 
			
	
	   }
		catch(Exception e) {
			System.out.println(e);
		    
		}
	}
	
	
	
	public void rejectTicket(String invoice,String remarks)
	{ 
		
		try {
		
			String q="update warranty set claimSts='Rejected',remarks='"+remarks+"' where invoiceNo='"+invoice+"' and claimSts not in('Closed','Rejected')";
			  Statement st=con.createStatement();
			int r=  st.executeUpdate(q);
			if(r==1)
				{System.out.println("Claim Request Rejected & Closed");
				ProductDao p=new ProductDao();
				p.updateStatus(invoice,"Rejected");
				}
			else
				System.out.println("Please Enter a valid open ticked");
			 
			
	
	   }
		catch(Exception e) {
			System.out.println(e);
		    
		}
	}

	
	public void closeTicket(String invoice)
	{ 
		
		try {
		   String remark="Warranty Product Delivered and Claim Request closed Successfully";
			String q="update warranty set claimSts='Closed',remarks='"+remark+"' where invoiceNo='"+invoice+"' and claimSts not in('Closed','Rejected')";
			  Statement st=con.createStatement();
			int r=  st.executeUpdate(q);
			if(r==1)
				{System.out.println("Claim Request Closed Successfully");
				ProductDao p=new ProductDao();
				p.updateStatus(invoice,"Claimed");
				}
			else
				System.out.println("Please Enter a valid open ticked");
			 
			
	
	   }
		catch(Exception e) {
			System.out.println(e);
		    
		}
	}

	public void invalidClaim(String invoice)
	{ 
		
		try {
		
			String q="delete from warranty  where invoiceNo='"+invoice+"' and claimSts not in('Closed','Rejected')";
			  Statement st=con.createStatement();
			int r=  st.executeUpdate(q);
			
			if(r==1)
				{System.out.println("Invalid claim deleted and claim status has been re setted");
				ProductDao p=new ProductDao();
				String sts="Not Claimed";
				p.updateStatus(invoice,sts);
				CustomerDao cd=new CustomerDao();
				cd.deleteCustomer(invoice);
				}
			else
				System.out.println("Please Enter a valid open ticked");
			 
			
	
	   }
		catch(Exception e) {
			System.out.println(e);
		    
		}
	}
	
	
}
