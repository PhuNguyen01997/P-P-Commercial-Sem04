package com.apt.p2p.common;

import com.apt.p2p.entity.Category;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    private CategoryService categoryService;

    private List<Category> headerCategories;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(headerCategories == null){
            headerCategories = categoryService.findAll();
            headerCategories = headerCategories.stream().sorted(Comparator.comparingInt(Category::getId).reversed()).limit(8).collect(Collectors.toList());
        }
        request.setAttribute("headerCategories", headerCategories);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
