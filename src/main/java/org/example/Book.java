package org.example;

import java.util.List;
import java.util.Queue;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private boolean isAvailable;
    private Queue<Application> applicationBuffer;
    //TODO: какая именно очередь?

    public Book(int idN, String titleN, String authorN, boolean isAvailableN){
        //TODO: id должен определяться автоматически
        this.bookID = idN;
        this.title = titleN;
        this.author = authorN;
        this.isAvailable = isAvailableN;
    }

    public void addToBuffer(Application app){
        applicationBuffer.add(app);
    }

    public void removeFromBuffer(int id){
        //TODO: удаляет заявку с данным id
    }
    public List<Application> sortApplications(){
        List<Application> res;
        //TODO: отсортировать по приоритету
        return res;
    }
    public void setAvailability() {this.isAvailable = !(this.isAvailable);}
    public boolean checkBuffer(){
        if (this.applicationBuffer.size() > 5) {return false;}
        else {return true;}
    }
}
