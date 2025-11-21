package org.example;

import java.util.List;

public class Reader {
    int priority;
    int readerId;
    String name;
    int membershipYears;
    int remarksCount;
    List<Application> currentApplications;
    List<Book> currentBooks;
    public Reader(int idN, String nameN, int yearsN, int remN){
        this.readerId = idN;
        this.name = nameN;
        this.membershipYears = yearsN;
        this.remarksCount = remN;

    };
    public int getPriority () {return priority;}
    public void resetPriority() {
        int res = 0;
        res+=membershipYears;
        res-=remarksCount;
        this.priority = res;
    };
    public Application sendApplication(Book b) {
        Application apply = new Application(b, name);
        return apply;
    };
    public void cancelApplication(int applicationId) {};

    public void addBook(Book b){currentBooks.add(b);};
}
