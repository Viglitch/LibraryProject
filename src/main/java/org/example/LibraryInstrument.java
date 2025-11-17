package org.example;

public class LibraryInstrument {
    int instrumentId;
    InstrumentType type;
    enum InstrumentType {BARCODE_SCANNER, READER_CARD_SCANNER, PEN, ACCOUNT_BOOK};
    boolean isAvailable;

    private Book scanBarcode(String code){

    };
    private Reader scanReaderCard(){

    };
}
