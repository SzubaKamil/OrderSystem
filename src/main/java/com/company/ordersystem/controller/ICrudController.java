package com.company.ordersystem.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


public interface ICrudController<T>{

    @RequestMapping(value = "/new")
    String newForm(Model model);

    @RequestMapping(value = "/list")
    String listForm(Model model);

    @RequestMapping(value = "/update/{id}")
    String updateForm(@PathVariable int id, Model model);

    @RequestMapping(value = "/delete/{id}")
    String delete (@PathVariable int id,  Model model);

    @RequestMapping("/update/")
    String update(@ModelAttribute("T") T t, Model model );

    @RequestMapping("/save/")
    String save(@ModelAttribute("T") T t, Model model);

    String saveOrUpdate(T t, String description, Model model);

    void modelForm(Model model);

}
