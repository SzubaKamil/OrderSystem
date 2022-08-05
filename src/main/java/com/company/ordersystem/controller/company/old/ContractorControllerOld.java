//package com.company.ordersystem.controller.company.old;
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
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/contractor")
//public class ContractorControllerOld {
//
//    @Autowired
//    IContractorService contractorService ;
//    @Autowired
//    IContactService contactService;
//
//    @RequestMapping("/new")
//    public String newForm(Model model) {
//        Contractor contractor = new Contractor();
//        Contact contact = new Contact();
//
//        model.addAttribute("actionDescription", "NOWY KONTRAHENT");
//
//        model.addAttribute("contractor", contractor);
//        model.addAttribute("contact", contact);
//
//        model.addAttribute("action", "/contractor/save/");
////        model.addAttribute("modelAttribute", "MyCompany");
//
//        return "/company/contractor-form";
//    }
//
//    //    for test without RequestMethod.POST
//    @RequestMapping(value = "/update/{contractorId}")
////    @RequestMapping(value = "/update/{companyId}", method = RequestMethod.POST )
//    public String updateForm(@PathVariable int contractorId, Model model) {
//        Contractor contractor = contractorService.get(contractorId);
//
//        model.addAttribute("contractor" , contractor);
//
//        Contact contact = new Contact();
//        model.addAttribute("contact", contact);
//
//        // IF update true show contacts list
//        boolean update = true;
//        model.addAttribute("update", update);
//
//        model.addAttribute("actionDescription", "EDYCJA KONTRAHENTA: " + contractor.getShortcut());
//        model.addAttribute("action", "/contractor/update/");
//
//        return "/company/contractor-form";
//    }
//
//    @RequestMapping(value = "/delete/{contractorId}")
//    public String delete (@PathVariable int contractorId, Model model){
//        Contractor contractor = contractorService.get(contractorId);
//
//        boolean result = contractorService.delete(contractor);
//        String description = "Usunięcie kontrahenta: " + contractor.getName() + " ";
//
//        model.addAttribute("description", description);
//        model.addAttribute("result", result);
//
//        return "result";
//    }
//
//    @RequestMapping(value = "/list/")
//    public String list (Model model){
//        List<Contractor> contractors = contractorService.getAll();
//
//        model.addAttribute("contractors", contractors);
//        model.addAttribute("actionDescription", "LISTA KONTRAHENTÓW");
//
//        return "/company/contractor-list";
//    }
//
//    @RequestMapping("/save/" )
//    public String save(@ModelAttribute("contractor") Contractor contractor, @ModelAttribute("contact") Contact contact, Model model){
//        //add contact to company if add new
//        contractor.getAddress().addContact(contact);
//
//        String description = "Dodanie nowego kontrahenta: " + contractor.getName() + " ";
//
//        return saveOrUpdate(contractor, description, model);
//    }
//
//    @RequestMapping("/update/" )
////    public String update(@ModelAttribute("contractor") Contractor contractor, @ModelAttribute("contact") Contact contact, Model model){
//    public String update(@ModelAttribute("contractor") Contractor contractor, Model model){
//        //IF UPDATE GET CONTACTS FROM DB
//        List<Contact> contacts = contactService.getByAddressId(contractor.getAddress().getId());
//
//        // set contacts to contractor
//        contractor.getAddress().setContacts(contacts);
//        String description = "Edycja kontrahenta: " + contractor.getName() + " ";
//
//        return saveOrUpdate(contractor, description, model);
//    }
//
//    private String saveOrUpdate(Contractor contractor, String description, Model model){
//        // get save result and return description
//        boolean result = contractorService.saveOrUpdate(contractor);
//
//        model.addAttribute("result", result);
//        model.addAttribute("description", description);
//
//        return "result";
//    }
//
//}
