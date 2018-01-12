package com.service;

import com.DAO.DAO;
import com.exception.DAOException;
import com.object.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService extends Object {

    @Autowired
    private DAO<Order> orderDAO;

    @Autowired
    private DAO<CartProduct> cartProductDAO;

    @Transactional
    public void addOrder(Order order) {
        try {
            orderDAO.create(order);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteOrder(Order order) {
        try {
            orderDAO.delete(order);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void editOrder(Order order) {
        try {
            orderDAO.update(order);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public Order getById(Integer id) {
        try {
            return orderDAO.readById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public List<Order> getAllOrders() {
        try {
            return orderDAO.read();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fromCart(Order order, Cart cart) {
        List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
        for (CartProduct product : cart.getCartProducts()) {
            OrderProduct orderProduct = new OrderProduct();
            OrderProductId orderProductId = new OrderProductId();
            orderProductId.setOrder(order);
            orderProductId.setProduct(product.getProduct());
            orderProduct.setOrderProductId(orderProductId);
            orderProduct.setNumber(product.getNumber());
            orderProducts.add(orderProduct);
        }
        order.setOrderedProducts(orderProducts);
    }
}
