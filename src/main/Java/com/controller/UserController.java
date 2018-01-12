package com.controller;

import com.object.Cart;
import com.object.User;
import com.service.CartService;
import com.service.RegisterService;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    public static final int ROLE_USER = 2;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CartService cartService;

    @Autowired
    private RegisterService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("/login");
    }




    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error() {
        return new ModelAndView("/error");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("/register");
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.getById(username) == null) {
            User user = new User();
            user.setRole(roleService.getById(ROLE_USER));
            user.setUserName(username);
            user.setUserPass(password);
            Cart cart = new Cart();
            cartService.addCart(cart);
            userService.addUser(user);
            cart.setOwner(user);
            cartService.editCart(cart);
        } else {
            ModelAndView modelAndView = new ModelAndView("/register");
            modelAndView.addObject("errorMessage", "User exists!");
            return modelAndView;
        }
        return new ModelAndView("/login");
    }

}
