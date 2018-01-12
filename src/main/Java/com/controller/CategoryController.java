package com.controller;

import com.object.Category;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.GET)
    public ModelAndView category() {
        return new ModelAndView("/admin/category/add", "command", new Category());
    }


    @RequestMapping(value="/admin/category/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id){
        Category category = categoryService.getById(id);
        return new ModelAndView("/admin/category/edit","command", category);
    }

    @RequestMapping(value="/admin/category/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        categoryService.deleteCategory(categoryService.getById(id));
        return "redirect:../../category";
    }


    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("listCategories", categoryService.getAllCategories());
        return "/admin/category/categories";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("shop-app")Category category) {
        categoryService.addCategory(category);
        return "redirect:admin/category";
    }

    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("shop-app")Category category) {
        categoryService.editCategory(category);
        return "redirect:admin/category";
    }
}
