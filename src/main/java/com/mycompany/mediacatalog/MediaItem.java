
package com.mycompany.mediacatalog;

/**
 * Represents a general media item in the catalog.
 * Stores an ID and title shared by all media types.
 */
public abstract class MediaItem {

    private String id;
    private String title;

    /**
     * Creates a media item with an ID and title.
     *
     * @param id the unique ID of the media item
     * @param title the title of the media item
     */
    public MediaItem(String id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Returns the media item's ID.
     *
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the media item's title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns a description of the media item.
     *
     * @return the media item's summary
     */
    public abstract String summary();
}

