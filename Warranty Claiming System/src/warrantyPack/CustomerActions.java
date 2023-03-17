package warrantyPack;
import java.time.LocalDate;
import java.util.Scanner;
abstract class CustomerActions extends Customer{
  static Scanner sc=new Scanner(System.in);
  
  abstract public boolean insertCustDetails();
   
	public void actions()
	{  while (true) {
		System.out.println("1.Claim Warranty For Product\n2.Track Existing Warranty Claim");
		System.out.print("Enter Choice :");
		int choice=sc.nextInt();
		sc.nextLine();
		if(choice==1)
		{
			registerTicket();
		}
		else if(choice==2)
		{  
			System.out.print("Enter Invoice No:");
			String inv=sc.nextLine();
			WarrantyDao w=new WarrantyDao(inv); 
			boolean f=w.trackClaim();
			if(f)
				System.out.println("Please Ensure You Entered valid registered Invoice No");
				
		}
		else
			System.out.println("Invalid Choice! \n");
		
		
		 System.out.print("Do you want try again (y/n)?? :");
		  char c=sc.nextLine().toUpperCase().charAt(0);
			if(c=='N')
			{
				System.out.println("You have been successfully Logged out ");
				return;
			}
	}
	}
	public void registerTicket() {
		
		Product p=new ProductDao();
		System.out.print("Enter Product Invoice No:");
		p.setInvoiceNo(sc.nextLine());
		System.out.print("Enter the Purchase (dd mm yyyy):");
		int date=sc.nextInt();
		int mon=sc.nextInt();
		int year=sc.nextInt();
		sc.nextLine();
		for (int t=0;t<5;t++)
		{if(!p.setPurDate(date, mon, year))
			System.out.println("Enter a valid Date(dd mm yyyy)");
		else break;
		}
		boolean b= p.isValidProduct();
		if(b) {
			p.display();
			boolean flag=true;
			
			if(!p.getClaimSts().toLowerCase().equals("not claimed") ) {
				System.out.println("\nSorry the Warranty is "+p.getClaimSts());
				WarrantyDao w=new WarrantyDao(p.getInvoiceNo());
				w.trackClaim() ;
				
				flag=false;
			}
			if(LocalDate.now().isAfter(p.getPurdate().plusYears(1) )&& flag)
			{
				System.out.println("OOPS Claim Time Expired...!!");
				flag=false;
			}
			if(flag)
			{
				WarrantyDao obj=new WarrantyDao(p.getInvoiceNo(),this.getEmail() );
			 flag=	obj.getDescription();
			 	if(flag) {getCustDetails(p);	
				System.out.println("warranty registered");}
			 	else
				System.out.println("Error while updating warranty");
			}
		}
		else
		{
			System.out.println("Please Enter a Valid InvoiceNo & Purchase Date"); 
		}
			
		
	
		
		
	}
	
	
	
	private  void getCustDetails(Product p) {
		this.setInvoice(p.getInvoiceNo());
		boolean valid=true;
		for(int i=0;i<5;i++) {
			valid=true;
		System.out.println("Please Enter Your Details For Shipping Purpose !!");
		System.out.print("Enter your Name     :");
		String name=sc.nextLine();
		if(Validation.isValid(name)) {
	     	this.setCustName(name);
		}
		else
		{
			System.out.println("Please Enter a Valid Name");
			valid=false;
			continue;
		}
			
		System.out.print("Enter your Address  :");
		String address=sc.nextLine();
		if(Validation.isValid(name)) {
		this.setAddress(address);
		}
		else {
			System.out.println("please Enter a valid Addresss");
			valid=false;
			continue;
		}
		
			System.out.print("Enter your PinCode  :");
			String pin=sc.nextLine();
			if(Validation.isValidPin(pin))
			{   valid=true;
				this.setPinCode(Integer.parseInt(pin));
				
			}
			else
			{  valid=false;
				System.out.println("Please Enter 6 digit Valid Pin Code");
				continue;
			}
			
		
		
		
			System.out.print("Enter your Phone Number :");
			String ph=sc.nextLine();
			if(Validation.isValidPhone(ph.replace(" ", "")) && valid)
			{
				this.setPhone(ph);
				valid=true;
				
			}
			else
			{
				System.out.println("\n----Please Enter 10 Digit Valid Phone Number---- ");
				valid=false;
				continue;
			}
			if(valid)
			{
				break;
			}
			
		}
		if(valid)
		{
			boolean flag=this.insertCustDetails();
			if(flag)
				System.out.println("Customer details updated\n");
			
		}
		else
		{
			System.out.print("Do you want to try again?? (Y/N):");
			char c=sc.next().toUpperCase().charAt(0);
			if(c=='Y')
			{
				getCustDetails(p);
			}
			else
				return;
		}
	
	}
	
	
	
	
	
	
	
}
