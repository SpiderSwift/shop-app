package com.service;

import com.DAO.DAO;
import com.exception.DAOException;
import com.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private DAO<Product> productDAO;

    @Transactional
    public void addProduct(Product product) {
        try {
            productDAO.create(product);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteProduct(Product product) {
        try {
            productDAO.delete(product);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void editProduct(Product product) {
        try {
            productDAO.update(product);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public Product getById(Integer id) {
        try {
            return productDAO.readById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getActiveProducts(List<Product> products) {
        List<Product> activeProducts = new ArrayList<Product>();
        for (Product product : products) {
            if (product.getCategory().getStatus()) {
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }

    @Transactional
    public List<Product> getAllProducts() {
        try {
            return productDAO.read();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
