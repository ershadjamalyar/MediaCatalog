

\# Media Catalog - Assignment 2



\## Description

A Java console application that manages a catalog of

Books, Movies, and Albums.



The program demonstrates object-oriented programming,

polymorphism, inheritance, and Java file input/output.



\## Features

\- List all media items

\- Add Books, Movies, and Albums

\- Automatically generate unique media IDs

\- Save catalog data to catalog.csv

\- Load saved catalog data when the program starts

\- Export catalog data to catalog.bin

\- Validate numerical input



\## Class Structure

\- MediaItem: Abstract parent class containing ID,

&#x20; title, and the abstract summary() method.

\- Book: Stores author, publication year, and page count.

\- Movie: Stores director, release year, and runtime.

\- Album: Stores artist, release year, and track count.

\- MediaCatalog: Main application with the console menu,

&#x20; CSV file handling, and binary export.



\## Technologies

\- Java

\- Apache NetBeans

\- Maven

\- CSV File I/O

\- DataOutputStream



\## How to Run

1\. Open the MediaCatalog project in Apache NetBeans.

2\. Use a JDK compatible with the version in pom.xml.

3\. Run the MediaCatalog main class.

4\. Select an option from the console menu.



\## Menu

1\. List all items

2\. Add item (Book/Movie/Album)

3\. Save catalog to CSV

4\. Export catalog to binary file

0\. Exit



\## Files

\- catalog.csv: Stores catalog data in text format.

\- catalog.bin: Generated binary export file.

\- pom.xml: Maven project configuration.



\## Assignment

Assignment 2: Media Catalog

Java File I/O and Object-Oriented Programming



