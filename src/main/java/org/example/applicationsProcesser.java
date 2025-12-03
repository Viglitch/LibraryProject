package org.example;

import java.util.Scanner;

public class applicationsProcesser {
    public static void applicationsProcessing(BookShielf lib, Reader reader, Book book) {
        Application curApp = reader.sendApplication(book);
        curApp.setReader(reader);
        print(reader, book, lib, "обработка заявки");
        //высокий приоритет
        print(reader, book, lib, "проверка приоритета");
        if (reader.getPriority() == 1){
            print(reader, book, lib, "проверка приборов");
            //приборы свободны
            if (lib.getAvailableStaff() != null) {
                Librarian staff = lib.getAvailableStaff();
                staff.scanDocuments(reader, book);
                staff.updateBookStatus(book);
                reader.addBook(book);
                print(reader, book, lib, "обслуживание завершено");
            }
            //приборы заняты
            else{
                print(reader, book, lib, "проверка буфера");
                //буфер свободен
                if (book.checkBuffer()){
                    book.addToBuffer(curApp);
                    print(reader, book, lib, "заявка добавлена в очередь");
                }
                //буфер занят
                //TODO: проверка приоритета
                else {print(reader, book, lib, "заявка отклонена");}
            }
        }
        //приоритет низкий
        else{
            print(reader, book, lib, "проверка приоритета");
            //приборы свободны
            print(reader, book, lib, "проверка приборов");
            if(lib.getAvailableStaff() != null){
                print(reader, book, lib, "проверка буфера по приоритету");
                //нет заявок приоритета выше
                if (!book.sortApplications()){
                    Librarian staff = lib.getAvailableStaff();
                    staff.scanDocuments(reader, book);
                    staff.updateBookStatus(book);
                    reader.addBook(book);
                    print(reader, book, lib, "обслуживание завершено");
                }
                //есть заявки приоритета выше
                else {
                    print(reader, book, lib, "проверка свободных мест в буфере");
                    //буфер свободен
                    if (book.checkBuffer()){
                        book.addToBuffer(curApp);
                        print(reader, book, lib, "заявка добавлена в очередь");
                    }
                    //буфер занят
                    else {print(reader, book, lib, "заявка отклонена");}
                }
            }
            //приборы заняты
            else {
                //буфер свободен
                print(reader, book, lib, "проверка свободных мест в буфере");
                if (book.checkBuffer()){
                    book.addToBuffer(curApp);
                    print(reader, book, lib, "заявка добавлена в очередь");
                }
                //буфер занят
                else {print(reader, book, lib, "проверка отклонена");}
            }
        }
    }

    public static void print(Reader reader, Book book, BookShielf lib, String status) {
        System.out.println("| "+reader.getPriority()+"        |  "+book.getBuffer()+"  |  "
                +lib.getAvailableStaff().name+"  |  "+status+"      |");
    }
}
