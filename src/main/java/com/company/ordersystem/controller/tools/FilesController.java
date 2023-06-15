package com.company.ordersystem.controller.tools;

import com.company.ordersystem.service.tools.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private IFileService fileService;

    @RequestMapping()
    public String showPage (Model model){
        model.addAttribute("actionDescription", "PLIKI");
        return "tools/files";
    }

    @GetMapping("/list")
    public String getFiles(@RequestParam("folderPath") String folderPath, Model model) {
        List<String> fileNames = fileService.getFileNames(folderPath);
        model.addAttribute("actionDescription", "PLIKI");
        model.addAttribute("fileNames", fileNames);

        return "/tools/files";
    }

}
