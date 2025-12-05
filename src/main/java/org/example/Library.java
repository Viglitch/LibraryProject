package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
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

    public List<Librarian> getAvailableStaff() {
        List<Librarian> res = new ArrayList<>();
        for (Librarian staff: librarians){
            if (staff.isAvailable()){res.add(staff);};
        }
        return res;
    }

    public String printStaff(List<Librarian> librarians){
        String res = "";
        for (Librarian staff: librarians){
            if (staff.isAvailable()){res+=staff.name; res+=" ,";}
        }
        if (res.equals("")) {return "Нет";}
        else{return res;}
    };
}
