package org.example;

public class ApplicationsProcessor {
    public static void applicationsProcessing(Library lib, Reader reader, Book book) {
        System.out.println("--------- заявка "+reader.name+" на книгу "+book.title+"------------");
        Application curApp = reader.sendApplication(book);
        curApp.setReader(reader);
        print(reader, book, lib, "обработка заявки");
        //высокий приоритет
        print(reader, book, lib, "проверка приоритета");
        if (reader.getPriority() < 3){
            print(reader, book, lib, "проверка приборов");
            //приборы свободны
            if (!lib.getAvailableStaff().isEmpty()) {
                Librarian staff = lib.getAvailableStaff().get(0);
                staff.scanDocuments(reader, book);
                staff.updateBookStatus(book);
                reader.addBook(book);
                print(reader, book, lib, "книга выдается");
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
                else {
                    print(reader, book, lib, "проверка возможности замены");
                    //есть заявки со статусом ниже (можно заменить)
                    if (book.checkReplace(reader.getPriority())){
                        book.applicationBuffer.remove();
                        book.applicationBuffer.add(curApp);
                        print(reader, book, lib, "заявка занесена в очередь");
                    }
                    // все завяки выше
                    else {print(reader, book, lib, "заявка отклонена");}
                }
            }
        }
        //приоритет низкий
        else{
            print(reader, book, lib, "проверка приоритета");
            //приборы свободны
            print(reader, book, lib, "проверка приборов");
            if(!lib.getAvailableStaff().isEmpty()){
                print(reader, book, lib, "проверка буфера по приоритету");
                //нет заявок приоритета выше
                if (!book.sortApplications()){
                    Librarian staff = lib.getAvailableStaff().get(0);
                    staff.scanDocuments(reader, book);
                    staff.updateBookStatus(book);
                    reader.addBook(book);
                    print(reader, book, lib, "книга выдается");
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
                else {print(reader, book, lib, "заявка отклонена");}
            }
        }
    }

    public static void print(Reader reader, Book book, Library lib, String status) {
        System.out.println("| "+reader.getPriority()+"  |  "+book.getBuffer()+"  |  "
                +lib.printStaff(lib.getAvailableStaff())+"  |  "+status+"  |");
    }
}
