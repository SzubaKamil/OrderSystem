//package com.company.ordersystem.controller.product;
//
//
//import com.company.ordersystem.entity.product.*;
//import com.company.ordersystem.entity.product.Color;
//import com.company.ordersystem.service.product.IProductDetailsService;
//import com.company.ordersystem.service.product.IProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.awt.*;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RequestMapping("/product")
//@Controller
//public class ProductController_OLD{
//
//    @Autowired
//    IProductService productService;
//    @Autowired
//    IProductDetailsService productDetailsService;
//
//    private List<Code> codeList;

//    public ProductController() {
//        this.codeList = new ArrayList<>();
//    }

//    @RequestMapping("/new")
//    public String newForm(Model model) {
//        Product product = new Product();
//        codeList = new ArrayList<>();
//        product.setCodeList(codeList);
//
//        model.addAttribute("product", product);
//        model.addAttribute("action", "/product/save/");
//        model.addAttribute("actionDescription", "NOWY PRODUKT");
//
//        modelForm(model);
//
//        return "/product/product-form";
//    }

//    @RequestMapping(value = "/update/{productId}")
////    @RequestMapping(value = "/update/{productId}", method = RequestMethod.POST )
//    public String updateForm(@PathVariable int productId, Model model) {
//        Product product;
//
//        if (productId > 0) {
//            product = productService.get(productId);
//            codeList = product.getCodeList();
//        } else {
//            product = new Product();
//            product.setCodeList(codeList);
//        }
//
//        model.addAttribute("product", product);
//        model.addAttribute("action", "/product/update/");
//        model.addAttribute("actionDescription", "EDYCJA PRODUKTU: " + product.getName());
//
//        modelForm(model);
//
//        return "/product/product-form";
//    }

//    @RequestMapping(value = "/copy/{productId}")
////    @RequestMapping(value = "/update/{companyId}", method = RequestMethod.POST )
//    public String copyForm(@PathVariable int productId, Model model) {
//        Product oldProduct = new Product();
//        Product product;
//
//        if (productId > 0) {
//            oldProduct = productService.get(productId);
//            try {
//                product = oldProduct.clone();
//            }
//            catch (CloneNotSupportedException e) {
//                throw new RuntimeException(e);
//            }
//            codeList = product.getCodeList();
//        }
//        else {
//            product = new Product();
//            product.setCodeList(codeList);
//        }
//
//        model.addAttribute("product", product);
//        model.addAttribute("action", "/product/copy/");
//        model.addAttribute("actionDescription", "KOPIOWANIE Z PRODUKTU: " + oldProduct.getName());
//
//        modelForm(model);
//
//        return "/product/product-form";
//    }

//    @RequestMapping(value = "/delete/{productId}")
//    public String delete(@PathVariable int productId, Model model) {
//        Product product = productService.get(productId);
//
//        boolean result = productService.delete(product);
//        String description = "Usunięcie produktu: " + product.getName() + " ";
//
//        model.addAttribute("description", description);
//        model.addAttribute("result", result);
//
//        return "result";
//    }

//    @RequestMapping(value = "/list/")
//    public String list(Model model) {
//        List<Product> products = productService.getAll();
//
//        model.addAttribute("products", products);
//        model.addAttribute("actionDescription", "LISTA PRODUKTÓW");
//        model.addAttribute("searchProduct", new Product());
//
//        return "/product/product-list";
//    }

//    @RequestMapping(value = "search")
//    public String search(Model model, @ModelAttribute("searchProduct") Product searchProduct){
//        List<Product> products = productService.search(searchProduct);
//
//        model.addAttribute("products", products);
//        model.addAttribute("actionDescription", "LISTA PRODUKTÓW");
//        model.addAttribute("searchProduct", searchProduct);
//
//        return "/product/product-list";
//    }
//
//    @RequestMapping(value = "/list-details/")
//    public String listDetails(Model model) {
//        model.addAttribute("actionDescription", "LISTA ELEMENTÓW PRODUKTU");
//        modelForm(model);
//
//        return "/product/product-details-list";
//    }

//    @RequestMapping("/save/")
////    public String save(@ModelAttribute("contractor") Contractor contractor, @ModelAttribute("contact") Contact contact, Model model){
//    public String save(@ModelAttribute("product") Product product, Model model) {
//        product.setCodeList(codeList);
//
//        String description = "Dodanie nowego produktu " + product.getName() + " ";
//
//        return saveOrUpdate(product, description, model);
//    }

//    @RequestMapping("/update/")
//    public String update(@ModelAttribute("product") Product product, Model model) {
//        product.setCodeList(codeList);
//
//        String description = "Edycja produktu " + product.getName() + " ";
//
//        return saveOrUpdate(product, description, model);
//    }

//    @RequestMapping("/copy/")
//    public String copy(@ModelAttribute("product") Product product, Model model) {
//        product.setCodeList(codeList);
//
//        String description = "Kopiowanie produktu " + product.getName() + " ";
//
//        return saveOrUpdate(product, description, model);
//    }



//    @RequestMapping("/save-code/{productId}")
//    public String saveCode(@ModelAttribute("code") Code code, @PathVariable int productId) {
//        Product product = productService.get(productId);
//        product.addCode(code);
//        productService.saveOrUpdate(product);
//
//        return "redirect:/product/update/" + productId;
//    }
//
//    //    @RequestMapping(value = "/delete-code/{codeListId}/{productId}")
////    public String deleteCode (@PathVariable int codeListId, @PathVariable int productId){
//    @RequestMapping(value = "/delete-code/{codeId}/{productId}")
//    public String deleteCode(@PathVariable int codeId, @PathVariable int productId) {
//        Code code = productDetailsService.getCode(codeId);
//        productDetailsService.deleteCode(code);
//        return "redirect:/product/update/" + productId;
//    }

//    private String saveOrUpdate(Product product, String description, Model model) {
//        boolean result = productService.saveOrUpdate(product);
//
//        model.addAttribute("result", result);
//        model.addAttribute("description", description);
//
//        return "result";
//    }

//    /** COPIED **/
//    private void modelForm(Model model) {
//        model.addAttribute("formatList", productDetailsService.getAllFormat());
//        model.addAttribute("paperList", productDetailsService.getAllPaper());
//        model.addAttribute("colorList", productDetailsService.getAllColor());
//
//        model.addAttribute("code", new Code());
////        model.addAttribute("actionCode", "/product/save-code/");
//
//        model.addAttribute("color", new Color());
//        model.addAttribute("format", new Format());
//        model.addAttribute("paper", new Paper());
//        model.addAttribute("actionColor", "/product-details/saveColor/");
//        model.addAttribute("actionFormat", "/product-details/saveFormat/");
//        model.addAttribute("actionPaper", "/product-details/savePaper/");
//    }

//}
