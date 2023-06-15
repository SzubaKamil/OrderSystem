package com.company.ordersystem.controller.product;

import com.company.ordersystem.controller.ICrudController;
import com.company.ordersystem.entity.product.*;
import com.company.ordersystem.service.product.IProductDetailsService;
import com.company.ordersystem.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController implements ICrudController<Product> {

    @Autowired
    IProductService productService;
    @Autowired
    IProductDetailsService productDetailsService;
    private List<Code> codeList;

    public ProductController() {
        this.codeList = new ArrayList<>();
    }

    @Override
    public String newForm(Model model) {
        Product product = new Product();
        codeList = new ArrayList<>();
        product.setCodeList(codeList);

        model.addAttribute("product", product);
        model.addAttribute("action", "/product/save/");
        model.addAttribute("actionDescription", "NOWY PRODUKT");
        modelForm(model);

        return "/product/product-form";
    }

    @RequestMapping(value = "/new-eng")
    public String newFormEng (Model model){
        boolean isEnglish = true;

        Product product = new Product();
        product.setProductEng(new Product());

        newForm(model);
        model.addAttribute("isEnglish", isEnglish);
        model.addAttribute("product", product);

        return "/product/product-form";
    }

    @Override
    public String listForm(Model model) {
        List<Product> products = productService.getAll();

        model.addAttribute("products", products);
        model.addAttribute("actionDescription", "LISTA PRODUKTÓW");
        model.addAttribute("searchProduct", new Product());

        return "/product/product-list";
    }

    @Override
    public String updateForm(int id, Model model) {
        Product product;

        if (id > 0) {
            product = productService.get(id);
            codeList = product.getCodeList();
        } else {
            product = new Product();
            product.setCodeList(codeList);
        }

        if (product.getProductEng() !=null){
            model.addAttribute("isEnglish", true);;
        }

        model.addAttribute("product", product);
        model.addAttribute("action", "/product/update/");
        model.addAttribute("actionDescription", "EDYCJA PRODUKTU: " + product.getName());
        modelForm(model);

        return "/product/product-form";
    }

    @Override
    public String delete(int id, Model model) {
        Product product = productService.get(id);
        boolean result;

        if (product.getProductEng() != null){
            result = productService.delete(product.getProductEng());
        }

        result = productService.delete(product);
        String description = "Usunięcie produktu: " + product.getName() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @Override
    public String update(Product product, Model model) {
        product.setCodeList(codeList);

        String description = "Edycja produktu " + product.getName() + " ";

        return saveOrUpdate(product, description, model);
    }

    @Override
    public String save(Product product, Model model) {
        product.setCodeList(codeList);

        String description = "Dodanie nowego produktu " + product.getName() + " ";

        return saveOrUpdate(product, description, model);
    }

    @Override
    public String saveOrUpdate(Product product, String description, Model model) {
        boolean result;

        if (product.getProductEng() != null){
            result = productService.saveOrUpdate(product.getProductEng());
        }
        result = productService.saveOrUpdate(product);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    @Override
    public void modelForm(Model model) {
        model.addAttribute("formatList", productDetailsService.getAllFormat());
        model.addAttribute("paperList", productDetailsService.getAllPaper());
        model.addAttribute("colorList", productDetailsService.getAllColor());
        model.addAttribute("code", new Code());
        model.addAttribute("color", new Color());
        model.addAttribute("format", new Format());
        model.addAttribute("paper", new Paper());
        model.addAttribute("actionColor", "/product-details/saveColor/");
        model.addAttribute("actionFormat", "/product-details/saveFormat/");
        model.addAttribute("actionPaper", "/product-details/savePaper/");
    }

    @RequestMapping(value = "/copy/{productId}")
    public String copyForm(@PathVariable int productId, Model model) {
        Product oldProduct = new Product();
        Product product;

        if (productId > 0) {
            oldProduct = productService.get(productId);
            try {
                product = oldProduct.clone();
            }
            catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            codeList = product.getCodeList();
        }
        else {
            product = new Product();
            product.setCodeList(codeList);
        }

        model.addAttribute("product", product);
        model.addAttribute("action", "/product/copy/");
        model.addAttribute("actionDescription", "KOPIOWANIE Z PRODUKTU: " + oldProduct.getName());
        if (product.getProductEng() != null){
            model.addAttribute("isEnglish", true);
        }
        modelForm(model);

        return "/product/product-form";
    }

    @RequestMapping("/copy/")
    public String copy(@ModelAttribute("product") Product product, Model model) {
        product.setCodeList(codeList);

        String description = "Kopiowanie produktu " + product.getName() + " ";

        return saveOrUpdate(product, description, model);
    }

    @RequestMapping(value = "search")
    public String search(Model model, @ModelAttribute("searchProduct") Product searchProduct){
        List<Product> products = productService.search(searchProduct);

        model.addAttribute("products", products);
        model.addAttribute("actionDescription", "LISTA PRODUKTÓW");
        model.addAttribute("searchProduct", searchProduct);

        return "/product/product-list";
    }

    @RequestMapping(value = "/list-details/")
    public String listDetails(Model model) {
        model.addAttribute("actionDescription", "LISTA ELEMENTÓW PRODUKTU");
        modelForm(model);

        return "/product/product-details-list";
    }

    @RequestMapping("/save-code/{productId}")
    public String saveCode(@ModelAttribute("code") Code code, @PathVariable int productId) {
        Product product = productService.get(productId);
        product.addCode(code);
        productService.saveOrUpdate(product);

        return "redirect:/product/update/" + productId;
    }

    @RequestMapping(value = "/delete-code/{codeId}/{productId}")
    public String deleteCode(@PathVariable int codeId, @PathVariable int productId) {
        Code code = productDetailsService.getCode(codeId);
        productDetailsService.deleteCode(code);
        return "redirect:/product/update/" + productId;
    }
}
