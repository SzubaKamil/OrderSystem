package com.company.ordersystem.controller.product;


import com.company.ordersystem.entity.product.Color;
import com.company.ordersystem.entity.product.Format;
import com.company.ordersystem.entity.product.Paper;
import com.company.ordersystem.service.product.IProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-details")
public class ProductDetailsController {

    @Autowired
    IProductDetailsService productDetailsService;

    @RequestMapping(value = "/new")
    public String newForm(Model model) {
        Color color = new Color();
        Format format = new Format();
        Paper paper = new Paper();

        model.addAttribute("actionDescription", "DODAJ DETALE PRODUKTU");

        model.addAttribute("color", color);
        model.addAttribute("format", format);
        model.addAttribute("paper", paper);

        model.addAttribute("actionColor", "/product-details/saveColor/");
        model.addAttribute("actionFormat", "/product-details/saveFormat/");
        model.addAttribute("actionPaper", "/product-details/savePaper/");

        return "/product/product-details-form";
    }

    @RequestMapping(value = "/update-color/{colorId}")
    public String updateColorForm(@PathVariable int colorId, Model model) {
        Color color = productDetailsService.getColor(colorId);
        model.addAttribute("color" , color);

        boolean updateColor = true;
        model.addAttribute("updateColor", updateColor);

        model.addAttribute("actionDescription", "EDYCJA KOLORU: " + color.getValue());
        model.addAttribute("actionColor", "/product-details/saveColor/");
        model.addAttribute("paper", new Paper());
        model.addAttribute("format", new Format());

        return "/product/product-details-form";
    }

    @RequestMapping(value = "/update-format/{formatId}")
    public String updateFormatForm(@PathVariable int formatId, Model model) {
        Format format = productDetailsService.getFormat(formatId);
        model.addAttribute("format" , format);

        boolean updateFormat = true;
        model.addAttribute("updateFormat", updateFormat);

        model.addAttribute("actionDescription", "EDYCJA FORMATU: " + format.getFormat());
        model.addAttribute("actionColor", "/product-details/saveFormat");
        model.addAttribute("paper", new Paper());
        model.addAttribute("color", new Color());

        return "/product/product-details-form";
    }

    @RequestMapping(value = "/update-paper/{paperId}")
    public String updatePaperForm(@PathVariable int paperId, Model model) {
        Paper paper = productDetailsService.getPaper(paperId);
        model.addAttribute("paper" , paper);

        boolean updatePaper = true;
        model.addAttribute("updatePaper", updatePaper);

        model.addAttribute("actionDescription", "EDYCJA PAPIERU: " + paper.getValue());
        model.addAttribute("actionColor", "/product-details/savePaper");
        model.addAttribute("color", new Color());
        model.addAttribute("format", new Format());

        return "/product/product-details-form";
    }

    @RequestMapping(value = "/delete-color/{colorId}")
    public String deleteColor(@PathVariable int colorId, Model model) {
        Color color = productDetailsService.getColor(colorId);

        boolean result = productDetailsService.deleteColor(color);
        String description = "Usunięcie koloru: " + color.getValue() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @RequestMapping(value = "/delete-format/{formatId}")
    public String deleteFormat(@PathVariable int formatId, Model model) {
        Format format = productDetailsService.getFormat(formatId);

        boolean result = productDetailsService.deleteFormat(format);
        String description = "Usunięcie formatu: " + format.getFormat() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @RequestMapping(value = "/delete-paper/{paperId}")
    public String deletePaper(@PathVariable int paperId, Model model) {

        Paper paper = productDetailsService.getPaper(paperId);

        boolean result = productDetailsService.deletePaper(paper);
        String description = "Usunięcie papieru: " + paper.getValue() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @RequestMapping("/saveColor/" )
    public String saveColor(@ModelAttribute("color") Color color, Model model){
        boolean result = productDetailsService.saveOrUpdateColor(color);
        String description = "Dodanie nowego koloru: " + color.getValue() + " ";

        return saveOrUpdate(result, description, model);
    }

    @RequestMapping("/saveFormat/" )
    public String saveFormat (@ModelAttribute("format") Format format, Model model){
        boolean result = productDetailsService.saveOrUpdateFormat(format);
        String description = "Dodanie nowego formatu: " + format.getFormat() + " ";

        return saveOrUpdate(result, description, model);
    }

    @RequestMapping("/savePaper/" )
    public String savePaper (@ModelAttribute("paper") Paper paper, Model model){
        boolean result = productDetailsService.saveOrUpdatePaper(paper);
        String description = "Dodanie nowego papieru: " + paper.getValue() + " ";

        return saveOrUpdate(result, description, model);
    }

    private String saveOrUpdate(boolean result, String description, Model model){
        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

}
