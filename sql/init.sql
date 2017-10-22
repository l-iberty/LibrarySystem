drop database library;
create database library;
use library;

create table book (
	id varchar(20) not null,
	name varchar(20) not null,
	author varchar(20),
	price numeric,
	numItems int,
	numSales int,
	primary key(id)
);

create table user (
	name varchar(20) not null,
	passwd varchar(20) not null,
	type int default 0,
	primary key(name)
);

insert into book (id,name,author,price,numItems,numSales) values ('A1234','Computer NetWork','Keith W.Rose',66,100,234);
insert into book (id,name,author,price,numItems,numSales) values ('B1234','Thinking in Java','Bruce Eckel',108,12,145);
insert into book (id,name,author,price,numItems,numSales) values ('C1234','Digital Design','John F.Wakerly',79,56,122);

insert into user (name,passwd) values ('Hacker','12345');
