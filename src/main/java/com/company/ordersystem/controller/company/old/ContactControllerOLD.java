//package com.company.ordersystem.controller.company;
//
//import com.company.ordersystem.entity.company.Contact;
//import com.company.ordersystem.entity.company.Contractor;
//import com.company.ordersystem.service.company.IContactService;
//import com.company.ordersystem.service.company.IContractorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/contact")
//public class ContactControllerOLD {
//
//    @Autowired
//    IContactService contactService;
//    @Autowired
//    IContractorService contractorService;
//
//    @RequestMapping("/new/{addressId}")
//    public String newForm(@PathVariable int addressId, Model model) {
//        Contact contact = new Contact();
//        contact.setAddressId(addressId);
//        modelForm(model, contact);
//
//        return "/company/contact-form";
//    }

//    @RequestMapping("/new/")
//    public String newForm( Model model) {
//        List<Contractor> contractors = contractorService.getAll();
//
//        model.addAttribute("contractorSelect", true);
//        model.addAttribute("contractors", contractors );
//        modelForm(model, new Contact());
//        return "/company/contact-form";
//    }

//    @RequestMapping(value = "/list/")
//    public String list (Model model){
//        List<Contact> contacts = contactService.getAll();
//
//        model.addAttribute("contacts", contacts);
//        model.addAttribute("actionDescription", "LISTA KONTAKTÓW");
//
//        return "/company/contact-list";
//    }

//    @RequestMapping(value = "/update/", method = RequestMethod.POST)
//    public String editContactForm(@ModelAttribute("contact") Contact contact, Model model){
//        contact = contactService.get(contact.getId());
//
//        modelUpdateForm(model, contact);
//
//        return "/company/contact-form";
//    }

//    @RequestMapping(value = "/update/{contactId}")
//    public String editContactForm(@PathVariable int contactId, Model model){
//        Contact contact = contactService.get(contactId);
//
//        modelUpdateForm(model, contact);
//
//        return "/company/contact-form";
//    }

//    @RequestMapping(value = "/delete/{contactId}")
//    public String delete (@PathVariable int contactId, Model model){
//        Contact contact = contactService.get(contactId);
//
//        boolean result = contactService.delete(contact);
//        String description = "Usunięcie kontaktu: " + contact.getFirstName() + " " + contact.getSurname() + " ";
//
//        model.addAttribute("description", description);
//        model.addAttribute("result", result);
//
//        return "result";
//    }

//    @RequestMapping(value = "/save/{addressId}")
//    @RequestMapping(value = "/save/")
////    public String saveContact (Model model, @ModelAttribute("contact") Contact contact, @PathVariable int addressId){
//    public String saveContact (Model model, @ModelAttribute("contact") Contact contact){
//        String description = "Dodanie kontaktu: " + contact.getFirstName() + " " + contact.getSurname();
//
//        return saveOrUpdate(contact, description, model);
//    }

//    @RequestMapping(value = "/update")
//    public String update (Model model, @ModelAttribute("contact") Contact contact){
//        String description = "Edycja kontaku: " + contact.getFirstName() + " " + contact.getSurname();
//
//        return saveOrUpdate(contact, description, model);
//    }

//    private String saveOrUpdate(Contact contact, String description, Model model) {
//        boolean result = contactService.saveOrUpdate(contact);
//
//        model.addAttribute("result", result);
//        model.addAttribute("description", description);
//
//        return "result";
//    }

//    private void modelForm (Model model, Contact contact){
//        model.addAttribute("contact", contact);
//        model.addAttribute("actionDescription", "NOWY KONTAKT");
//        model.addAttribute("action", "/contact/save/");
//    }
//    private void modelUpdateForm(Model model, Contact contact){
//        model.addAttribute("actionDescription", "EDYCJA KONTAKTU");
//        model.addAttribute("contact", contact);
//        model.addAttribute("action", "/contact/update/");
//    }
//
//}
