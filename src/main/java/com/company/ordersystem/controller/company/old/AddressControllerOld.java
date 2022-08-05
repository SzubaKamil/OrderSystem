//package com.company.ordersystem.controller.company;
//
//
//import com.company.ordersystem.entity.company.Address;
//import com.company.ordersystem.entity.company.Contact;
//import com.company.ordersystem.service.company.IAddressService;
//import com.company.ordersystem.service.company.IContactService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@RequestMapping(value = "/address")
//@Controller
//public class AddressControllerOld {
//
////    @Autowired
////    IAddressService addressService;
//    @Autowired
//    IContactService contactService;


//    @RequestMapping("/new")
//    public String newForm(Model model) {
//        Address address = new Address();
//        model.addAttribute("address", address);
//        model.addAttribute("actionDescription", "NOWY ADRES");
//        model.addAttribute("action", "/address/save/");
//
//        modelForm(model);
//
//        return "/company/address-form";
//    }

//    @RequestMapping(value = "/update/{addressId}")
//    public String updateForm(@PathVariable int addressId, Model model) {
//        Address address = addressService.get(addressId);
//        model.addAttribute("address" , address);
//        model.addAttribute("actionDescription", "EDYCJA ADRESU: ");
//        model.addAttribute("action", "/address/update/");
//
//        // IF update true show contacts list
//        boolean update = true;
//        model.addAttribute("update", update);
//
//        modelForm(model);
//
//        return "/company/address-form";
//    }
//
//    @RequestMapping(value = "/delete/{addressId}")
//    public String delete (@PathVariable int addressId, Model model){
//        Address address = addressService.get(addressId);
//
//        boolean result = addressService.delete(address);
//        String description = "Usunięcie adresu: ";
//
//        model.addAttribute("description", description);
//        model.addAttribute("result", result);
//
//        return "result";
//    }

//    @RequestMapping(value = "/list/")
//    public String list (Model model){
//        List<Address> addresses = addressService.getAll();
//
//        model.addAttribute("addresses", addresses);
//        model.addAttribute("actionDescription", "LISTA ADRESÓW");
//
//        return "/company/address-list";
//    }

//    @RequestMapping("/save/" )
//    public String save(@ModelAttribute("address") Address address, @ModelAttribute("contact") Contact contact, Model model){
//        //add contact to company if add new
//        address.addContact(contact);
//
//        String description = "Dodanie nowego adresu: ";
//
//        return saveOrUpdate(address, description, model);
//    }

//    @RequestMapping("/update/" )
////    public String update(@ModelAttribute("address") Address address, @ModelAttribute("contact") Contact contact, Model model){
//    public String update(@ModelAttribute("address") Address address, Model model){
//        //IF UPDATE GET CONTACTS FROM DB
//        List<Contact> contacts = contactService.getByAddressId(address.getId());
//
//        // set contacts to contractor
////        address.addContact(contact);
//        address.setContacts(contacts);
//        String description = "Edycja kontrahenta: ";
//
//        return saveOrUpdate(address, description, model);
//    }

//    private String saveOrUpdate(Address address, String description, Model model){
//        // get save result and return description
//        boolean result = addressService.saveOrUpdate(address);
//
//        model.addAttribute("result", result);
//        model.addAttribute("description", description);
//
//        return "result";
////    }
//
//    private void modelForm(Model model){
//        Contact contact = new Contact();
//        model.addAttribute("contact", contact);
//    }
//
//}
