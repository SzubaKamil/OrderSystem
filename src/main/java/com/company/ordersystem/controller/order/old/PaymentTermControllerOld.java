//package com.company.ordersystem.controller.order.old;
//
//
//import com.company.ordersystem.entity.order.PaymentTerm;
//import com.company.ordersystem.service.order.IPaymentTermService;
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
//@RequestMapping("/payment-term")
//public class PaymentTermControllerOld {
//
//    @Autowired
//    IPaymentTermService paymentTermService;

//    @RequestMapping("/new")
//    public String newForm(Model model) {
//        PaymentTerm paymentTerm = new PaymentTerm();
//
//        model.addAttribute("paymentTerm", paymentTerm);
//        model.addAttribute("actionDescription", "NOWE WARUNKI PŁATNOŚCI");
//        model.addAttribute("action", "/payment-term/save/");
//
////        modelForm(model);
//        return "/order/payment-term-form";
//    }

//    @RequestMapping(value = "/update/{paymentTermId}")
////    @RequestMapping(value = "/update/{companyId}", method = RequestMethod.POST )
//    public String updateForm(@PathVariable int paymentTermId, Model model) {
//        PaymentTerm paymentTerm = paymentTermService.get(paymentTermId);
//
//        model.addAttribute("paymentTerm" , paymentTerm);
//        model.addAttribute("actionDescription", "EDYCJA WARUNKÓW PŁATNOŚCI: ");
//        model.addAttribute("action", "/payment-term/update/");
//
////        modelForm(model);
//        return "/order/payment-term-form";
//    }

//    @RequestMapping(value = "/list/")
//    public String list (Model model){
//        List<PaymentTerm> paymentTerms = paymentTermService.getAll();
//
//        model.addAttribute("paymentTerms", paymentTerms);
//        model.addAttribute("actionDescription", "LISTA WARUNKÓW PŁATNOŚCI");
//
//        return "/order/payment-term-list";
//    }

//    @RequestMapping("/save/" )
////    public String save(@ModelAttribute("address") Address address, @ModelAttribute("contact") Contact contact, Model model){
//    public String save(@ModelAttribute("paymentTerm") PaymentTerm paymentTerm,  Model model){
//        String description = "Dodanie nowych warunków płatności: ";
//
//        return saveOrUpdate(paymentTerm, description, model);
//    }

//    @RequestMapping("/update/" )
////    public String update(@ModelAttribute("address") Address address, @ModelAttribute("contact") Contact contact, Model model){
//    public String update(@ModelAttribute("paymentTerm") PaymentTerm paymentTerm, Model model){
//        String description = "Edycja warunków płatności: ";
//
//        return saveOrUpdate(paymentTerm, description, model);
//    }

//    private String saveOrUpdate(PaymentTerm paymentTerm, String description, Model model){
//        // get save result and return description
//        boolean result = paymentTermService.saveOrUpdate(paymentTerm);
//
//        model.addAttribute("result", result);
//        model.addAttribute("description", description);
//
//        return "result";
//    }

//    private void modelForm(Model model){
////        PaymentTerm paymentTerm = new PaymentTerm();
////        model.addAttribute("paymentTerm", paymentTerm);
//    }
//}
