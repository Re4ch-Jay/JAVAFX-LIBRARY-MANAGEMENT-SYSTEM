# JAVAFX-LIBRARY-MANAGEMENT-SYSTEM
LMS with JavaFX and MySQL


```sql

create database book;
use book;
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

# Insert dummy data of book

INSERT INTO book(book_id, title, author, genre, pub_date, price, image) 
VALUES 
(1, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', '1925-04-10', 9.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(2, 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', '1960-07-11', 8.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(3, '1984', 'George Orwell', 'Science Fiction', '1949-06-08', 10.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(4, 'Pride and Prejudice', 'Jane Austen', 'Romance', '1813-01-28', 7.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(5, 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', '1951-07-16', 6.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(6, 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy', '1937-09-21', 11.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(7, 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', '1954-07-29', 14.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(8, 'The Da Vinci Code', 'Dan Brown', 'Mystery', '2003-03-18', 12.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(9, 'The Girl with the Dragon Tattoo', 'Stieg Larsson', 'Mystery', '2005-08-19', 9.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(10, 'The Hunger Games', 'Suzanne Collins', 'Science Fiction', '2008-09-14', 8.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(11, 'Harry Potter and the Philosopher\'s Stone', 'J.K. Rowling', 'Fantasy', '1997-06-26', 10.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(12, 'The Adventures of Sherlock Holmes', 'Arthur Conan Doyle', 'Mystery', '1892-10-14', 7.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(13, 'The Picture of Dorian Gray', 'Oscar Wilde', 'Fiction', '1890-07-01', 6.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(14, 'The War of the Worlds', 'H.G. Wells', 'Science Fiction', '1898-04-01', 8.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg'),
(15, 'The Adventures of Tom Sawyer', 'Mark Twain', 'Adventure', '1876-12-01', 5.99, 'C:\\Users\\Reach\\Downloads\\logo.jpg');


create table customer(
  id int primary key auto_increment,
  customer_id int,
    book_id int,
    title varchar(255),
    author varchar(255),
    genre varchar(255),
    quantity varchar(255),
    price double,
    date date
);


create table customer_info(
  id int primary key auto_increment,
    customer_id int,
    total int,
    date date
);

# Insert dummy data of customer_info

INSERT INTO customer_info (customer_id, total, date) 
VALUES 
(1, 23.45, '2023-03-09'),
(2, 14.99, '2023-03-08'),
(3, 87.65, '2023-03-07'),
(4, 42.50, '2023-03-06'),
(5, 56.78, '2023-03-05'),
(6, 33.25, '2023-03-04'),
(7, 19.99, '2023-03-03'),
(8, 75.20, '2023-03-02'),
(9, 11.99, '2023-03-01'),
(10, 67.80, '2023-02-28');

```
