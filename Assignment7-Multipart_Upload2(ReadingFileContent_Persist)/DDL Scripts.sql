-- ORACLE --

create table fileuploadjsonmodel(productid int primary key ,name varchar(255),processor varchar(255),ram int,color varchar(255),price FLOAT,FILEGRPID int);
create table fileuploadxmlmodel(name varchar(255),location varchar(255));
create table fileuploadcsvmodel(sid int,bank_name varchar2(255),bank_acc_no varchar2(255),acc_type varchar2(255),bank_location varchar2(255),company varchar2(255));
create table fileuploadimgmodel(id varchar2(255),name varchar2(255),gender varchar2(255),mobile int,address varchar2(255),bank varchar2(255),type varchar2(255),
company varchar2(255),signature varchar2(255),place varchar2(255));
create table fileuploadpdfmodel(name varchar2(255),empid varchar2(255),location varchar2(255),productid int,orderid varchar2(255),invoiceno int,phone int,
address varchar2(255),productname varchar2(255),price float,discount varchar2(255),gst varchar2(255),total float,modeofpayment varchar2(255));
create table fileuploadexcelmodel (no float,name varchar2(255),company varchar2(255),totalexperience float,salary float);
create table companylists(companylists varchar2(255),companycount float);









-- mySql --

create table fileuploadjsonmodel(ProductId BIGINT primary key,Name varchar(255),Processor varchar(255),RAM int,Color varchar(255),Price float,filegrpid int);
create table fileuploadxmlmodel(name varchar(255),location varchar(255));
create table fileuploadcsvmodel(sid int,bank_name varchar(255),bank_acc_no varchar(255),acc_type varchar(255),bank_location varchar(255),company varchar(255));
create table fileuploadimgmodel(id varchar(255),name varchar(255),gender varchar(255),mobile int8,address varchar(255),bank varchar(255),type varchar(255),
company varchar(255),signature varchar(255),place varchar(255));
create table fileuploadpdfmodel(name varchar(255),empid varchar(255),location varchar(255),productid int,orderid varchar(255),invoiceno int,phone int,
address varchar(255),productname varchar(255),price double,discount varchar(255),gst varchar(255),total double,modeofpayment varchar(255));
create table fileuploadexcelmodel (no double,name varchar(255),company varchar(255),totalexperience double,salary double);
create table companylists(companylists varchar(255),companycount double);