
package com.mycompany.mediacatalog;

/**
 * Represents a music album in the media catalog.
 * Stores the artist, release year, and number of tracks.
 */
public class Album extends MediaItem {

    private String artist;
    private int year;
    private int trackCount;

    /**
     * Creates an album with its information.
     *
     * @param id the album's unique ID
     * @param title the album's title
     * @param artist the album's artist
     * @param year the release year
     * @param trackCount the number of tracks
     */
    public Album(String id, String title, String artist,
                 int year, int trackCount) {
        super(id, title);
        this.artist = artist;
        this.year = year;
        this.trackCount = trackCount;
    }

    /**
     * Returns a summary of the album.
     *
     * @return the album's information
     */
    @Override
    public String summary() {
        return "Album [" + getId() + "]: " + getTitle()
                + " by " + artist
                + " (" + year + "), "
                + trackCount + " tracks";
    }

    /**
     * Returns the artist.
     *
     * @return the artist's name
     */
    public String getArtist() {
        return artist;
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
     * Returns the number of tracks.
     *
     * @return the track count
     */
    public int getTrackCount() {
        return trackCount;
    }
}
