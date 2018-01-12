package com.service;

import com.DAO.DAO;
import com.exception.DAOException;
import com.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RegisterService {

    @Autowired
    DAO<User> userDAO;


    @Transactional
    public void addUser(com.object.User user) {
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        try {
            List<User> registered = userDAO.read();
            for (User user : registered) {
                if (user.getRole().getName().equals("user"))
                    users.add(user);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Transactional
    public User getById(String username) {
        try {
            return userDAO.readById(username);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
