create Database WarrantyClaim;
use WarrantyClaim;
/* User table*/
create table User(
userName varchar(35) not null primary key,
pwd varchar(25) not null,
userType varchar(20) not null
);
select * from user;

/*Product Table*/
create table Product(
invoiceNo varchar(20) not null primary key,
pName varchar(30) not null, 
pType varchar(30) not null,
purDate date not null,
claimSts varchar(20) not null default 'Not Claimed'
);

/* Customer table*/
create table Customer (
userName varchar(35) not Null,
invoiceNo varchar(20) not Null  primary Key,
custName varchar(35)not null,
address varchar(200) not null,
pinCode int not null,
phoneNo char(10) not null,
Foreign Key(invoiceNo) REFERENCES Product(invoiceNo),
 Foreign Key(userName) REFERENCES User(userName)
);

/* Warranty Table*/

create table Warranty(
claim_Id int  primary key auto_increment,
invoiceNo varchar(20) not null ,
userName varchar(35) not null,
claimSts varchar(20) not null,
probDesc varchar(200) not null,
 remarks  varchar(200)not null default 'our team will verify your claim',
  Foreign Key(invoiceNo) REFERENCES Product(invoiceNo),
   Foreign Key(userName) REFERENCES User(userName)
);