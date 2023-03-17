package warrantyPack;

import java.util.regex.Pattern;

public class Validation {

	public static boolean   signUp(String email,String pwd)
	{
		if(!isValidEmail(email)) {
			System.out.println("Please Enter a Valid Email");
			return false;
		}
		if(!isValidPwd(pwd))
		{
			System.out.println("Please Enter a Valid Password");
		}
		UserDao u=new UserDao();
		 return u.signup(email,pwd);
		
	}
	
	private static boolean isValidEmail(String x)
	{
		return Pattern.matches("[a-z]{1}[a-z0-9\\-\\_\\.]*@[a-z]+[\\.[a-z]+]+", x);
	}
	private static boolean isValidPwd(String x)
	{
		
		int up=0;
		int lo=0;
		int sy=0;
		int no=0;
		for(int i=0;i<x.length();i++)
		{
			if(Character.isUpperCase(x.charAt(i)))
				up++;
			if(Character.isLowerCase(x.charAt(i)))
				lo++;
			if(Character.isDigit(x.charAt(i)))
				no++;
			else
				sy++;
		}
		return(up>0&&lo>0&&sy>0&&no>0&&x.length()>=8);
	}
	public static  boolean isValidPin(String pin)
	{
		return Pattern.matches("[0-9]{6}",pin);
	}
	public static boolean isValidPhone(String ph)
	{
		return Pattern.matches("[9876][0-9]{9}", ph);
	}
	public static boolean isValid(String s)
	{
		return !s.equals("");
	}
	public static boolean isValidInvoice(String s)
	{
		return Pattern.matches("[a-zA-Z][0-9]{10,}",s);
	}
	
}
