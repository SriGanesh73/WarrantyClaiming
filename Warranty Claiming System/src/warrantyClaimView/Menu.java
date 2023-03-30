package warrantyClaimView;
import warrantyClaimController.*;
import warrantyClaimModel.*;
import java.util.*;



public class Menu {

	static Scanner sc=new Scanner(System.in);
	
static void wantExit()
{
	System.out.print("Do you Want to Exit (Y/N) ?? : ");
	char c=sc.nextLine().toUpperCase().charAt(0);
	if(c=='Y')
		{
		System.out.println("EXITING....");
		Db.close();
		sc.close();
		System.exit(0);}
}
	
	
   public static void main(String[] args) {
	while(true) {
			try {	
					System.out.println("Login/SignUp");
					System.out.println("1.Log In 2.Sign Up");
					System.out.print("Enter Choice:");
					int c=sc.nextInt();
					sc.nextLine();

					if(c==1)
					{
						System.out.println("Choose your Login Options ");
	
						
	
						UserDao u=new UserDao(); 
						System.out.println("------- LOGIN --------");
						System.out.print("Enter your User name :");
						u.setuName(sc.nextLine());
						System.out.print("Enter your Password  : ");
						u.setPwd(sc.nextLine());
						boolean b=u.login();
						
							if(b)
							{
								if(u.getuType().toLowerCase().equals("admin")) {
								System.out.println("----Login Successfull----");
								Admin a=new Admin();
								a.actions();
								}
								else if(u.getuType().equals("Customer"))
								{
									System.out.println("----Login Successfull----");
									CustomerDao obj=new CustomerDao();
									obj.setEmail(u.getuName());
									obj.actions();
								
							}
	
							else
								System.out.println("Wrong User name/Password!\n");
						}
						
					}



				else if(c==2) {
						System.out.println("------- SIGN UP --------");
						System.out.print("Enter your Email :");
						String email=sc.nextLine();
						String pwd="";
						while(true) {
						System.out.print("Enter your Password:");
						 pwd=sc.nextLine();
						 System.out.print("Confirm Password");
						 String rpwd=sc.nextLine();
						 if(pwd.equals(rpwd))
							  break;
						 else
							 System.out.println("Password mismatch");
						
						}
						boolean b= Validation.signUp(email,pwd);
						if(b)System.out.println("----Registration Successfull----");
					}
				else
					System.out.println("Please Enter A Valid Input");
					wantExit();
			}
		catch(Exception ignore ) {
			System.out.println("\nPlease Enter A Valid Input ");
			sc.nextLine();
		
		}
  }


}
   
   
  public  static void display(WarrantyDao w)
  {
	  System.out.println("-----Displaying Claim Info-----");
		System.out.println("Invoice no        : "+w.getInvoice() );
		System.out.println("Claimed By User   : "+w.getUserName());
		System.out.println("Issue Description : "+ w.getDesc());
		System.out.println("Current Status    : "+w.getClaimSts());
		System.out.println("Remarks           : "+w.getRemarks());
  }
  public static void display(Product p)
  {
	  
	  	System.out.println("----Product Details----");
	  	System.out.println("Invoice No    :  "+p.getInvoiceNo());
	  	System.out.println("Product Name  :  "+ p.getProductName());
	  	System.out.println("Product Type  :  "+p.getProductType());
	  	System.out.println("Purchase Date :  "+p.getPurdate()+"(YYYY-MM-DD)");
	  	System.out.println("Claim Status  :  "+p.getClaimSts());
	  	

  }
  public static void display(List<WarrantyDao> li)
  {
	  int i=1;
	  System.out.println("\n-----Pending Tickets-----\n");
	  System.out.println(String.format("%-10s %-10s %-15s %-25s %-15s %-40s %-60s", "s.No","Claim ID", "Invoice No", "User Name", "Claim Status", "Problem Description", "Remarks"));
	  System.out.println(String.format("%-10s%-10s %-15s %-25s %-15s %-40s %-60s","---------", "--------", "----------", "---------", "------------", "--------------------", "-------"));
	  for(WarrantyDao w:li)
	  {

		  System.out.println(String.format("%-10s %-10s %-15s %-25s %-15s %-40s %-60s",i++, w.getClaimId(), w.getInvoice(), w.getUserName(), w.getClaimSts(), w.getDesc(), w.getRemarks()));
	  }
  }
  

}


