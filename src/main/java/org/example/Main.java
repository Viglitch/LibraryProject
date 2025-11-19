package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //TODO: сделать общие списки
        Book bookHP = new Book(1, "Гарри Поттер", "Джоан Роулинг", true);
        Book bookHP2 = new Book(2, "Гарри Поттер часть 2", "Джоан Роулинг", true);
        Book book451 = new Book(3, "451 градус по фаренгейту", "Рей Бредбери", true);
        Book book100 = new Book(4, "Сто лет одиночества", "Габриэль гарсиа Маркес", true);
        Book bookLotF = new Book(5, "Повелитель мух", "Уильям Голдинг", true);

        Reader Ivan = new Reader(1, "Книголюбов Иван Иванович",  5, 0);
        Reader Maria = new Reader(2, "Книгенко Мария Станиславовна",  1, 1);
        Reader Petr = new Reader(3, "Книгарян Петр Семенович",  0, 4);

        Librarian Andrey = new Librarian(1, "Отпускной Андрей Евгениевич", false);
        Librarian Sofia = new Librarian(2, "Библиотенко София Михайловна", true);
        Librarian Michail = new Librarian(3, "Рабочий Михаил Артемович", true);

        Ivan.sendApplication(bookLotF);
        Ivan.resetPriority();
        //высокий приоритет
        if (Ivan.getPriority() > 0){
            //приборы свободны
            //TODO: автоматическая проверка и определение свободного
            if (Andrey.isAvailable() || Sofia.isAvailable() || Michail.isAvailable()){
                Sofia.scanDocuments(Ivan, bookLotF);
                Sofia.updateBookStatus(bookLotF);
                //TODO: добавить читателю книги
            }
            //приборы заняты
            else{
                //буфер свободен
                if (bookLotF.checkBuffer()){
                    //TODO: это все должно быть где-то не здесь
                    bookLotF.addToBuffer();
                }
                //буфер занят
                else {System.out.println("Мы не можем выдать эту книгу");}
            }
        }
        //приоритет низкий
        else{
            //приборы свободны
            if(Andrey.isAvailable() || Sofia.isAvailable() || Michail.isAvailable()){
                //нет заявок приоритета выше
                //TODO: сортировка по заявкам
                if (){
                    Sofia.scanDocuments(Ivan, bookLotF);
                    Sofia.updateBookStatus(bookLotF);
                    //TODO: добавить читателю книги
                }
                //есть заявки приоритета выше
                else {
                    //буфер свободен
                    if (bookLotF.checkBuffer()){
                        //TODO: это все должно быть где-то не здесь
                        bookLotF.addToBuffer();
                    }
                    //буфер занят
                    else {System.out.println("Мы не можем выдать эту книгу");}
                }
            }
            //приборы заняты
            else {
                //буфер свободен
                if (bookLotF.checkBuffer()){
                    //TODO: это все должно быть где-то не здесь
                    bookLotF.addToBuffer();
                }
                //буфер занят
                else {System.out.println("Мы не можем выдать эту книгу");}
            }
        }
    }
}