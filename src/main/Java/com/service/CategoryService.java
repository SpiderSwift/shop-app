package com.service;

import com.DAO.DAO;
import com.exception.DAOException;
import com.object.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private DAO<Category> categoryDAO;

    @Transactional
    public void addCategory(Category category) {
        try {
            categoryDAO.create(category);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteCategory(Category category) {
        try {
            categoryDAO.delete(category);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void editCategory(Category category) {
        try {
            categoryDAO.update(category);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public Category getById(Integer id) {
        try {
            return categoryDAO.readById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public List<Category> getAllCategories() {
        try {
            return categoryDAO.read();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
