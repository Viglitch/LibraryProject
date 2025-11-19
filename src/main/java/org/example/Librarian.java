package org.example;

public class Librarian {
    private int librarianId;
    private String name;
    private boolean available;
    public Librarian(int id, String n, boolean a){
        this.librarianId = id;
        this.name = n;
        this.available = a;
    }
    public boolean isAvailable(){return available;}
    public void scanDocuments(Reader r, Book b){};
    public void updateBookStatus(Book b) {
        b.setAvailability();
    };
}
