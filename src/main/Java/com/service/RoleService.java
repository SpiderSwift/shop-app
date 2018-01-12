package com.service;

import com.DAO.DAO;
import com.exception.DAOException;
import com.object.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private DAO<Role> roleDAO;

    @Transactional
    public void addRole(Role role) {
        try {
            roleDAO.create(role);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteRole(Role role) {
        try {
            roleDAO.delete(role);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void editRole(Role role) {
        try {
            roleDAO.update(role);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public Role getById(Integer id) {
        try {
            return roleDAO.readById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public List<Role> getAllRoles() {
        try {
            return roleDAO.read();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
