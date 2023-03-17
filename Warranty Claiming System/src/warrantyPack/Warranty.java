package warrantyPack;
import java.util.Scanner;
abstract  class Warranty {
	static Scanner sc=new Scanner(System.in);
   protected String invoice;
   protected String userName;
   protected String desc;
   abstract public boolean addDesc();
   Warranty()
   {
	   invoice=null;
	   userName=null;
	   desc=null;
   }
   Warranty(String invoice,String userName){
	   this.invoice=invoice;
	   this.userName=userName;
   }
   
   
   
   
   public String getDesc() {
	return desc;
}




public void setDesc(String desc) {
	this.desc = desc;
}





   
   
   
	public boolean getDescription()
	{ 
		for(int i=0;i<5;i++) {
		System.out.print("Enter Problem Description:");
		String d=sc.nextLine();
		boolean flag=Validation.isValid(d);
		if(flag)
			{this.setDesc(d);
			return this.addDesc();
			 
			}
		else
			System.out.println("Enter valid Problem Description\n");
		}		
		return false;
	}







}
