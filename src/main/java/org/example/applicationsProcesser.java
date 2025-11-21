package org.example;

public class applicationsProcesser {
    public static void applicationsProcessing(BookShielf lib, Reader reader, Book book) {
        Application curApp = reader.sendApplication(book);
        reader.resetPriority();
        System.out.println("Обрабатываю заявку "+reader.name+" на книгу "+book.title);
        //высокий приоритет
        if (reader.getPriority() > 0){
            System.out.println("Приоритет читателя: " + reader.getPriority() + "(высокий)");
            //приборы свободны
            if (lib.getAvailableStaff() != null) {
                Librarian staff = lib.getAvailableStaff();
                staff.scanDocuments(reader, book);
                staff.updateBookStatus(book);
                reader.addBook(book);
                System.out.println("Работник "+ staff.name +" завершил обслуживание");
            }
            //приборы заняты
            else{
                //буфер свободен
                if (book.checkBuffer()){
                    book.addToBuffer(curApp);
                    System.out.println("Ваша заявка добавлена в очередь. Ожидайте");
                }
                //буфер занят
                else {System.out.println("К сожалению, мы не можем выдать Вам эту книгу");}
            }
        }
        //приоритет низкий
        else{
            System.out.println("Приоритет читателя: " + reader.getPriority() + "(низкий)");
            //приборы свободны
            if(lib.getAvailableStaff() != null){
                //нет заявок приоритета выше
                if (!book.sortApplications()){
                    Librarian staff = lib.getAvailableStaff();
                    staff.scanDocuments(reader, book);
                    staff.updateBookStatus(book);
                    reader.addBook(book);
                    System.out.println("Работник "+ staff.name +" завершил обслуживание");
                }
                //есть заявки приоритета выше
                else {
                    //буфер свободен
                    if (book.checkBuffer()){
                        book.addToBuffer(curApp);
                        System.out.println("Ваша заявка добавлена в очередь. Ожидайте");
                    }
                    //буфер занят
                    else {System.out.println("К сожалению, мы не можем выдать Вам эту книгу");}
                }
            }
            //приборы заняты
            else {
                //буфер свободен
                if (book.checkBuffer()){
                    book.addToBuffer(curApp);
                    System.out.println("Ваша заявка добавлена в очередь. Ожидайте");
                }
                //буфер занят
                else {System.out.println("К сожалению, мы не можем выдать Вам эту книгу");}
            }
        }
    }
}
