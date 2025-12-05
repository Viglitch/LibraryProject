package org.example;

import java.time.LocalDate;

public class Application {
    int applicationId;
    LocalDate applicationDate;
    ApplicationStatus status;
    enum ApplicationStatus {PENDING, APPROVED, REJECTED, CANCELED};
    Book requestedBook;
    Reader applicant;

    public Application(Book book){
        this.applicationId = book.applicationBuffer.size() + 1;
        this.applicationDate = LocalDate.now();
        this.status = ApplicationStatus.PENDING;
        this.requestedBook = book;
    }

    public void setReader(Reader reader){
        this.applicant = reader;
    }

}
