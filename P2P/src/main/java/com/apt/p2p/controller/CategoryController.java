package com.apt.p2p.controller;

import com.apt.p2p.entity.Category;
import com.apt.p2p.model.view.ToastResponse;
import com.apt.p2p.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
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
