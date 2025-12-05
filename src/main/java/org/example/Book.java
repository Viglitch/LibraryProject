package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class Book {
    private int bookID;
    public String title;
    public String author;
    private boolean isAvailable;
    public Queue<Application> applicationBuffer = new LinkedList<>();
    private List<Book> volumes; // Список томов для многотомных изданий
    private boolean isVolume; // Является ли книгой томом
    private Book parentBook; // Основная книга для томов

    public Book(int idN, String titleN, String authorN, boolean isAvailableN) {
        this.bookID = idN;
        this.title = titleN;
        this.author = authorN;
        this.isAvailable = isAvailableN;
        this.volumes = new ArrayList<>();
        this.isVolume = false;
        this.parentBook = null;
    }

    // Конструктор для томов
    public Book(int idN, String titleN, String authorN, boolean isAvailableN, Book parent) {
        this.bookID = idN;
        this.title = titleN;
        this.author = authorN;
        this.isAvailable = isAvailableN;
        this.isVolume = true;
        this.parentBook = parent;
    }

    // Метод для добавления тома
    public void addVolume(Book volume) {
        this.volumes.add(volume);
    }

    // Метод для получения всех томов
    public List<Book> getVolumes() {
        return new ArrayList<>(volumes);
    }

    // Метод для проверки, является ли книга многотомным изданием
    public boolean isMultiVolume() {
        return !volumes.isEmpty();
    }

    public void addToBuffer(Application app) {
        applicationBuffer.add(app);
    }

    public boolean sortApplications() {
        for (Application app : applicationBuffer) {
            if (app.applicant.getPriority() > 0) {
                return true;
            }
        }
        return false;
    }

    public void setAvailability() {
        this.isAvailable = !(this.isAvailable);
    }

    public boolean checkBuffer() {
        if (this.applicationBuffer.size() >= 1) {
            return false;
        } else {
            return true;
        }
    }

    public String getBuffer() {
        String res = "";
        for (Application app : applicationBuffer) {
            res += app.applicant.name;
        }
        return res;
    };

    public boolean checkReplace(int curPriority){
        for (Application app: applicationBuffer){
            if (app.applicant.getPriority() > curPriority){
                return true;
            }
        }
        return false;
    }

    // Геттер для доступности
    public boolean isAvailable() {
        return isAvailable;
    }
}