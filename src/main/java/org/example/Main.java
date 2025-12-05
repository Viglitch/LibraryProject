package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Library theLibrary = new Library();

        Book bookHP = new Book(1, "Гарри Поттер (полный комплект)", "Джоан Роулинг", true);
        Book bookHP1 = new Book(11, "Гарри Поттер часть 1", "Джоан Роулинг", true, bookHP);
        Book bookHP2 = new Book(12, "Гарри Поттер часть 2", "Джоан Роулинг", true, bookHP);
        Book bookHP3 = new Book(13, "Гарри Поттер часть 3", "Джоан Роулинг", true, bookHP);

        bookHP.addVolume(bookHP1);
        bookHP.addVolume(bookHP2);
        bookHP.addVolume(bookHP3);

        Book book451 = new Book(3, "451 градус по фаренгейту", "Рей Бредбери", true);
        Book book100 = new Book(4, "Сто лет одиночества", "Габриэль гарсиа Маркес", true);
        Book bookLotF = new Book(5, "Повелитель мух", "Уильям Голдинг", true);

        theLibrary.addBook(bookHP);
        theLibrary.addBook(bookHP1);
        theLibrary.addBook(bookHP2);
        theLibrary.addBook(bookHP3);
        theLibrary.addBook(book451);
        theLibrary.addBook(book100);
        theLibrary.addBook(bookLotF);

        Reader Ivan = new Reader(1, "Иван",  1);
        Reader Maria = new Reader(2, "Мария",  3);
        Reader Petr = new Reader(3, "Петр",  2);
        theLibrary.registerReader(Ivan);
        theLibrary.registerReader(Maria);
        theLibrary.registerReader(Petr);

        Librarian Sofia = new Librarian(2, "София", true);
        theLibrary.registerEmployee(Sofia);

        ApplicationsProcessor process = new ApplicationsProcessor();
        ApplicationProcessorAuto process2 = new ApplicationProcessorAuto();

        boolean auto = true;

        if (auto){
            ArrayList<ArrayList<String>> data = process2.applicationsProcessing(theLibrary,Ivan,bookHP);
            ArrayList<ArrayList<String>> data2 = process2.applicationsProcessing(theLibrary,Maria,book451);
            ArrayList<ArrayList<String>> data3 = process2.applicationsProcessing(theLibrary,Petr,book451);
            ArrayList<ArrayList<String>> data4 =process2.applicationsProcessing(theLibrary,Maria,book100);
            String[] names = {"СТАТУС"};
            TableWindow twAuto= new TableWindow(data, names);
            twAuto.updateTableData(data2);
            twAuto.updateTableData(data3);
            twAuto.updateTableData(data4);
        }
        else {
            ArrayList<ArrayList<String>> data = process.applicationsProcessing(theLibrary,Ivan,bookHP);
            String[] names = {"ВРЕМЯ", "ИМЯ", "НАЗВАНИЕ", "ИСТОЧНИК", "ПРИБОРЫ", "БУФЕР", "СТАТУС"};
            TableWindow tw= new TableWindow(data, names);
            ArrayList<ArrayList<String>> data2 = process.applicationsProcessing(theLibrary,Maria,book451);
            tw.updateTableData(data2);
            ArrayList<ArrayList<String>> data3 = process.applicationsProcessing(theLibrary,Petr,book451);
            tw.updateTableData(data3);
            ArrayList<ArrayList<String>> data4 =process.applicationsProcessing(theLibrary,Maria,book100);
            tw.updateTableData(data4);
        }
    }
}