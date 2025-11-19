package org.example;

import java.util.List;

public class Reader {
    int priority;
    int readerId;
    String name;
    int membershipYears;
    int remarksCount;
    List<Application> currentApplications;
    public Reader(int idN, String nameN, int yearsN, int remN){
        //TODO: id должен определяться автоматически
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
    public void sendApplication(Book b) {
        Application apply = new Application(b, name);
        b.addToBuffer(apply);
    };
    public void cancelApplication(int applicationId) {};
}
