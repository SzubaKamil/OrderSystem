package com.company.ordersystem.controller.company;

import com.company.ordersystem.controller.ICrudController;
import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.entity.company.Contact;
import com.company.ordersystem.entity.company.Contractor;
import com.company.ordersystem.service.company.IAddressService;
import com.company.ordersystem.service.company.IContactService;
import com.company.ordersystem.service.company.IContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController implements ICrudController<Contact> {

    @Autowired
    IContactService contactService;
    @Autowired
    IContractorService contractorService;
    @Autowired
    IAddressService addressService;

    @Override
    public String newForm(Model model) {
        List<Contractor> contractors = contractorService.getAll();

        model.addAttribute("contractorSelect", true);
        model.addAttribute("contractors", contractors );

        model.addAttribute("contact", new Contact());
        modelForm(model);

        return "/company/contact-form";
    }

    @Override
    public String listForm(Model model) {
        List<Contact> contacts = contactService.getAll();

        model.addAttribute("contacts", contacts);
        model.addAttribute("actionDescription", "LISTA KONTAKTÓW");

        return "/company/contact-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        Contact contact = contactService.get(id);

        model.addAttribute("contact", contact);
        modelUpdateForm(model);

        return "/company/contact-form";
    }

    @Override
    public String delete(int id, Model model) {
        Contact contact = contactService.get(id);

        boolean result = contactService.delete(contact);
        String description = "Usunięcie kontaktu: " + contact.getFirstName() + " " + contact.getSurname() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(Contact contact, Model model) {
        String description = "Edycja kontaku: " + contact.getFirstName() + " " + contact.getSurname();

        return saveOrUpdate(contact, description, model);
    }

    @Override
    public String save(Contact contact, Model model) {
        String description = "Dodanie kontaktu: " + contact.getFirstName() + " " + contact.getSurname();

        Address address = addressService.get(contact.getAddressId());
        address.addContact(contact);
        boolean result = addressService.saveOrUpdate(address);

        model.addAttribute("result", result);
        model.addAttribute("description", description);
        return "result";
    }

    @Override
    public String saveOrUpdate(Contact contact, String description, Model model) {
        boolean result = contactService.saveOrUpdate(contact);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        model.addAttribute("actionDescription", "NOWY KONTAKT");
        model.addAttribute("action", "/contact/save/");
    }

    private void modelUpdateForm(Model model){
        model.addAttribute("actionDescription", "EDYCJA KONTAKTU");
        model.addAttribute("action", "/contact/update/");
    }

    @RequestMapping(value = "/update/", method = RequestMethod.POST)
    public String editContactForm(@ModelAttribute("contact") Contact contact, Model model){
        contact = contactService.get(contact.getId());

        model.addAttribute("contact", contact);
        modelUpdateForm(model);

        return "/company/contact-form";
    }

    @RequestMapping("/new/{addressId}")
    public String newForm(@PathVariable int addressId, Model model) {
        Contact contact = new Contact();
        contact.setAddressId(addressId);

        model.addAttribute("contact", contact);
        modelForm(model);

        return "/company/contact-form";
    }
}
