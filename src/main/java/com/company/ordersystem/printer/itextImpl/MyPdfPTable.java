package com.company.ordersystem.printer.itextImpl;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;

public class MyPdfPTable extends PdfPTable {

    public MyPdfPTable() {
    }

    public MyPdfPTable(float[] relativeWidths) {
        super(relativeWidths);
    }

    public MyPdfPTable(int numColumns) {
        super(numColumns);
    }

    public MyPdfPTable(PdfPTable table) {
        super(table);
    }

    @Override
    public void addCell(String text) {
        this.addCell(new Phrase(text, HelveticaFont.get(12)));
    }

}
