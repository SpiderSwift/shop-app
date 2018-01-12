package com.controller;


import com.exception.ServiceException;
import com.object.*;
import com.service.CartService;
import com.service.OrderService;
import com.service.ProductService;
import com.service.RegisterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Calendar;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RegisterService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


    private static Logger log = Logger.getLogger(CartController.class);

    @RequestMapping(path = "/user/cart", method = RequestMethod.GET)
    public ModelAndView cart() {
        ModelAndView modelAndView = new ModelAndView("/user/cart/products");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getById(username);
        Cart cart = user.getCart();
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }


    @RequestMapping(path = "/user/cart/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        Product product = productService.getById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getById(username);
        Cart cart = cartService.getById(user.getCart().getCartId());//user.getCart();
        for (CartProduct aProduct : cart.getCartProducts()) {
            if (aProduct.getProduct().equals(product)) {
                boolean result = cart.getCartProducts().remove(aProduct);
                log.info("1st : " + result);
                result = product.getCarts().remove(aProduct);
                log.info("2nd : " + result);
                aProduct.getProduct().getCarts().remove(aProduct);
                cart.getCartProducts().remove(aProduct);
                break;
            }
        }
        user.setCart(cart);
        cartService.editCart(cart);
        productService.editProduct(product);
        return "redirect:../../cart";
    }

    @RequestMapping(path = "/user/cart/order", method = RequestMethod.GET)
    public String orderProducts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getById(username);
        Cart cart = cartService.getById(user.getCart().getCartId());//user.getCart();
        try {
            cartService.checkAvailability(cart);
            cartService.excludeOrdered(cart);
            Order order = new Order();
            orderService.addOrder(order);
            orderService.fromCart(order, cart);
            order.setUser(user);
            order.setDate(new Date(Calendar.getInstance().getTime().getTime()));
            order.setStatus(false);
            //cart.setCartProducts(new HashSet<CartProduct>());
            orderService.editOrder(order);
            cartService.deleteCart(cart);
            Cart newCart = new Cart();
            cartService.addCart(newCart);
            newCart.setOwner(user);
            cartService.editCart(newCart);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:../cart";
    }


}
