
package com.mycompany.mediacatalog;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * Main application for managing a catalog of
 * books, movies, and albums.
 */

public class MediaCatalog {

    /**
     * Prevents creating instances of this utility class.
     */
    private MediaCatalog() {
    }

    private static final ArrayList<MediaItem> items = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Starts the media catalog application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
          
        loadCatalog();

        boolean running = true;

        while (running) {
            displayMenu();

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listItems();
                    break;

                case "2":
                addItem();
                break;

                case "3":
                saveCatalog();
                break;

                case "4":
                exportBinary();
                break;

                case "0":
                    System.out.println("Exiting Media Catalog. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the available menu options.
     */
    private static void displayMenu() {
        System.out.println("\n===== MEDIA CATALOG =====");
        System.out.println("1) List all items");
        System.out.println("2) Add item (Book/Movie/Album)");
        System.out.println("3) Save catalog to CSV");
        System.out.println("4) Export catalog to binary file");
        System.out.println("0) Exit");
        System.out.println("=========================");
    }

    /**
     * Displays the summary of every media item.
     */
    private static void listItems() {

        if (items.isEmpty()) {
            System.out.println("The catalog is empty.");
            return;
        }

        System.out.println("\n===== CATALOG ITEMS =====");

        for (MediaItem item : items) {
            System.out.println(item.summary());
        }
    }
    
    /**
     * Prompts the user to add a book, movie, or album
     * to the media catalog.
     */
    private static void addItem() {

        System.out.println("\n===== ADD MEDIA ITEM =====");
        System.out.println("1) Book");
        System.out.println("2) Movie");
        System.out.println("3) Album");

        System.out.print("Select media type: ");
        String type = scanner.nextLine().trim();

        if (!type.equals("1") && !type.equals("2")
                && !type.equals("3")) {
            System.out.println("Invalid media type.");
            return;
        }

        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        String id;

        switch (type) {

            case "1":
                System.out.print("Enter author: ");
                String author = scanner.nextLine().trim();

                System.out.print("Enter publication year: ");
                int bookYear = readPositiveInt();

                System.out.print("Enter page count: ");
                int pageCount = readPositiveInt();

                id = generateId("BK");

                items.add(new Book(id, title, author,
                        bookYear, pageCount));

                break;

            case "2":
                System.out.print("Enter director: ");
                String director = scanner.nextLine().trim();

                System.out.print("Enter release year: ");
                int movieYear = readPositiveInt();

                System.out.print("Enter runtime in minutes: ");
                int runtime = readPositiveInt();

                id = generateId("MV");

                items.add(new Movie(id, title, director,
                        movieYear, runtime));

                break;

            case "3":
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine().trim();

                System.out.print("Enter release year: ");
                int albumYear = readPositiveInt();

                System.out.print("Enter track count: ");
                int trackCount = readPositiveInt();

                id = generateId("AL");

                items.add(new Album(id, title, artist,
                        albumYear, trackCount));

                break;

            default:
                return;
        }

        System.out.println("Item added successfully! ID: " + id);
    }

    /**
     * Reads a positive integer from the user.
     * Repeats until a valid number is entered.
     *
     * @return a positive integer
     */
    private static int readPositiveInt() {

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(input);

                if (number > 0) {
                    return number;
                }

                System.out.print(
                        "Please enter a positive number: ");

            } catch (NumberFormatException e) {
                System.out.print(
                        "Invalid number. Please try again: ");
            }
        }
    }

    /**
     * Generates a unique ID for a media type.
     * Uses the highest existing numeric ID plus one.
     *
     * @param prefix the media type prefix
     * @return a new unique media ID
     */
    private static String generateId(String prefix) {

        int maxNumber = 0;

        for (MediaItem item : items) {

            String id = item.getId();

            if (id.startsWith(prefix + "-")) {

                String numberPart = id.substring(
                        prefix.length() + 1);

                try {
                    int number = Integer.parseInt(numberPart);

                    if (number > maxNumber) {
                        maxNumber = number;
                    }

                } catch (NumberFormatException e) {
                    // Ignore IDs with invalid numeric portions.
                }
            }
        }

        return String.format("%s-%03d", prefix, maxNumber + 1);
    }

    /**
     * Saves all media items to catalog.csv.
     * Overwrites the existing file with current catalog data.
     */
    private static void saveCatalog() {

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter("catalog.csv"))) {

            for (MediaItem item : items) {

                String line;

                if (item instanceof Book) {

                    Book book = (Book) item;

                    line = "Book,"
                            + csvField(book.getId()) + ","
                            + csvField(book.getTitle()) + ","
                            + csvField(book.getAuthor()) + ","
                            + book.getYear() + ","
                            + book.getPageCount();

                } else if (item instanceof Movie) {

                    Movie movie = (Movie) item;

                    line = "Movie,"
                            + csvField(movie.getId()) + ","
                            + csvField(movie.getTitle()) + ","
                            + csvField(movie.getDirector()) + ","
                            + movie.getYear() + ","
                            + movie.getRuntimeMinutes();

                } else if (item instanceof Album) {

                    Album album = (Album) item;

                    line = "Album,"
                            + csvField(album.getId()) + ","
                            + csvField(album.getTitle()) + ","
                            + csvField(album.getArtist()) + ","
                            + album.getYear() + ","
                            + album.getTrackCount();

                } else {
                    continue;
                }

                writer.write(line);
                writer.newLine();
            }

            System.out.println(
                    "Catalog saved successfully to catalog.csv!");

        } catch (IOException e) {
            System.out.println(
                    "Error saving catalog: " + e.getMessage());
        }
    }

    /**
     * Escapes text so commas, quotes, and line breaks
     * can be stored safely in a CSV field.
     *
     * @param value the original text
     * @return the CSV-formatted text
     */
    private static String csvField(String value) {

        if (value.contains(",")
                || value.contains("\"")
                || value.contains("\n")
                || value.contains("\r")) {

            return "\"" + value.replace("\"", "\"\"") + "\"";
        }

        return value;
    }

    /**
     * Loads media items from catalog.csv if the file exists.
     */
    private static void loadCatalog() {

        File file = new File("catalog.csv");

        if (!file.exists()) {
            System.out.println("No existing catalog found.");
            return;
        }

        ArrayList<MediaItem> loadedItems = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;
            int lineNumber = 0;

            
while ((line = reader.readLine()) != null) {
    lineNumber++;

    if (line.isEmpty()) {
        continue;
    }

    StringBuilder record = new StringBuilder(line);

    while (!isCompleteCsvRecord(record.toString())) {

        String nextLine = reader.readLine();

        if (nextLine == null) {
            throw new IOException(
                    "Incomplete CSV record starting at line "
                    + lineNumber);
        }

        record.append("\n").append(nextLine);
        lineNumber++;
    }

    line = record.toString();


                try {
                    ArrayList<String> fields = parseCsvLine(line);

                    if (fields.size() != 6) {
                        throw new IllegalArgumentException(
                                "Expected 6 CSV fields.");
                    }

                    String type = fields.get(0);
                    String id = fields.get(1);
                    String title = fields.get(2);
                    String creator = fields.get(3);
                    int year = Integer.parseInt(fields.get(4));
                    int count = Integer.parseInt(fields.get(5));

                    if (id.isEmpty() || title.isEmpty()
                            || creator.isEmpty()
                            || year <= 0 || count <= 0) {
                        throw new IllegalArgumentException(
                                "Invalid media item values.");
                    }

                    MediaItem item;

                    switch (type) {
                        case "Book":
                            item = new Book(id, title, creator,
                                    year, count);
                            break;

                        case "Movie":
                            item = new Movie(id, title, creator,
                                    year, count);
                            break;

                        case "Album":
                            item = new Album(id, title, creator,
                                    year, count);
                            break;

                        default:
                            throw new IllegalArgumentException(
                                    "Unknown media type: " + type);
                    }

                    loadedItems.add(item);

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid CSV data at line "
                            + lineNumber + ": " + e.getMessage());
                    System.out.println(
                            "Catalog loading stopped to protect saved data.");
                    return;
                }
            }

            items.clear();
            items.addAll(loadedItems);

            System.out.println("Loaded " + items.size()
                    + " item(s) from catalog.csv.");

        } catch (IOException e) {
            System.out.println(
                    "Error loading catalog: " + e.getMessage());
        }
    }

    /**
     * Parses a CSV line, supporting quoted fields,
     * commas within text, and escaped quotation marks.
     *
     * @param line the CSV line to parse
     * @return the parsed fields
     */
    private static ArrayList<String> parseCsvLine(String line) {

        ArrayList<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        boolean closedQuote = false;

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length()
                            && line.charAt(i + 1) == '"') {
                        current.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                        closedQuote = true;
                    }
                } else {
                    current.append(c);
                }

            } else if (c == ',') {
                fields.add(current.toString());
                current.setLength(0);
                closedQuote = false;

            } else if (c == '"' && current.length() == 0
                    && !closedQuote) {
                inQuotes = true;

            } else if (closedQuote || c == '"') {
                throw new IllegalArgumentException(
                        "Invalid quotation marks.");

            } else {
                current.append(c);
            }
        }

        if (inQuotes) {
            throw new IllegalArgumentException(
                    "Unclosed quoted CSV field.");
        }

        fields.add(current.toString());
        return fields;
    }

    /**
     * Exports all media items to catalog.bin
     * using Java's DataOutputStream.
     */
    private static void exportBinary() {

        try (DataOutputStream output =
                new DataOutputStream(
                        new FileOutputStream("catalog.bin"))) {

            int recordCount = 0;

            for (MediaItem item : items) {
            if (item instanceof Book
                    || item instanceof Movie
                    || item instanceof Album) {
                recordCount++;
            }
          }

output.writeInt(recordCount);

            for (MediaItem item : items) {

                if (item instanceof Book) {

                    Book book = (Book) item;

                    output.writeUTF("Book");
                    output.writeUTF(book.getId());
                    output.writeUTF(book.getTitle());
                    output.writeUTF(book.getAuthor());
                    output.writeInt(book.getYear());
                    output.writeInt(book.getPageCount());

                } else if (item instanceof Movie) {

                    Movie movie = (Movie) item;

                    output.writeUTF("Movie");
                    output.writeUTF(movie.getId());
                    output.writeUTF(movie.getTitle());
                    output.writeUTF(movie.getDirector());
                    output.writeInt(movie.getYear());
                    output.writeInt(movie.getRuntimeMinutes());

                } else if (item instanceof Album) {

                    Album album = (Album) item;

                    output.writeUTF("Album");
                    output.writeUTF(album.getId());
                    output.writeUTF(album.getTitle());
                    output.writeUTF(album.getArtist());
                    output.writeInt(album.getYear());
                    output.writeInt(album.getTrackCount());
                }
            }

            System.out.println(
                    "Catalog exported successfully to catalog.bin!");

        } catch (IOException e) {
            System.out.println(
                    "Error exporting catalog: " + e.getMessage());
        }
    }

    /**
     * Checks whether a CSV record has balanced
     * quotation marks and is ready to parse.
     *
     * @param record the CSV record to check
     * @return true if all quoted fields are closed
     */
    private static boolean isCompleteCsvRecord(String record) {

        boolean inQuotes = false;

        for (int i = 0; i < record.length(); i++) {

            char c = record.charAt(i);

            if (c == '"') {

                if (inQuotes
                        && i + 1 < record.length()
                        && record.charAt(i + 1) == '"') {

                    i++;

                } else {
                    inQuotes = !inQuotes;
                }
            }
        }

        return !inQuotes;
    }

}

