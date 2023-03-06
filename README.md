# JAVAFX-LIBRARY-MANAGEMENT-SYSTEM
LMS with JavaFX and MySQL


```

create database book;

create table admin(id int primary key auto_increment, username varchar(255), password varchar(255));


create table book(
	id int primary key auto_increment,
    book_id int,
    title varchar(255),
    author varchar(255),
    genre varchar(255),
    pub_date date,
    price double,
    image varchar(255)
);


create table customer(
	id int primary key auto_increment,
    book_id int,
    title varchar(255),
    author varchar(255),
    genre varchar(255),
    quantity varchar(255),
    price double,
    date date
);

create table customer(
	id int primary key auto_increment,
    customer_id int,
    book_id int,
    title varchar(255),
    author varchar(255),
    genre varchar(255),
    quantity int,
    price double,
    date date
);

create table customer_info(
	id int primary key auto_increment,
    customer_id int,
    total int,
    date date
);

```
