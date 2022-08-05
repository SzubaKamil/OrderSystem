package com.company.ordersystem.service.product.impl;


import com.company.ordersystem.dao.product.ICodeDAO;
import com.company.ordersystem.dao.product.IColorDAO;
import com.company.ordersystem.dao.product.IFormatDAO;
import com.company.ordersystem.dao.product.IPaperDAO;
import com.company.ordersystem.entity.product.Code;
import com.company.ordersystem.service.product.IProductDetailsService;
import com.company.ordersystem.entity.product.Color;
import com.company.ordersystem.entity.product.Format;
import com.company.ordersystem.entity.product.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class ProductDetailsService implements IProductDetailsService {

    @Autowired
    IColorDAO colorDAO;
    @Autowired
    IFormatDAO formatDAO;
    @Autowired
    IPaperDAO paperDAO;
    @Autowired
    ICodeDAO codeDAO;

    @Override
    public Color getColor(int id) {
        return colorDAO.get(id);
    }

    @Override
    public List<Color> getAllColor() {
        return colorDAO.getAll();
    }

    @Override
    public boolean saveOrUpdateColor(Color color) {
        return colorDAO.saveOrUpdate(color);
    }

    @Override
    public boolean deleteColor(Color color) {
        try {
            return colorDAO.delete(color);
        }
        catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Format getFormat(int id) {
        return formatDAO.get(id);
    }

    @Override
    public List<Format> getAllFormat() {
        return formatDAO.getAll();
    }

    @Override
    public boolean saveOrUpdateFormat(Format format) {
        return formatDAO.saveOrUpdate(format);
    }

    @Override
    public boolean deleteFormat(Format format) {
        try {
            return formatDAO.delete(format);
        }
        catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Paper getPaper(int id) {
        return paperDAO.get(id);
    }

    @Override
    public List<Paper> getAllPaper() {
        return paperDAO.getAll();
    }

    @Override
    public boolean saveOrUpdatePaper(Paper paper) {
        return paperDAO.saveOrUpdate(paper);
    }

    @Override
    public boolean deletePaper(Paper paper) {
        try {
            return paperDAO.delete(paper);
        }
        catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveOrUpdateCode(Code code) {
        return codeDAO.saveOrUpdate(code);
    }

    @Override
    public boolean deleteCode(Code code) {
        return codeDAO.delete(code);
    }

    @Override
    public Code getCode(int id) {
        return codeDAO.get(id);
    }
}
