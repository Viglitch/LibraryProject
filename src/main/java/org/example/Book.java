package org.example;

import java.util.List;
import java.util.Queue;

public class Book {
    private int bookID;
    public String title;
    private String author;
    private boolean isAvailable;
    public Queue<Application> applicationBuffer;

    public Book(int idN, String titleN, String authorN, boolean isAvailableN){
        this.bookID = idN;
        this.title = titleN;
        this.author = authorN;
        this.isAvailable = isAvailableN;
    }

    public void addToBuffer(Application app){
        applicationBuffer.add(app);
    }

    public void removeFromBuffer(Application app){
        applicationBuffer.remove(app);
    }
    public boolean sortApplications(){
        for (Application app: applicationBuffer) {
            if (app.applicant.getPriority() > 0) {return true;}
        }
        return false;
    }
    public void setAvailability() {this.isAvailable = !(this.isAvailable);}
    public boolean checkBuffer(){
        if (this.applicationBuffer.size() > 5) {return false;}
        else {return true;}
    }
}
