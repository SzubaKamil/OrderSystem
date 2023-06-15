package com.company.ordersystem.printer;

import com.company.ordersystem.printer.dictionary.Dictionary;

class DictionariesGenerator {

    protected static Dictionary getPolishDictionary (){
        return new Dictionary(
                "Zlecenie_druku",
                "Aktualizacja",
                "AKTUALIZACJA",
                "zlecenia",
                "ZLECENIE USŁUG POLIGRAFICZNYCH",
                "Niniejszym zlecam firmie",
                "wykonanie druku zgodnie z ustalonymi parametrami",
                "Termin dostarczenia materiałów",
                "Termin wykonania",
                "Ilość", "egz.",
                "Cena", "zł netto /egz.", "zł netto /nakład",
                "Należność",
                "Dane do wystawienia FV",
                "Adres dostawy",
                "Kontakt",
                "Nazwa",
                "Kod produktu",
                "Format",
                "Papier",
                "Kolor",
                "Ilość stron",
                "Perforacja",
                "Pokrycie zadruku",
                "Klapka",
                "Okienko",
                "Klejenie",
                "Uszlachetnienia",
                "Papier środek",
                "Kolor środek",
                "Okladka",
                "Papier okładka",
                "Kolor okładka",
                "Dodatkowe",
                "Uwagi" );
    }

    protected static Dictionary getEnglishDictionary (){
        return new Dictionary(
                "Printing Order",
                "Update",
                "UPDATE",
                "order",
                "PRINTING JOB ORDER",
                "I commission the contractor:",
                "Printing according to the following parameters:",
                "Date of delivery of materials",
                "Deadline of delivery",
                "Quantity", "pcs.",
                "Price", "PLN net /pcs..", "PLN ney /all",
                "Payment term",
                "Data to invoice",
                "Delivery address",
                "Contact",
                "Product name",
                "Product code",
                "Format",
                "Paper",
                "Color",
                "Number of pages",
                "Perforation",
                "Covering",
                "Flap",
                "Window",
                "Glued",
                "Printing finishing",
                "Paper inside",
                "Color inside",
                "Cover",
                "Paper cover",
                "Color cover",
                "Additional",
                "Note" );
    }

}
