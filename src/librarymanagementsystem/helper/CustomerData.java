
package librarymanagementsystem.helper;

import java.sql.Date;

public class CustomerData {
    
    private Integer customerId;
    private Integer bookId;
    private String title;
    private String author;
    private String genre;
    private Integer quantity;
    private Double price;
    private Date date;

    /**
     * @param customerId
     * @param bookId
     * @param title
     * @param author
     * @param genre
     * @param quantity
     * @param price
     * @param date
     */
    public CustomerData(Integer customerId, Integer bookId, String title, String author
            , String genre, Integer quantity, Double price, Date date){
        this.customerId = customerId;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    /**
     * @return CustomerID
     */
    public Integer getCustomerId(){
        return customerId;
    }

    /**
     * @return BookId
     */
    public Integer getBookId(){
        return bookId;
    }

    /**
     * @return Title
     */
    public String getTitle(){
        return title;
    }

    /**
     * @return Author
     */
    public String getAuthor(){
        return author;
    }

    /**
     * @return Genre
     */
    public String getGenre(){
        return genre;
    }

    /**
     * @return Quantity
     */
    public Integer getQuantity(){
        return quantity;
    }

    /**
     * @return price
     */
    public Double getPrice(){
        return price;
    }

    /**
     * @return Date
     */
    public Date getDate(){
        return date;
    }
    
}
