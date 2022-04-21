package com.apt.p2p.common;

import com.apt.p2p.entity.Category;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    private CategoryService categoryService;

    private List<Category> headerCategories;
    private Map<String, String> naviPortalHeader;
    private List<String[]> dataNaviPortalHeader = Arrays.asList(
            new String[]{"/portal/order", "Orders"},
            new String[]{"/portal/product", "Products"},
            new String[]{"/portal/product/create", "Add new products"},
            new String[]{"/portal/rate", "Reviews"},
            new String[]{"/portal/", "My Store"},
            new String[]{"/portal/fund", "Fund store"},
            new String[]{"/portal/fund/withdraw", "Request withdraw"}
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        headerCategories = categoryService.findAll();
        headerCategories = headerCategories.stream().sorted(Comparator.comparingInt(Category::getId).reversed()).limit(8).collect(Collectors.toList());

        if (naviPortalHeader == null) {
            naviPortalHeader = new HashMap<>();
            dataNaviPortalHeader.forEach(arrStr -> {
                naviPortalHeader.put(arrStr[0], arrStr[1]);
            });
        }

        String requestUri = request.getRequestURI();
        if (requestUri.indexOf("/portal") != -1) {
            request.setAttribute("portalHeaderNavi", this.naviPortalHeader.get(requestUri));
        }
        request.setAttribute("headerCategories", headerCategories);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public void setNaviPortalHeader(Map<String, String> naviPortalHeader) {
        this.naviPortalHeader = naviPortalHeader;
    }
}
