package com.controller;

import com.object.Order;
import com.object.User;
import com.service.OrderService;
import com.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RegisterService userService;


    @RequestMapping(value = "/user/orders", method = RequestMethod.GET)
    public ModelAndView viewOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ModelAndView modelAndView = new ModelAndView("/user/order/orders");
        User user = userService.getById(username);
        modelAndView.addObject("orders", user.getOrders());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/order/{username}", method = RequestMethod.GET)
    public ModelAndView viewUserOrders(@PathVariable("username") String username) {
        User user = userService.getById(username);
        List<Order> orders = user.getOrders();
        ModelAndView modelAndView = new ModelAndView("/admin/order/orders");
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("username", user.getUserName());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/order", method = RequestMethod.GET)
    public ModelAndView viewUsers() {
        ModelAndView modelAndView = new ModelAndView("/admin/order/users");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/order/remove/{username}/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") Integer id, @PathVariable("username") String username) {
        Order order = orderService.getById(id);
        orderService.deleteOrder(order);
        return "redirect:../../" + username;
    }

    @RequestMapping(value = "/admin/order/{username}/{id}", method = RequestMethod.GET)
    public String changeStatus(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        Order order = orderService.getById(id);
        order.setStatus(!order.getStatus());
        orderService.editOrder(order);
        return "redirect:../" + username;
    }


}
