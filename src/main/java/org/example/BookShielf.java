package org.example;

import java.util.List;
import java.util.Map;

public class BookShielf {
    Map<Integer, Book> AllBooks;
    public List<Reader> readers;
    List<Librarian> librarians;

    public void addBook(Book newBook){
        AllBooks.put(AllBooks.size(), newBook);
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
