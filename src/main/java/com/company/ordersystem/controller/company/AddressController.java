package com.company.ordersystem.controller.company;


import com.company.ordersystem.controller.ICrudControllerExtraSaveAttrib;
import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.entity.company.Contact;
import com.company.ordersystem.service.company.IAddressService;
import com.company.ordersystem.service.company.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/address")
@Controller
public class AddressController implements ICrudControllerExtraSaveAttrib<Address, Contact> {

    @Autowired
    IAddressService addressService;
    @Autowired
    IContactService contactService;

    @Override
    public String newForm(Model model) {
        Address address = new Address();
        model.addAttribute("address", address);
        model.addAttribute("actionDescription", "NOWY ADRES");
        model.addAttribute("action", "/address/save2/");

        modelForm(model);

        return "/company/address-form";
    }

    @Override
    public String listForm(Model model) {
        List<Address> addresses = addressService.getAll();

        model.addAttribute("addresses", addresses);
        model.addAttribute("actionDescription", "LISTA ADRESÓW");

        return "/company/address-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        Address address = addressService.get(id);
        model.addAttribute("address" , address);
        model.addAttribute("actionDescription", "EDYCJA ADRESU: ");
        model.addAttribute("action", "/address/update/");

        // IF update true show contacts list
        boolean update = true;
        model.addAttribute("update", update);

        modelForm(model);

        return "/company/address-form";
    }

    @Override
    public String delete(int id, Model model) {
        Address address = addressService.get(id);

        boolean result = addressService.delete(address);
        String description = "Usunięcie adresu: ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(Address address, Model model) {
        //IF UPDATE GET CONTACTS FROM DB
        List<Contact> contacts = contactService.getByAddressId(address.getId());

        address.setContacts(contacts);
        String description = "Edycja adresu: ";

        return saveOrUpdate(address, description, model);
    }

    @Override
    public String save(Address address, Model model) {
        // no need impl
        return null;
    }

    @Override
    public String save2(Address address, Contact contact, Model model) {
        address.addContact(contact);

        String description = "Dodanie nowego adresu: ";

        return saveOrUpdate(address, description, model);
    }

    @Override
    public String saveOrUpdate(Address address, String description, Model model) {
        boolean result = addressService.saveOrUpdate(address);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
    }
}
