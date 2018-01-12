package com.service;

import com.DAO.DAO;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.object.Cart;
import com.object.CartProduct;
import com.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private DAO<Cart> cartDAO;

    @Autowired
    private ProductService productService;

    @Transactional
    public void addCart(Cart cart) {
        try {
            cartDAO.create(cart);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteCart(Cart cart) {
        try {
            cartDAO.delete(cart);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void editCart(Cart cart) {
        try {
            cartDAO.update(cart);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public Cart getById(Integer id) {
        try {
            return cartDAO.readById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public List<Cart> getAllCarts() {
        try {
            return cartDAO.read();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkAvailability(Cart cart) throws ServiceException {
        for (CartProduct product : cart.getCartProducts()) {
            Integer ordered = product.getNumber();
            Integer available = product.getProduct().getNumberAvailable();
            Integer delta = ordered - available;
            if (delta > 0) {
                throw new ServiceException("available amount < ordered");
            }
        }
    }

    public void excludeOrdered(Cart cart) {
        for (CartProduct product : cart.getCartProducts()) {
            Integer ordered = product.getNumber();
            Product prod = product.getProduct();
            prod.setNumberAvailable(prod.getNumberAvailable() - ordered);
            productService.editProduct(prod);

        }
    }

    public void clearCart(Cart cart) {
        for (CartProduct product : cart.getCartProducts()) {
            product.setCart(null);
            product.setProduct(null);
        }
        cart.getCartProducts().clear();
    }
}
