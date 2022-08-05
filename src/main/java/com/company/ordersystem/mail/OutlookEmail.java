package com.company.ordersystem.mail;

import com.company.ordersystem.entity.company.Contact;
import com.company.ordersystem.entity.user.OutlookSetting;

import java.io.IOException;
import java.util.List;

public class OutlookEmail {

    private static String END_ORDER_MESSAGE = "Bardzo proszę o akceptację zlecenia i przesłanie podpisanego skanu mailem. ";
    private static String END_INQUIRY_MESSAGE = "Bardzo proszę o wycenę druku materiałów według przesłanej specyfikacji.";

    public static void newOrderEmail(String fileName, List<Contact> contactList, OutlookSetting outlookSetting) {
        newEmail(fileName, contactList, END_ORDER_MESSAGE, outlookSetting, false);
    }

    public static void newInquiryEmail(String fileName, List<Contact> contactList, OutlookSetting outlookSetting) {
        newEmail(fileName, contactList, END_INQUIRY_MESSAGE, outlookSetting, true);
    }

    /**
     * ccEmail -> IF is empty  ";"
     */
    private static void newEmail(String fileName, List<Contact> contactList, String endMessage, OutlookSetting outlookSetting, boolean ccEmpty) {
        Thread thread = new Thread(() -> {

            String pathFile = System.getProperty("user.home") + "\\Downloads\\" + fileName;
            String ccMails;

            Contact mainContact = contactList.get(0);

            StringBuilder emailsContactor = new StringBuilder();
            contactList.forEach(contact -> emailsContactor.append(contact.getEmail()));

            pathFile = pathFile.replaceAll("[^\\p{ASCII}]", "_");

            String subject = fileName;
            String body = "Dzień dobry,\n" +
                    "\n" + getGender(mainContact.getFirstName()) + " " + mainContact.getFirstNameVocative() + ", w załączniku przesyłam: " +
                    "\n\t -" + fileName +
                    "\n\n" + endMessage +
                    "\n\n" + outlookSetting.getSignature();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (ccEmpty){
                ccMails = ";";
            }
            else{
                ccMails = outlookSetting.getCcEmails();
            }

            try {
                new ProcessBuilder(outlookSetting.getFilePath(), "/a",
                        pathFile, "/m", emailsContactor + "&cc=" + ccMails+ "&subject=" + subject + "&body=" + body).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
    }

    private static String getGender(String name) {
        if (name.endsWith("a")) {
            return "Pani";
        }
        return "Panie";
    }
}
