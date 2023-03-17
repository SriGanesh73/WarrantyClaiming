package warrantyPack;

import java.time.LocalDate;
abstract class Product {
   private String invoiceNo;
   private String productName;
   private String productType;
   private LocalDate purDate;
   private String claimSts;

public String getInvoiceNo() {
	return invoiceNo;
}
public void setInvoiceNo(String invoiceNo) {
	this.invoiceNo = invoiceNo;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getProductType() {
	return productType;
}
public void setProductType(String productType) {
	this.productType = productType;
}
   
public LocalDate getPurdate() {
	return this.purDate;
}
public boolean setPurDate(int day,int month,int year) {
 try{
	 this.purDate=LocalDate.of(year, month,day );
	 return true;
 	}
 catch(Exception e ){
	 System.out.println("Enter a Valid Date");
	 return false;
 	}
}

public String getClaimSts() {
	return claimSts;
}
public void setClaimSts(String claimSts) {
	this.claimSts = claimSts;
}
public boolean isValidProduct() {return false;}   
public void display()
{
	System.out.println("----Product Details----");
	System.out.println("Invoice No    :  "+this.invoiceNo);
	System.out.println("Product Name  :  "+ this.productName);
	System.out.println("Product Type  :  "+this.productType);
	System.out.println("Purchase Date :  "+this.purDate+"(YYYY-MM-DD)");
	System.out.println("Claim Status  :  "+this.claimSts);
	
}


}
