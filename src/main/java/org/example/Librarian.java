package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Librarian {
    private Random rnd = new Random();
    private int librarianId;
    public String name;
    public volatile boolean available;
    private ScheduledExecutorService scheduler;
    private ArrayList<Integer> exp = new ArrayList<>(Arrays.asList(5,6,7));

    public Librarian(int id, String n, boolean a){
        this.librarianId = id;
        this.name = n;
        this.available = a;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public boolean isAvailable(){return available;}

    public void scanDocuments(Reader r, Book b){
        this.available = false;
        int delay = exp.get(0);
        exp.remove(0);
        scheduler.schedule(() -> {
            this.available = true;
        }, delay, TimeUnit.SECONDS);
    };

    public void updateBookStatus(Book b) {
        b.setAvailability();
    };

    // Метод для завершения работы планировщика
    public void shutdown() {
        scheduler.shutdown();
    }
}
