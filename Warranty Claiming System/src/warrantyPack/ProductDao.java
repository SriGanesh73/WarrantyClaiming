package warrantyPack;
import java.sql.*;
import java.time.LocalDate;
public class ProductDao extends Product{
	Connection con=Db.getConnection();
    public boolean isValidProduct()
    {
    	try {
    		String inv=this.getInvoiceNo();
    		LocalDate ld=this.getPurdate();
    		Statement st=con.createStatement();
    		String q="select * from product where invoiceNo= '"+inv+"';";
    		ResultSet rs=st.executeQuery(q);
    		while(rs.next())
    		{    	LocalDate l = rs.getDate(4).toLocalDate();
    			if(rs.getString(1).equals(inv) && ld.equals(l))
    			{
    				this.setProductName(rs.getString(2));
    				this.setProductType(rs.getString(3));
    				this.setClaimSts(rs.getString(5));
    				return true;
    			}
    		}
    		return false;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    		return false;
    	}
    }
    public void updateStatus(String invoice,String sts) throws Exception
    {
    	Statement st=con.createStatement();
		String q="update Product set claimSts ='"+sts+"' where invoiceNo='"+invoice+"';";
		st.executeUpdate(q);
    }
    
    

	public boolean insertProduct()
	{ 
		
		try {
		
			String q="Insert into Product (invoiceNo,pName,Ptype,purDate,claimSts) values(?,?,?,?,?);";
			  PreparedStatement ps=con.prepareStatement(q);
			  ps.setString(1,this.getInvoiceNo());
			  ps.setString(2,this.getProductName());
			  ps.setString(3, this.getProductType());
			  ps.setDate(4,Date.valueOf(this.getPurdate()));
			  ps.setString(5, this.getClaimSts());
			  
			  ps.executeUpdate(); 
			
	
		return true;
	   }
		catch(Exception e) {
			System.out.println(e);
		    return false;
		}
	}
}
