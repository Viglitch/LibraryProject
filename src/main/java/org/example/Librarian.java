package org.example;

import java.util.Random;

public class Librarian {
    private Random rnd = new Random();
    private int librarianId;
    public String name;
    private boolean available;
    public Librarian(int id, String n, boolean a){
        this.librarianId = id;
        this.name = n;
        this.available = a;
    }
    public boolean isAvailable(){return available;}
    public void scanDocuments(Reader r, Book b){
        this.available = false;
        for (int i = 0; i < this.rnd.nextDouble()*10000; i++);
        this.available = true;
    };
    public void updateBookStatus(Book b) {
        b.setAvailability();
    };
}
