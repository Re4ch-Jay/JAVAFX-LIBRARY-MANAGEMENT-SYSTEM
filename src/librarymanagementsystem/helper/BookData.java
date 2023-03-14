
package librarymanagementsystem.helper;

import java.sql.Date;

public class BookData {
    
    private Integer bookId;
    private String title;
    private String author;
    private String genre;
    private Date date;
    private Double price;
    private String image;

    /**
     * @param bookId
     * @param title
     * @param author
     * @param genre
     * @param date
     * @param price
     * @param image
     */
    // MAKE SURE YOU FOLLOWED THE PARAMETERS THAT I PUT
    public BookData(Integer bookId, String title, String author, String genre
            , Date date, Double price, String image){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.date = date;
        this.price = price;
        this.image = image;
    }

    /**
     * @return bookId
     */
    public Integer getBookId(){
        return bookId;
    }

    /**
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     * @return author
     */
    public String getAuthor(){
        return author;
    }

    /**
     * @return genre
     */
    public String getGenre(){
        return genre;
    }

    /**
     * @return date
     */
    public Date getDate(){
        return date;
    }

    /**
     * @return price
     */
    public Double getPrice(){
        return price;
    }

    /**
     * @return image
     */
    public String getImage(){
        return image;
    }
    
}
