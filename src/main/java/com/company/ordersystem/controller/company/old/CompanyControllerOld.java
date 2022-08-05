//package com.company.ordersystem.controller.company;
//
//import com.company.ordersystem.entity.company.Company;
//import com.company.ordersystem.entity.company.Contact;
//import com.company.ordersystem.service.company.ICompanyService;
//import com.company.ordersystem.service.company.IContactService;
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
//
//@RequestMapping("/company")
//@Controller
//public class CompanyControllerOld {
//
//    @Autowired
//    ICompanyService companyService;
//    @Autowired
//    IContactService contactService;

    //    for test without RequestMethod.POST
//    @RequestMapping("/new")
//    public String newForm(Model model){
//        Company company = new Company();
//        Contact contact = new Contact();
//
//        model.addAttribute("company", company);
//        model.addAttribute("contact", contact);
//
//        model.addAttribute("actionDescription", "NOWA FIRMA");
//
//        model.addAttribute("action", "/company/save/");
////        model.addAttribute("modelAttribute", "company");
//
//        return "/company/company-form";
//    }

//    for test without RequestMethod.POST
//    @RequestMapping(value = "/update/{companyId}")
////    @RequestMapping(value = "/update/{companyId}", method = RequestMethod.POST )
//    public String updateForm(@PathVariable int companyId, Model model) {
//        Company company = companyService.get(companyId);
//
//        model.addAttribute("company" , company);
//
//        Contact contact = new Contact();
//        model.addAttribute("contact", contact);
//
//        // IF update true show contacts list
//        boolean update = true;
//        model.addAttribute("update", update);
//
//        model.addAttribute("actionDescription", "EDYCJA FIRMY: " + company.getShortcut());
//        model.addAttribute("action", "/company/update/");
////        model.addAttribute("modelAttribute", "company");
//
//        return "/company/company-form";
//    }
//
//    @RequestMapping(value = "/delete/{companyId}")
//    public String delete (@PathVariable int companyId, Model model){
//        Company company = companyService.get(companyId);
//
//        boolean result = companyService.delete(company);
//        String description = "Usunięcie firmy: " + company.getName() + " ";
//
//        model.addAttribute("description", description);
//        model.addAttribute("result", result);
//
//        return "result";
//    }

//    @RequestMapping(value = "/list/")
//    public String list (Model model){
//        List<Company> companies = companyService.getAll();
//
//        model.addAttribute("companies", companies);
//        model.addAttribute("actionDescription", "LISTA FIRM");
//
//        return "/company/company-list";
//    }

//    @RequestMapping("/save/" )
//    public String save(@ModelAttribute("company") Company company, @ModelAttribute("contact") Contact contact, Model model){
//        //add contact to company if add new
//        company.getAddress().addContact(contact);
//
//        // get save result and return description
////        boolean result = companyService.saveOrUpdate(company);
//        String description = "Dodanie nowej firmy: " + company.getName() + " ";
//
//        return saveOrUpdate(company, description, model);
//    }

//    @RequestMapping("/update/" )
////    public String update(@ModelAttribute("company") Company company, @ModelAttribute("contact") Contact contact, Model model){
//    public String update(@ModelAttribute("company") Company company,  Model model){
//        //IF UPDATE GET CONTACTS FROM DB
//        List<Contact> contacts = contactService.getByAddressId(company.getAddress().getId());
//
//        // set contacts to company
//        company.getAddress().setContacts(contacts);
//        String description = "Edycja firmy: " + company.getName() + " ";
//
//        return saveOrUpdate(company, description, model);
//    }

//    @RequestMapping(value = "/edit-contact/{companyId}", method = RequestMethod.POST)
//    public String editContactForm(@PathVariable int companyId, @ModelAttribute("contact") Contact contact){
//
//        // NO NEED FOR COMPANY  USER is
//
//        System.out.println(contact + "  COMPANY ID " + companyId);
//        return "/company/contact-form";
//    }
//
//    private String saveOrUpdate(Company company, String description, Model model){
//        // get save result and return description
//        boolean result = companyService.saveOrUpdate(company);
//
//        model.addAttribute("result", result);
//        model.addAttribute("description", description);
//
//        return "result";
//    }

//
//}
