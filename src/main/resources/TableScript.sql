
drop table Customer cascade constraints;
drop table Loan cascade constraints;

drop sequence hibernate_sequence;

create sequence hibernate_sequence start with 1006 increment by 1;

create table Loan(
loan_id number(6)  primary key,
loan_amount number(8) not null,
term number(4) not null,
loan_type varchar2(25) not null,
interest_rate number(4)not null
);

insert into Loan values(1001,1200000,15,'HomeLoan',13);
insert into Loan values(1002,700000,5,'CarLoan',9);
insert into Loan values(1003,900000,5,'CarLoan',9);
insert into Loan values(1004,1500000,15,'HomeLoan',13);
insert into Loan values(1005,2200000,10,'HomeLoan',9);

create table Customer(
customer_id number(6) primary key,
name varchar2(25) not null,
mobile_no number(10) not null,
loan_id number(8) references Loan(loan_id) 
); 

insert into Customer values(2001,'Markel',8765476548,1001);
insert into Customer values(2002,'Chris', 9696459375,1002);
insert into Customer values(2003,'James', 8957216439,1003);
insert into Customer values(2004,'Alex',9140814428,null);
insert into Customer values(2005,'Bernard',7941123214,1004);
insert into Customer values(2006,'Michel',8941123214,1005);

select * from Customer;

select * from Loan;

commit;


	