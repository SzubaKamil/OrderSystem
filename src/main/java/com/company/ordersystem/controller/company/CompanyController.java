package com.company.ordersystem.controller.company;

import com.company.ordersystem.controller.ICrudController;
import com.company.ordersystem.entity.company.Company;
import com.company.ordersystem.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/company")
@Controller
public class CompanyController implements ICrudController<Company> {

    @Autowired
    ICompanyService companyService;

    @Override
    public String newForm(Model model) {
        Company company = new Company();

        model.addAttribute("company", company);
        model.addAttribute("actionDescription", "NOWA FIRMA");
        model.addAttribute("action", "/company/save/");

        return "/company/company-form";
    }

    @Override
    public String listForm(Model model) {
        List<Company> companies = companyService.getAll();

        model.addAttribute("companies", companies);
        model.addAttribute("actionDescription", "LISTA FIRM");

        return "/company/company-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        Company company = companyService.get(id);

        model.addAttribute("company" , company);
        model.addAttribute("actionDescription", "EDYCJA FIRMY: " + company.getShortcut());
        model.addAttribute("action", "/company/update/");

        return "/company/company-form";
    }

    @Override
    public String delete(int id, Model model) {
        Company company = companyService.get(id);

        boolean result = companyService.delete(company);
        String description = "Usunięcie firmy: " + company.getName() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(Company company, Model model) {
        String description = "Edycja firmy: " + company.getName() + " ";

        return saveOrUpdate(company, description, model);
    }

    @Override
    public String save(Company company, Model model) {
        String description = "Dodanie nowej firmy: " + company.getName() + " ";

        return saveOrUpdate(company, description, model);
    }

    @Override
    public String saveOrUpdate(Company company, String description, Model model) {
        boolean result = companyService.saveOrUpdate(company);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        // no need impl
    }
}
