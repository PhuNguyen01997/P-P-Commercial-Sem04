package com.apt.p2p.controller;

import com.apt.p2p.entity.Category;
import com.apt.p2p.model.view.AdminHeaderNavi;
import com.apt.p2p.model.view.ToastResponse;
import com.apt.p2p.service.CategoryService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"Danh mục", ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Danh mục", naviArr));

        return "admin/category";
    }

    @PostMapping("/admin/category/update")
    public String update(@ModelAttribute Category category, RedirectAttributes redirectAttributes){
        Category result = categoryService.updateName(category.getId(), category.getName());
        if(result != null){
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(1, "Cập nhật danh mục thành công"));
        }else{
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(0, "Có lỗi xảy ra, vui lòng thử lại sau"));
        }
        return "redirect:/admin/category";
    }

    @PostMapping("/admin/category/create")
    public String create(@ModelAttribute Category category, RedirectAttributes redirectAttributes){
        Category result = categoryService.save(category);
        if(result != null){
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(1, "Tạo mới danh mục thành công"));
        }else{
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(0, "Có lỗi xảy ra, vui lòng thử lại sau"));
        }
        return "redirect:/admin/category";
    }
}
