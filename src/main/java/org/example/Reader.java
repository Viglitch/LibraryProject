package org.example;

import java.util.List;

public class Reader {
    int readerId;
    String name;
    boolean hasSubscription;
    int membershipYears;
    int remarksCount;
    List<Application> currentApplications;

    private int getPriority() {};
    private Application sendApplication(Book b) {};
    private void cancelApplication(int applicationId) {};
}
