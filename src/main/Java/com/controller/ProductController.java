package com.controller;

import com.object.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

 @Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RegisterService userService;
    @Autowired
    private CartService cartService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Product> products = productService.getAllProducts();
        modelAndView.addObject("listProducts", productService.getActiveProducts(products));
        return modelAndView;
    }


    @RequestMapping(value = "/user/product", method = RequestMethod.GET)
    public ModelAndView prod() {
        ModelAndView modelAndView = new ModelAndView("/user/product/products");
        List<Product> products = productService.getAllProducts();
        modelAndView.addObject("listProducts", productService.getActiveProducts(products));
        return modelAndView;
    }


    @RequestMapping(value = "/user/product/addToCart", method = RequestMethod.GET)
    public String addToCart(@RequestParam("productId") Integer id, @RequestParam("number") Integer number ) {
        Product product = productService.getById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getById(username);
        Cart cart = user.getCart();
        boolean inCart = false;
        for (CartProduct cartProduct : product.getCarts()) {
            if (cartProduct.getProduct().equals(product)) {
                inCart = true;
                cartProduct.setNumber(number);
            }
        }
        if (!inCart) {
            CartProduct cartProduct = new CartProduct();
            CartProductId cartProductId = new CartProductId();
            cartProductId.setCart(cart);
            cartProductId.setProduct(product);
            cartProduct.setCartProductId(cartProductId);
            cartProduct.setNumber(number);
            cart.getCartProducts().add(cartProduct);
            product.getCarts().add(cartProduct);
        }
        productService.editProduct(product);
        cartService.editCart(cart);
        return "redirect:../product";
    }


    @RequestMapping(value = "/admin/product/add", method = RequestMethod.GET)
    public ModelAndView product() {
        ModelAndView modelAndView = new ModelAndView("/admin/product/add", "command", new Product());
        modelAndView.addObject("listCategory", categoryService.getAllCategories());
        return modelAndView;
    }


    @RequestMapping(value="/admin/product/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id){
        Product product = productService.getById(id);
        ModelAndView modelAndView = new ModelAndView("/admin/product/edit","command", product);
        modelAndView.addObject("listCategory", categoryService.getAllCategories());
        return modelAndView;
    }

    @RequestMapping(value="/admin/product/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        productService.deleteProduct(productService.getById(id));
        return "redirect:../../product";
    }


    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "/admin/product/products";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("shop-app")Product product, @RequestParam("categoryId") Integer id) {
        Category category = categoryService.getById(id);
        product.setCategory(category);
        productService.addProduct(product);
        return "redirect:admin/product";
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("shop-app")Product product, @RequestParam("categoryId") Integer id) {
        Category category = categoryService.getById(id);
        product.setCategory(category);
        productService.editProduct(product);
        return "redirect:admin/product";
    }

}
