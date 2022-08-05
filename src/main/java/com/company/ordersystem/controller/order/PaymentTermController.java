package com.company.ordersystem.controller.order;


import com.company.ordersystem.controller.ICrudController;
import com.company.ordersystem.entity.order.PaymentTerm;
import com.company.ordersystem.service.order.IPaymentTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/payment-term")
public class PaymentTermController implements ICrudController<PaymentTerm> {

    @Autowired
    IPaymentTermService paymentTermService;

    @Override
    public String newForm(Model model) {
        PaymentTerm paymentTerm = new PaymentTerm();

        model.addAttribute("paymentTerm", paymentTerm);
        model.addAttribute("actionDescription", "NOWE WARUNKI PŁATNOŚCI");
        model.addAttribute("action", "/payment-term/save/");

        return "/order/payment-term-form";
    }

    @Override
    public String listForm(Model model) {
        List<PaymentTerm> paymentTerms = paymentTermService.getAll();

        model.addAttribute("paymentTerms", paymentTerms);
        model.addAttribute("actionDescription", "LISTA WARUNKÓW PŁATNOŚCI");

        return "/order/payment-term-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        PaymentTerm paymentTerm = paymentTermService.get(id);

        model.addAttribute("paymentTerm" , paymentTerm);
        model.addAttribute("actionDescription", "EDYCJA WARUNKÓW PŁATNOŚCI: ");
        model.addAttribute("action", "/payment-term/update/");

        return "/order/payment-term-form";
    }

    @Override
    public String delete(int id, Model model) {
        PaymentTerm paymentTerm = paymentTermService.get(id);

        boolean result = paymentTermService.delete(paymentTerm);
        String description = "Usunięcie warunków płatności: " + paymentTerm.getTerm() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(PaymentTerm paymentTerm, Model model) {
        String description = "Edycja warunków płatności: ";

        return saveOrUpdate(paymentTerm, description, model);
    }

    @Override
    public String save(PaymentTerm paymentTerm, Model model) {
        String description = "Dodanie nowych warunków płatności: ";

        return saveOrUpdate(paymentTerm, description, model);
    }

    @Override
    public String saveOrUpdate(PaymentTerm paymentTerm, String description, Model model) {
        boolean result = paymentTermService.saveOrUpdate(paymentTerm);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        // no need impl
    }
}
