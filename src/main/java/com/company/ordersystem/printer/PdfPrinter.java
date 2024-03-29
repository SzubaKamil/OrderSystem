package com.company.ordersystem.printer;

import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.entity.company.Company;
import com.company.ordersystem.entity.company.Contractor;
import com.company.ordersystem.entity.order.Order;
import com.company.ordersystem.entity.order.PaymentTerm;
import com.company.ordersystem.entity.product.Code;
import com.company.ordersystem.entity.product.Product;
import com.company.ordersystem.entity.user.User;
import com.company.ordersystem.printer.dictionary.Dictionary;
import com.company.ordersystem.printer.itextImpl.HelveticaFont;
import com.company.ordersystem.printer.itextImpl.MyPdfPTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PdfPrinter {

    private Order order;
    private User user;
    private final String FILE_NAME_ORDER;
    private final String FILE_NAME_INQUIRY;
    private final String FILE_NAME_ORDER_UPDATE;
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");
    private final boolean isEnglishVersion;
    private final Dictionary polishDictionary;
    private final Dictionary englishDictionary;

    public PdfPrinter(Order order, User user) {
        this.order = order;
        this.user = user;
        this.polishDictionary = DictionariesGenerator.getPolishDictionary();
        this.englishDictionary = DictionariesGenerator.getEnglishDictionary();

        Dictionary currectDictionary;

        isEnglishVersion = order.getProduct().getProductEng() != null;
        String productName;

        if (isEnglishVersion){
            currectDictionary = englishDictionary;
            productName = order.getProduct().getProductEng().getName();
        }
        else {
            currectDictionary = polishDictionary;
            productName = order.getProduct().getName();
        }

        String fileName = currectDictionary.getFileNameOrderTitle() + "_" + order.getCompany().getShortcut() +
                "_" + productName + "_";

        if (order.getSent() != null){
            fileName += order.getSent().toString();
        }
        else {
            fileName += getCurrentData();
        }

        FILE_NAME_ORDER = fileName + ".pdf";

        FILE_NAME_ORDER_UPDATE = fileName + "_" + currectDictionary.getFileNameOrderUpdate() + "_Nr_" + order.getUpdate() + "_" + getCurrentData() + ".pdf";

        FILE_NAME_INQUIRY = "Zapytanie cenowe_" + order.getCompany().getShortcut() +
                "_" +order.getProduct().getName() + "_"+ getCurrentData() +  ".pdf";

    }

    public String printOrder(){
        Document document = new Document();

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME_ORDER));
            document.open();

            document.add(addDataParagraph());
            document.add(addContractorParagraph(false));
            document.add(addMainTable(false));
            document.add(addSignatureParagraph());

            if (isEnglishVersion){
                document.newPage();
                document.add(addDataParagraph());
                document.add(addContractorParagraph(true));
                document.add(addMainTable(true));
                document.add(addSignatureParagraph());
            }

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

            document.add(addUpdateParagraph(false));
            document.add(addDataParagraph());
            document.add(addContractorParagraph(false));
            document.add(addMainTable(false));
            document.add(addSignatureParagraph());

            if (isEnglishVersion){
                document.newPage();
                document.add(addUpdateParagraph(true));
                document.add(addDataParagraph());
                document.add(addContractorParagraph(true));
                document.add(addMainTable(true));
                document.add(addSignatureParagraph());
            }

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
            document.add(addMainTable(false));
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

    private Paragraph addUpdateParagraph (boolean englishVersion){
        Paragraph paragraph = new Paragraph();
        Dictionary currentDictionary = getCurrentDictionary(englishVersion);

        paragraph.setFont(HelveticaFont.getBold(14));
        paragraph.add(currentDictionary.getUpdateParagraph() + " NR: " + order.getUpdate() + " " + currentDictionary.getUpdateParagraphOrder() + ": \n");

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

    private Paragraph addContractorParagraph(boolean englishVersion){
        Contractor contractor = order.getContractor();
        Paragraph paragraph = new Paragraph();
        Dictionary currentDictionary = getCurrentDictionary(englishVersion);

        paragraph.setIndentationLeft(130);
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(10);

        paragraph.setFont(HelveticaFont.getBold(16));

        paragraph.add("\n" + currentDictionary.getContractorParagraphTitle());

        paragraph.setFont(HelveticaFont.get(12));


        paragraph.add("\n" + currentDictionary.getContractorParagraphContractor() + ":\n" +
                        contractor +
                        "\ne-mail: " + contractor.getAddress().getContacts().get(0).getEmail() +
                        "\n" + currentDictionary.getContractorParagraphPrinting() + ":\n");

        return paragraph;
    }

    private PdfPTable addMainTable(boolean englishVersion){
        Product product = order.getProduct();
        Company company = order.getCompany();
        Address deliveryAddress = order.getDeliveryAddress();
        Dictionary currentDictionary = getCurrentDictionary(englishVersion);

        PaymentTerm paymentTerm = order.getPaymentTerm();

        MyPdfPTable table = new MyPdfPTable(2);
        table.setWidthPercentage(92);

        try {
            table.setWidths(new int[]{280, 450});
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        addProduct(table, product, englishVersion);

        if (order.getFileDeadline() != null){
            table.addCell(currentDictionary.getMainTableDateDeliveryMaterials());
            table.addCell(order.getFileDeadline().toString());
        }
        if (order.getDeliveryDeadline() != null){
            table.addCell(currentDictionary.getMainTableDeadline());
            table.addCell(order.getDeliveryDeadline().toString());
        }
        table.addCell(currentDictionary.getMainTableQuantity());
        table.addCell(DECIMAL_FORMAT.format(order.getQuantity()) +  " "+ currentDictionary.getMainTableQuantityUnit());


        if (order.getPricePcs() > 0){
            table.addCell(currentDictionary.getMainTablePrice());
            Double total =  order.getPricePcs() * order.getQuantity();

            table.addCell(new DecimalFormat("#.####").format(order.getPricePcs())+ " "+ currentDictionary.getMainTablePriceUnitPcs()+"\n" + DECIMAL_FORMAT.format(total)
                    + " "+ currentDictionary.getMainTablePriceUnitAll());
        }

        table.addCell(currentDictionary.getMainTablePaymentTerm());
        if (paymentTerm != null){
            table.addCell(paymentTerm.getTerm(englishVersion)+ " ");
        }
        else {
            table.addCell("");
        }

        table.addCell(currentDictionary.getMainTableInvoiceData());
        table.addCell(company.toString());

        table.addCell(currentDictionary.getMainTableDeliveryAddress());
        if (deliveryAddress != null){
            table.addCell(deliveryAddress +
                    "\n" + currentDictionary.getMainTableContact() +": " + deliveryAddress.getContacts().get(0));
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

    private PdfPTable addProduct(PdfPTable table, Product product, boolean englishVersion ){
        Dictionary currentDictionary = getCurrentDictionary(englishVersion);

        if (englishVersion){
            product = product.getProductEng();
        }


        table.addCell(currentDictionary.getProductName());
        table.addCell(product.getName());

        if (product.getCodeList().size() > 0){
            table.addCell(currentDictionary.getProductCode());
            Paragraph productCodesParagraph = new Paragraph();

            for (Code code: product.getCodeList()){
                productCodesParagraph.add(code + "\n");
            }
            table.addCell(productCodesParagraph);
        }

        if (product.getFormat() != null){
            table.addCell(currentDictionary.getProductFormat());
            table.addCell(product.getFormat().getFormat());
        }

        if (product.getPaper() != null){
            table.addCell(currentDictionary.getProductPaper());
            table.addCell(product.getPaper().getValue());
        }

        if (product.getColor() != null){
            table.addCell(currentDictionary.getProductColor());
            table.addCell(product.getColor().getValue());
        }

        if (product.getNumberPages() != null && !product.getNumberPages().equals("")){
            table.addCell(currentDictionary.getProductNumberPages());
            table.addCell(product.getNumberPages());
        }

        if (product.getPerforation() != null && !product.getPerforation().equals("")){
            table.addCell(currentDictionary.getProductPerforation());
            table.addCell(product.getPerforation());
        }

        if (product.getCovering() != null && !product.getCovering().equals("")){
            table.addCell(currentDictionary.getProductCovering());
            table.addCell(product.getCovering());
        }

        if (product.getFlap() != null && !product.getFlap().equals("")){
            table.addCell(currentDictionary.getProductFlap());
            table.addCell(product.getFlap());
        }

        if (product.getWindow() != null && !product.getWindow().equals("")){
            table.addCell(currentDictionary.getProductWindow());
            table.addCell(product.getWindow());
        }

        if (product.getGlued() != null && !product.getGlued().equals("")){
            table.addCell(currentDictionary.getProductGlued());
            table.addCell(product.getGlued());
        }

        if (product.getPrintingFinishing() != null && !product.getPrintingFinishing().equals("")){
            table.addCell(currentDictionary.getProductPrintingFinishing());
            table.addCell(product.getPrintingFinishing());
        }

        if (product.getPaperInside() != null){
            table.addCell(currentDictionary.getProductPaperInside());
            table.addCell(product.getPaperInside().getValue());
        }

        if (product.getColorInside() != null){
            table.addCell(currentDictionary.getProductColorInside());
            table.addCell(product.getColorInside().getValue());
        }

        if (product.getCover() != null && !product.getCover().equals("")){
            table.addCell(currentDictionary.getProductCover());
            table.addCell(product.getCover());
        }

        if (product.getPaperCover() != null){
            table.addCell(currentDictionary.getProductPaperCover());
            table.addCell(product.getPaperCover().getValue());
        }

        if (product.getColorCover() != null){
            table.addCell(currentDictionary.getProductColorCover());
            table.addCell(product.getColorCover().getValue());
        }

        if (product.getAdditional() != null && !product.getAdditional().equals("")){
            table.addCell(currentDictionary.getProductAdditional());
            table.addCell(product.getAdditional());
        }

        if (order.getNote() != null && !order.getNote().equals("")){
            table.addCell(currentDictionary.getProductNote());
            table.addCell(order.getNote());
        }
        return table;
    }

    private String getCurrentData (){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return  dateFormat.format(date);
    }

    private Dictionary getCurrentDictionary (boolean englishVersion){
        if (englishVersion){
            return englishDictionary;
        }
        else {
            return polishDictionary;
        }
    }
}

