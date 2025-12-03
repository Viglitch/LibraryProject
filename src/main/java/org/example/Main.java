package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library theLibrary = new Library();

        Book bookHP = new Book(1, "Гарри Поттер", "Джоан Роулинг", true);
        Book bookHP2 = new Book(2, "Гарри Поттер часть 2", "Джоан Роулинг", true);
        Book bookHP3 = new Book(2, "Гарри Поттер часть 3", "Джоан Роулинг", true);
        Book book451 = new Book(3, "451 градус по фаренгейту", "Рей Бредбери", true);
        Book book100 = new Book(4, "Сто лет одиночества", "Габриэль гарсиа Маркес", true);
        Book bookLotF = new Book(5, "Повелитель мух", "Уильям Голдинг", true);
        theLibrary.addBook(bookHP);
        theLibrary.addBook(bookHP2);
        theLibrary.addBook(bookHP3);
        theLibrary.addBook(book451);
        theLibrary.addBook(book100);
        theLibrary.addBook(bookLotF);

        Reader Ivan = new Reader(1, "Книголюбов иван иванович",  1);
        Reader Maria = new Reader(2, "Книгенко мария станиславовна",  3);
        Reader Petr = new Reader(3, "Книгарян петр семенович",  2);
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
        ApplicationsProcessor process = new ApplicationsProcessor();

        System.out.println("_______________________________________");
        System.out.println("| NСТОЧНNК | БУФЕР | ПРNБОРЫ | СТАТУС |");
        System.out.println("---------------------------------------");

        //happy flow
        process.applicationsProcessing(theLibrary,Ivan,bookHP);

        //приборы заняты, буфер свободен
        Michail.available=false;
         process.applicationsProcessing(theLibrary,Maria,book451);
         //TODO: пакетная обработка (трехтомники)

        //приборы и буфер заняты
        process.applicationsProcessing(theLibrary,Petr,book451);

        //низкий приоритет, но приборы свободны
        Michail.available=true;
        process.applicationsProcessing(theLibrary,Maria,book100);
    }
}