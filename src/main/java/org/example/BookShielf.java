package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookShielf {
    public List<Book> AllBooks = new ArrayList<>();
    public List<Reader> readers = new ArrayList<>();
    public List<Librarian> librarians = new ArrayList<>();

    public void addBook(Book newBook){
        AllBooks.add(newBook);
    }

    public void registerReader(Reader newReader){
        readers.add(newReader);
    }
    public void registerEmployee(Librarian newLibrarian){
        librarians.add(newLibrarian);
    }

    public boolean processApplication(Application myApp){
        //TODO: логика заявок
        return true;
    }

    //public List<Book> getAvailableBooks(){
    //    List<Book> res;
        //TODO: а это вообще надо?
    //   return res;
    //}

    public Librarian getAvailableStaff() {
        for (Librarian staff: librarians){
            if (staff.isAvailable()){return staff;};
        }
        return null;
    }
}
