package com.company.ordersystem.service.product;

import com.company.ordersystem.entity.product.Code;
import com.company.ordersystem.entity.product.Color;
import com.company.ordersystem.entity.product.Format;
import com.company.ordersystem.entity.product.Paper;

import java.util.List;

public interface IProductDetailsService {
    Color getColor(int id);
    List<Color> getAllColor();
    boolean saveOrUpdateColor(Color color);
    boolean deleteColor(Color color);

    Format getFormat (int id);
    List<Format> getAllFormat();
    boolean saveOrUpdateFormat(Format format);
    boolean deleteFormat (Format format);

    Paper getPaper(int id);
    List<Paper> getAllPaper();
    boolean saveOrUpdatePaper(Paper paper);
    boolean deletePaper(Paper paper);

    boolean saveOrUpdateCode(Code code);
    boolean deleteCode(Code code);
    Code getCode (int id );
}
