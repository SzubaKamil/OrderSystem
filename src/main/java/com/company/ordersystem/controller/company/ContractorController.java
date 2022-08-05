package com.company.ordersystem.controller.company;

import com.company.ordersystem.controller.ICrudControllerExtraSaveAttrib;
import com.company.ordersystem.entity.company.Contact;
import com.company.ordersystem.entity.company.Contractor;
import com.company.ordersystem.service.company.IContactService;
import com.company.ordersystem.service.company.IContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contractor")
public class ContractorController implements ICrudControllerExtraSaveAttrib<Contractor, Contact> {

    @Autowired
    IContractorService contractorService ;
    @Autowired
    IContactService contactService;

    @Override
    public String newForm(Model model) {
        Contractor contractor = new Contractor();
        Contact contact = new Contact();

        model.addAttribute("actionDescription", "NOWY KONTRAHENT");

        model.addAttribute("contractor", contractor);
        model.addAttribute("contact", contact);
        model.addAttribute("action", "/contractor/save2/");

        return "/company/contractor-form";
    }

    @Override
    public String listForm(Model model) {
        List<Contractor> contractors = contractorService.getAll();

        model.addAttribute("contractors", contractors);
        model.addAttribute("actionDescription", "LISTA KONTRAHENTÓW");

        return "/company/contractor-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        Contractor contractor = contractorService.get(id);

        model.addAttribute("contractor" , contractor);

        Contact contact = new Contact();
        model.addAttribute("contact", contact);

        // IF update true show contacts list
        boolean update = true;
        model.addAttribute("update", update);

        model.addAttribute("actionDescription", "EDYCJA KONTRAHENTA: " + contractor.getShortcut());
        model.addAttribute("action", "/contractor/update/");

        return "/company/contractor-form";
    }

    @Override
    public String delete(int id, Model model) {
        Contractor contractor = contractorService.get(id);

        boolean result = contractorService.delete(contractor);
        String description = "Usunięcie kontrahenta: " + contractor.getName() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(Contractor contractor, Model model) {
        //IF UPDATE GET CONTACTS FROM DB
        List<Contact> contacts = contactService.getByAddressId(contractor.getAddress().getId());

        // set contacts to contractor
        contractor.getAddress().setContacts(contacts);
        String description = "Edycja kontrahenta: " + contractor.getName() + " ";

        return saveOrUpdate(contractor, description, model);
    }

    @Override
    public String save(Contractor contractor, Model model) {
        // NO IMPL NEED
        return null;
    }

    @Override
    public String save2(Contractor contractor, Contact contact, Model model) {
        //add contact to company if add new
        contractor.getAddress().addContact(contact);

        String description = "Dodanie nowego kontrahenta: " + contractor.getName() + " ";

        return saveOrUpdate(contractor, description, model);
    }

    @Override
    public String saveOrUpdate(Contractor contractor, String description, Model model) {
        boolean result = contractorService.saveOrUpdate(contractor);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        // NO IMPL NEED
    }
}
