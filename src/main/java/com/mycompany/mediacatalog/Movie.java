
package com.mycompany.mediacatalog;

/**
 * Represents a movie in the media catalog.
 * Stores the director, release year, and runtime.
 */
public class Movie extends MediaItem {

    private String director;
    private int year;
    private int runtimeMinutes;

    /**
     * Creates a movie with its information.
     *
     * @param id the movie's unique ID
     * @param title the movie's title
     * @param director the movie's director
     * @param year the release year
     * @param runtimeMinutes the movie's runtime in minutes
     */
    public Movie(String id, String title, String director,
                 int year, int runtimeMinutes) {
        super(id, title);
        this.director = director;
        this.year = year;
        this.runtimeMinutes = runtimeMinutes;
    }

    /**
     * Returns a summary of the movie.
     *
     * @return the movie's information
     */
    @Override
    public String summary() {
        return "Movie [" + getId() + "]: " + getTitle()
                + " directed by " + director
                + " (" + year + "), "
                + runtimeMinutes + " minutes";
    }

    /**
     * Returns the director.
     *
     * @return the director's name
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns the release year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the movie's runtime.
     *
     * @return the runtime in minutes
     */
    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }
}
