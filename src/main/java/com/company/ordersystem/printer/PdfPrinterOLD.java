package com.company.ordersystem.printer;

import com.company.ordersystem.entity.user.User;
import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.entity.company.Company;
import com.company.ordersystem.entity.company.Contractor;
import com.company.ordersystem.entity.order.Order;
import com.company.ordersystem.entity.order.PaymentTerm;
import com.company.ordersystem.entity.product.Code;
import com.company.ordersystem.entity.product.Product;
import com.company.ordersystem.printer.itextImpl.HelveticaFont;
import com.company.ordersystem.printer.itextImpl.MyPdfPTable;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PdfPrinterOLD {

    private Order order;
    private User user;
    private final String FILE_NAME_ORDER;
    private final String FILE_NAME_INQUIRY;
    private final String FILE_NAME_ORDER_UPDATE;
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");

    public PdfPrinterOLD(Order order, User user) {
        this.order = order;
        this.user = user;

        String fileName = "Zlecenie_druku_"+ order.getCompany().getShortcut() +
                "_" +order.getProduct().getName() + "_";

        if (order.getSent() != null){
            fileName += order.getSent().toString();
        }
        else {
            fileName += getCurrentData();
        }

        FILE_NAME_ORDER = fileName + ".pdf";


        FILE_NAME_ORDER_UPDATE = fileName + "_Aktualizacja_Nr_" + order.getUpdate() + "_" + getCurrentData() + ".pdf";

        FILE_NAME_INQUIRY = "Zapytanie cenowe_" + order.getCompany().getShortcut() +
                "_" +order.getProduct().getName() + "_"+ getCurrentData() +  ".pdf";
    }

    public String printOrder(){
        Document document = new Document();

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME_ORDER));
            document.open();

            document.add(addDataParagraph());
            document.add(addContractorParagraph());
            document.add(addMainTable());
            document.add(addSignatureParagraph());

            document.close();
            pdfWriter.close();
            return FILE_NAME_ORDER;
        }
        catch (DocumentException e ){
            System.out.println("Document exception " + e.getMessage());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found exception " + e.getMessage());
        }
        return null;
    }

    public String updateOrder() {
        Document document = new Document();

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME_ORDER_UPDATE));
            document.open();

            document.add(addUpdateParagraph());
            document.add(addDataParagraph());
            document.add(addContractorParagraph());
            document.add(addMainTable());
            document.add(addSignatureParagraph());

            document.close();
            pdfWriter.close();
            return FILE_NAME_ORDER_UPDATE;
        }
        catch (DocumentException e ){
            System.out.println("Document exception " + e.getMessage());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found exception " + e.getMessage());
        }
        return null;
    }

    public String printInquiry (){
        Document document = new Document();

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME_INQUIRY));
            document.open();

            document.add(addDataParagraph());
            document.add(addInquiryParagraph());
            document.add(addMainTable());
            document.add(addSignatureParagraph());

            document.close();
            pdfWriter.close();

            return FILE_NAME_INQUIRY;
        }
        catch (DocumentException e ){
            System.out.println("Document exception " + e.getMessage());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found exception " + e.getMessage());
        }
        return null;
    }

    private Paragraph addUpdateParagraph (){
        Paragraph paragraph = new Paragraph();

        paragraph.setFont(HelveticaFont.getBold(14));
        paragraph.add("AKTUALIZACJA NR: " + order.getUpdate() + " zlecenia: \n");

        paragraph.setFont(HelveticaFont.get(12));
        paragraph.add(FILE_NAME_ORDER);

        return  paragraph;
    }

    private Paragraph addDataParagraph() {
        Paragraph paragraph = new Paragraph("Kraków " + getCurrentData() + " r.");
        paragraph.setAlignment(2);
        return  paragraph;
    }

    private Paragraph addInquiryParagraph (){
        Paragraph paragraph = new Paragraph();

        paragraph.setFont(HelveticaFont.get(12));
        paragraph.add("ZAPYTANIE CENOWE");
        paragraph.setAlignment(1);
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(10);

        return paragraph;
    }

    private Paragraph addContractorParagraph(){
        Contractor contractor = order.getContractor();
        Paragraph paragraph = new Paragraph();

        paragraph.setIndentationLeft(130);
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(10);

        paragraph.setFont(HelveticaFont.getBold(16));
        paragraph.add("\nZLECENIE USŁUG POLIGRAFICZNYCH");

        paragraph.setFont(HelveticaFont.get(12));
        paragraph.add("\nNiniejszym zlecam firmie:\n" +
                contractor +
                "\nwykonanie druku zgodnie z ustalonymi parametrami:\n");

        return paragraph;
    }

    private PdfPTable addMainTable(){
        Product product = order.getProduct();
        Company company = order.getCompany();
        Address deliveryAddress = order.getDeliveryAddress();
        PaymentTerm paymentTerm = order.getPaymentTerm();

        MyPdfPTable table = new MyPdfPTable(2);
        table.setWidthPercentage(92);

        try {
            table.setWidths(new int[]{280, 450});
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        addProduct(table, product);

        if (order.getFileDeadline() != null){
            table.addCell("Termin dostarczenia materiałów");
            table.addCell(order.getFileDeadline().toString());
        }
        if (order.getDeliveryDeadline() != null){
            table.addCell("Termin wykonania");
            table.addCell(order.getDeliveryDeadline().toString());
        }
        table.addCell("Ilość");
        table.addCell(DECIMAL_FORMAT.format(order.getQuantity()) + " egz.");


        if (order.getPricePcs() > 0){
            table.addCell("Cena");
            Double total =  order.getPricePcs() * order.getQuantity();

            table.addCell(new DecimalFormat("#.####").format(order.getPricePcs())+ " zł netto /egz.\n" + DECIMAL_FORMAT.format(total)
                    + " zł netto /nakład");
        }

        table.addCell("Należność");
        if (paymentTerm != null){
            table.addCell(paymentTerm.getTerm()+ "");
        }
        else {
            table.addCell("");
        }

        table.addCell("Dane do wystawienia FV");
        table.addCell(company.toString());

        table.addCell("Adres dostawy");
        if (deliveryAddress != null){
            table.addCell(deliveryAddress +
                    "\nKontakt: " + deliveryAddress.getContacts().get(0));
        }
        else {
            table.addCell("");
        }

        return table;
    }

    private Paragraph addSignatureParagraph(){
        Paragraph signature = new Paragraph("\n" +  user);

        signature.setFont(HelveticaFont.getBold(12));
        signature.setAlignment(2);
        signature.add(user.getName() + " " + user.getSurname());

        return signature;
    }

    private PdfPTable addProduct(PdfPTable table, Product product){
        table.addCell("Nazwa");
        table.addCell(product.getName());

        if (product.getCodeList().size() > 0){
            table.addCell("Kod produktu");
            Paragraph productCodesParagraph = new Paragraph();

            for (Code code: product.getCodeList()){
                productCodesParagraph.add(code + "\n");
            }
            table.addCell(productCodesParagraph);
        }

        if (product.getFormat() != null){
            table.addCell("Format");
            table.addCell(product.getFormat().getFormat());
        }

        if (product.getPaper() != null){
            table.addCell("Papier");
            table.addCell(product.getPaper().getValue());
        }

        if (product.getColor() != null){
            table.addCell("Kolor");
            table.addCell(product.getColor().getValue());
        }

        if (product.getNumberPages() != null && !product.getNumberPages().equals("")){
            table.addCell("Ilość stron");
            table.addCell(product.getNumberPages());
        }

        if (product.getPerforation() != null && !product.getPerforation().equals("")){
            table.addCell("Perforacja");
            table.addCell(product.getPerforation());
        }

        if (product.getCovering() != null && !product.getCovering().equals("")){
            table.addCell("Pokrycie zadruku");
            table.addCell(product.getCovering());
        }

        if (product.getFlap() != null && !product.getFlap().equals("")){
            table.addCell("Klapka");
            table.addCell(product.getFlap());
        }

        if (product.getWindow() != null && !product.getWindow().equals("")){
            table.addCell("Okienko");
            table.addCell(product.getWindow());
        }

        if (product.getGlued() != null && !product.getGlued().equals("")){
            table.addCell("Klejenie");
            table.addCell(product.getGlued());
        }

        if (product.getPrintingFinishing() != null && !product.getPrintingFinishing().equals("")){
            table.addCell("Uszlachetnienia");
            table.addCell(product.getPrintingFinishing());
        }

        if (product.getPaperInside() != null){
            table.addCell("Papier środek");
            table.addCell(product.getPaperInside().getValue());
        }

        if (product.getColorInside() != null){
            table.addCell("Kolor środek");
            table.addCell(product.getColorInside().getValue());
        }

        if (product.getCover() != null && !product.getCover().equals("")){
            table.addCell("Okladka");
            table.addCell(product.getCover());
        }

        if (product.getPaperCover() != null){
            table.addCell("Papier okładka");
            table.addCell(product.getPaperCover().getValue());
        }

        if (product.getColorCover() != null){
            table.addCell("Kolor okładka");
            table.addCell(product.getColorCover().getValue());
        }

        if (product.getAdditional() != null && !product.getAdditional().equals("")){
            table.addCell("Dodatkowe");
            table.addCell(product.getAdditional());
        }

        if (order.getNote() != null && !order.getNote().equals("")){
            table.addCell("Uwagi");
            table.addCell(order.getNote());
        }
        return table;
    }

    private String getCurrentData (){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return  dateFormat.format(date);
    }

}
