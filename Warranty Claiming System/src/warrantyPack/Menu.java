package warrantyPack;
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
						System.out.println("1.Admin 2.Customer");
						System.out.print("Enter Choice:");
						int choice=sc.nextInt();
						sc.nextLine();
	
						UserDao u=new UserDao(); 
						System.out.println("------- LOGIN --------");
						System.out.print("Enter your User name :");
						String uname=sc.nextLine();
						System.out.print("Enter your Password  : ");
						String pwd=sc.nextLine();
						if(choice==1)
						{
							boolean b=u.login(uname,pwd,"admin");
							if(b)
							{
								System.out.println("----Login Successfull----");
								Admin a=new Admin();
								a.actions();
							}
							else
								System.out.println("Wrong User name/Password!\n");
		
		
		
						}
						else if(choice==2)
						{

							boolean b=u.login(uname,pwd);
							if(b)
							{
								System.out.println("----Login Successfull----");
								CustomerDao obj=new CustomerDao();
								obj.setEmail(uname);
								obj.actions();
							}
							else
								System.out.println("Wrong User name/Password!\n");
						}
						else
							System.out.println("Invalid Input");
					}



				else if(c==2) {
						System.out.println("------- SIGN UP --------");
						System.out.print("Enter your Email :");
						String email=sc.nextLine();
						System.out.print("Enter your Password:");
						String pwd=sc.nextLine();
						boolean b= Validation.signUp(email,pwd);
						if(b)System.out.println("----Registration Successfull----");
					}
				else
					System.out.println("Please Enter A Valid Input");
					wantExit();
			}
		catch(Exception ignore ) {
			System.out.println("\nPlease Enter A Valid Input");
			sc.nextLine();
		
		}
  }


}

}
