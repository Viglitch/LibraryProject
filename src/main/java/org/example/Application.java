package org.example;

import java.util.Date;

public class Application {
    int applicationId;
    Date applicationDate;
    ApplicationStatus status;
    enum ApplicationStatus {PENDING, APPROVED, REJECTED, CANCELED};
    Book requestedBook;
    Reader applicant;

    public void submitApplication()
    {
        //TODO: добавить в книгу
    }
    public void cancelApplication()
    {
        //TODO: убрать из книги
    }

    public ApplicationStatus getStatus(){return status;}

}
