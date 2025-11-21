package org.example;

import java.util.List;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        BookShielf theLibrary = new BookShielf();

        Book bookHP = new Book(1, "Гарри Поттер", "Джоан Роулинг", true);
        Book bookHP2 = new Book(2, "Гарри Поттер часть 2", "Джоан Роулинг", true);
        Book book451 = new Book(3, "451 градус по фаренгейту", "Рей Бредбери", true);
        Book book100 = new Book(4, "Сто лет одиночества", "Габриэль гарсиа Маркес", true);
        Book bookLotF = new Book(5, "Повелитель мух", "Уильям Голдинг", true);
        theLibrary.addBook(bookHP);
        theLibrary.addBook(bookHP2);
        theLibrary.addBook(book451);
        theLibrary.addBook(book100);
        theLibrary.addBook(bookLotF);

        Reader Ivan = new Reader(1, "Книголюбов Иван Иванович",  5, 0);
        Reader Maria = new Reader(2, "Книгенко Мария Станиславовна",  1, 1);
        Reader Petr = new Reader(3, "Книгарян Петр Семенович",  0, 4);
        theLibrary.registerReader(Ivan);
        theLibrary.registerReader(Maria);
        theLibrary.registerReader(Petr);

        Librarian Andrey = new Librarian(1, "Отпускной Андрей Евгениевич", false);
        Librarian Sofia = new Librarian(2, "Библиотенко София Михайловна", true);
        Librarian Michail = new Librarian(3, "Рабочий Михаил Артемович", true);
        theLibrary.registerEmployee(Andrey);
        theLibrary.registerEmployee(Sofia);
        theLibrary.registerEmployee(Michail);

        Random rnd = new Random();
        applicationsProcesser process = new applicationsProcesser();
        Reader reader = theLibrary.readers.get(rnd.nextInt(2));
        Book book = theLibrary.AllBooks.get(rnd.nextInt(4));
        for (int i=0; i<5; i++){
            process.applicationsProcessing(theLibrary,reader, book);
        }
    }
}