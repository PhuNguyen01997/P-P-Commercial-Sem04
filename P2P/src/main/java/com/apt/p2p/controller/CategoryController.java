package com.apt.p2p.controller;

import com.apt.p2p.entity.Category;
import com.apt.p2p.model.view.AdminHeaderNavi;
import com.apt.p2p.model.view.ToastResponse;
import com.apt.p2p.service.CategoryService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
                new String[]{"Category", ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Category", naviArr));

        model.addAttribute("categoryForm", new Category());

        return "admin/category";
    }

    @PostMapping("/admin/category/update")
    public String update(Model model,
                         @Valid @ModelAttribute Category category,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());

            List<String[]> naviArr = Arrays.asList(
                    new String[]{"Home", "/admin"},
                    new String[]{"Category", ""}
            );
            model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Category", naviArr));

            model.addAttribute("categoryForm", new Category());

            return "admin/category";
        }

        Category updatedCategory = categoryService.updateName(category.getId(), category.getName());
        if (updatedCategory != null) {
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(1, "Update category successful"));
        } else {
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(0, "Something wrong, please check again later"));
        }
        return "redirect:/admin/category";
    }

    @PostMapping("/admin/category/create")
    public String create(Model model,
                         @Valid @ModelAttribute("categoryForm") Category categoryForm,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());

            List<String[]> naviArr = Arrays.asList(
                    new String[]{"Home", "/admin"},
                    new String[]{"Category", ""}
            );
            model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Category", naviArr));

            return "admin/category";
        }

        Category newCategory = categoryService.save(categoryForm);
        if (newCategory != null) {
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(1, "Create new category successful"));
        } else {
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(0, "Something wrong, please check again later"));
        }
        return "redirect:/admin/category";
    }

    @DeleteMapping("/admin/category/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes){
        try {
            categoryService.deleteById(id);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("responseMessage", new ToastResponse(0, e.getMessage()));
        }
        return "redirect:/admin/category";
    }
}
