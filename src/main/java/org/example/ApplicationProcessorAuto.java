package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalTime;

public class ApplicationProcessorAuto {
    public static ArrayList<ArrayList<String>> applicationsProcessing(Library lib, Reader reader, Book book) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        // Проверяем, является ли книга многотомным изданием
        if (book.isMultiVolume()) {
            // Получаем все тома
            ArrayList<Book> volumes = new ArrayList<>(book.getVolumes());

            // Проверяем доступность всех томов
            boolean allVolumesAvailable = true;
            for (Book volume : volumes) {
                if (!volume.isAvailable()) {
                    allVolumesAvailable = false;
                    break;
                }
            }

            if (!allVolumesAvailable) {
                return data;
            }

            // Обрабатываем каждый том
            for (Book volume : volumes) {

                // Создаем заявку на каждый том
                Application curApp = reader.sendApplication(volume);
                curApp.setReader(reader);

                // Проверяем доступность библиотекарей
                if (!lib.getAvailableStaff().isEmpty()) {
                    Librarian staff = lib.getAvailableStaff().get(0);
                    staff.scanDocuments(reader, volume);
                    staff.updateBookStatus(volume);
                    reader.addBook(volume);
                    print(reader, volume, lib, " получил ", data);
                } else {
                    // Если нет свободных библиотекарей, добавляем в буфер
                    if (volume.checkBuffer()) {
                        volume.addToBuffer(curApp);
                        print(reader, volume, lib, " встал в очередь на ", data);
                    } else {
                        print(reader, volume, lib, " встал в очередь на ", data);
                    }
                }

                // Небольшая пауза между обработкой томов
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else
        {
            Application curApp = reader.sendApplication(book);
            curApp.setReader(reader);
            //высокий приоритет
            if (reader.getPriority() < 3) {
                //приборы свободны
                if (!lib.getAvailableStaff().isEmpty()) {
                    Librarian staff = lib.getAvailableStaff().get(0);
                    staff.scanDocuments(reader, book);
                    staff.updateBookStatus(book);
                    reader.addBook(book);
                    print(reader, book, lib, " получил ", data);
                }
                //приборы заняты
                else {
                    //буфер свободен
                    if (book.checkBuffer()) {
                        book.addToBuffer(curApp);
                        print(reader, book, lib, " встал в очередь на ", data);
                    }
                    //буфер занят
                    else {
                        //есть заявки со статусом ниже (можно заменить)
                        if (book.checkReplace(reader.getPriority())) {
                            book.applicationBuffer.remove();
                            book.applicationBuffer.add(curApp);
                            print(reader, book, lib, " встал в очередь на ", data);
                        }
                        // все завяки выше
                        else {
                            print(reader, book, lib, " отказано в ", data);
                        }
                    }
                }
            }
            //приоритет низкий
            else {
                //приборы свободны
                if (!lib.getAvailableStaff().isEmpty()) {
                    //нет заявок приоритета выше
                    if (!book.sortApplications()) {
                        Librarian staff = lib.getAvailableStaff().get(0);
                        staff.scanDocuments(reader, book);
                        staff.updateBookStatus(book);
                        reader.addBook(book);
                        print(reader, book, lib, " получил ", data);
                    }
                    //есть заявки приоритета выше
                    else {
                        //буфер свободен
                        if (book.checkBuffer()) {
                            book.addToBuffer(curApp);
                            print(reader, book, lib, " отказано в ", data);
                        }
                        //буфер занят
                        else {
                            print(reader, book, lib, " отказано в", data);
                        }
                    }
                }
                //приборы заняты
                else {
                    //буфер свободен
                    if (book.checkBuffer()) {
                        book.addToBuffer(curApp);
                        print(reader, book, lib, " встал в очередь на ", data);
                    }
                    //буфер занят
                    else {
                        print(reader, book, lib, " отказано в ", data);
                    }
                }
            }
        }
        return data;
    }

    public static void print(Reader reader, Book book, Library lib, String status, ArrayList<ArrayList<String>> data) {
        ArrayList<String> newRow = new ArrayList<>(Arrays.asList(
                reader.name + status + book.title
        ));
        data.add(newRow);
    }
}
