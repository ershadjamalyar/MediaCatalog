
package com.mycompany.mediacatalog;

/**
 * Represents a book in the media catalog.
 * Stores the author, publication year, and page count.
 */
public class Book extends MediaItem {

    private String author;
    private int year;
    private int pageCount;

    /**
     * Creates a book with its information.
     *
     * @param id the book's unique ID
     * @param title the book's title
     * @param author the book's author
     * @param year the publication year
     * @param pageCount the number of pages
     */
    public Book(String id, String title, String author,
                int year, int pageCount) {
        super(id, title);
        this.author = author;
        this.year = year;
        this.pageCount = pageCount;
    }

    /**
     * Returns a summary of the book.
     *
     * @return the book's information
     */
    @Override
    public String summary() {
        return "Book [" + getId() + "]: " + getTitle()
                + " by " + author
                + " (" + year + "), "
                + pageCount + " pages";
    }

    /**
     * Returns the author.
     *
     * @return the author's name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the publication year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the number of pages.
     *
     * @return the page count
     */
    public int getPageCount() {
        return pageCount;
    }
}
