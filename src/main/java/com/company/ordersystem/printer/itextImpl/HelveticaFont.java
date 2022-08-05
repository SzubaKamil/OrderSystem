package com.company.ordersystem.printer.itextImpl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

import java.io.IOException;

public class HelveticaFont extends Font{

    private static BaseFont baseFont;

    public static Font get(int size){
        try {
            baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        }
        catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return new Font(baseFont, size);
    }

    public static Font getBold(int size){
        Font font = get(size);
        font.setStyle(Font.BOLD);
        return font;
    }

}
