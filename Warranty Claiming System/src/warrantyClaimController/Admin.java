package warrantyClaimController;
import java.util.Scanner;
import java.util.List;
import warrantyClaimModel.*;
import warrantyClaimView.Menu;

public class Admin {
	static Scanner sc = new Scanner(System.in);
	public void  actions()
	{
		while(true)
		{  System.out.println("\n\n---- Choose Action ----");
			System.out.println("1.Show All pending Tickets\n2.Update a Pending Ticket \n3.Insert a New Product\n" );
			System.out.print("Enter Choice:");
			int ch=sc.nextInt();sc.nextLine();
			String inv="";
			WarrantyDao w=new WarrantyDao();
			ProductDao p=new ProductDao(); 
			switch(ch){
			   case 1:
				List<WarrantyDao> li=   w.showPendingTickets();
				Menu.display(li);
				  break;
			
			   case 2:updateTicket(w);
			   break;
				  
			   case 3:
				   boolean ins=true;
				   while(ins) {
				   System.out.print("Enter Invoice No :");
				   inv=sc.nextLine();
				   if( !Validation.isValidInvoice(inv))
				   {
					   System.out.println("Please Enter a Valid Invoice : ");
					   continue;
				   }
			
				   p.setInvoiceNo(inv);
				   System.out.print("Enter Product name :");
				   String pname=sc.nextLine();
				   if(!Validation.isValid(pname) )
				   {
					   System.out.println("Please Enter a Valid Product Name");
					   continue;
				   }
				   p.setProductName(pname);
				   System.out.print ("Enter Product Type :");
				   String ptype=sc.nextLine();
				   if(!Validation.isValid(ptype) )
				   {
					   System.out.println("Please Enter a Valid Product Type");
					   continue;
				   }
				   p.setProductType(ptype);
				   System.out.print("Enter the Purchase (dd mm yyyy):");
					int date=sc.nextInt();
					int mon=sc.nextInt();
					int year=sc.nextInt();
					sc.nextLine();
					if(!p.setPurDate(date, mon, year))
						{System.out.println("Please Enter a valid Date(dd mm yyyy)");
				    		continue;
						}
				    p.setClaimSts("Not Claimed");
					
				  if( p.insertProduct())
					  System.out.println("Product Inserted");
				   System.out.print("Do you Want to Insert More Products(Y/N) ??:");
				   char c=sc.nextLine().toUpperCase().charAt(0);
				   if(c=='Y')
					   continue;
				   else
				      ins=false;
				   } 
				   break;
				 default:
					 System.out.println("Enter a Valid Choice");
			
			}
			System.out.print("Do you Want to continue(Y/N) ??:");
			 char c=sc.nextLine().toUpperCase().charAt(0);
			   if(c=='N')
			   {  System.out.println("You have been successfully Logged Out");
			      return;
			   }
			   
		}
	}
	void updateTicket(WarrantyDao w)
	{  
		
		System.out.println("1.Update status of Pending Ticket \n2.Reject a Pending Ticket\n3.Report Failed claim\n4.Close a pending Ticket " );
		System.out.print("Enter Choice:");
		int ch=sc.nextInt();sc.nextLine();
		String inv="";
		String sts="";
		String rem="";
		switch(ch)
		{
		case 1:
			 System.out.print("Enter invoice No   :");
			    inv=sc.nextLine();
			   System.out.print("Enter claim Status :");
			    sts=sc.nextLine();
			   System.out.print("Enter Remarks      :");
			    rem=sc.nextLine();
			   w.updateTicket(inv,sts,rem);
		     break;
		case 2:
			System.out.print("Enter Invoice No :");
		    inv=sc.nextLine();
		   System.out.print("Enter Remarks    :");
		    rem=sc.nextLine();
		   w.rejectTicket(inv, rem);
		   break;
		case 3:
			System.out.print("Enter claimId :");
			int c_id=sc.nextInt();sc.nextLine();
			System.out.print("Enter Reason :");
		    rem=sc.nextLine();
		   w.invalidClaim(c_id,rem);
		   break;
		case 4:
		   System.out.print("Enter Invoice No :");
		    inv=sc.nextLine();
		    w.closeTicket(inv);
		    break;
		  default:
			  System.out.println("Invalid choice");
		}
	}

}
