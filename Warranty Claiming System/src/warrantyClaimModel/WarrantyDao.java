package warrantyClaimModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import warrantyClaimController.*;

import java.sql.ResultSet;
import warrantyClaimView.*;

public class WarrantyDao extends Warranty {

	Connection con=Db.getConnection();
	private int claimId;
	private String claimSts;
	private String remarks; 
	
	public WarrantyDao()
	{
		super();
		this.claimSts=null;
	}
	public WarrantyDao(String invoice)
	{
		this.invoice=invoice;
	}
	public WarrantyDao(String invoice,String userName )
	{
		super(invoice,userName);
		this.claimSts="In Progress";
		
	}
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
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
			String q="Select * from Warranty where invoiceNo = '"+this.invoice+"'order by claimId desc limit 1;";
			ResultSet rs=st.executeQuery(q);
			boolean b=true;
			//rs.first();
			while(rs.next())
			{  
				b=false;
				WarrantyDao w=new WarrantyDao(); 
			w.claimId=	rs.getInt(1); 
			  w.invoice =rs.getString(2); 
			  w.userName=rs.getString(3);
			  w.claimSts =rs.getString(4);
			  w.desc = rs.getString(5);
			  w.remarks =rs.getString(6);
			  Menu.display(w);
			}
			return b;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return true;
		}
	}

	public  List<WarrantyDao> showPendingTickets()
	{
		try
		{  List<WarrantyDao> rec=new ArrayList<>(); 
			Statement st=con.createStatement();
			String q="Select * from Warranty where claimSts not in ('Rejected','Closed','Failed');";
			ResultSet rs=st.executeQuery(q);
			
			
			while(rs.next())
			{  
				WarrantyDao obj= new WarrantyDao();
				obj.claimId=rs.getInt(1);
				obj.invoice=rs.getString(2);
				obj.userName=rs.getString(3);
				obj.desc=rs.getString(5);
				obj.claimSts=rs.getString(4);
				obj.remarks=rs.getString(6);
				rec.add(obj);
			}
			if(rec.size()==0)
				System.out.println(" No Pending Tickets As Of Now !! ");
			return rec;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
			
		}
	}
	
	
	public void updateTicket(String invoice,String sts,String rem)
	{ 
		
		try {
		
			String q="update warranty set claimSts='"+sts+"',remarks='"+rem+"' where invoiceNo='"+invoice+"' and claimSts not in('Closed','Rejected','Failed')";
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
		
			String q="update warranty set claimSts='Rejected',remarks='"+remarks+"' where invoiceNo='"+invoice+"' and claimSts not in('Closed','Rejected','Failed')";
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
			String q="update warranty set claimSts='Closed',remarks='"+remark+"' where invoiceNo='"+invoice+"' and claimSts not in('Closed','Rejected','Failed')";
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

	public void invalidClaim(int cid,String rem)
	{ 
		
		try {
		     
			String q="update warranty  set claimSts='Failed',remarks='"+rem+"' where claimId="+cid+" and claimsts not in ('Rejected','Closed','Failed');";
			  Statement st=con.createStatement();
			int r=  st.executeUpdate(q);
			
			if(r==1)
				{System.out.println("Marked As Failed and claim status has been resetted");
				ProductDao p=new ProductDao();
				String sts="Not Claimed";
				String qu="select invoiceNo from warranty where claimid = "+cid+";"; 
				ResultSet rs=st.executeQuery(qu);
				String inv="";
				while(rs.next())
			     inv=rs.getString(1); 
				p.updateStatus(inv,sts);
				CustomerDao cd=new CustomerDao();
				cd.deleteCustomer(inv);
				}
			else
				System.out.println("Please Enter a valid open ticked");
			 
			
	
	   }
		catch(Exception e) {
			System.out.println(e);
		    
		}
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
