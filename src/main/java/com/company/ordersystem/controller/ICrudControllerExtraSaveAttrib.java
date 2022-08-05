package com.company.ordersystem.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ICrudControllerExtraSaveAttrib<T, G> extends ICrudController<T> {

    @RequestMapping("/save2/")
    public String save2(@ModelAttribute("T") T t, @ModelAttribute("G") G g, Model model);

}
