package com.DAO;

import com.exception.DAOException;

import java.io.Serializable;
import java.util.List;

public interface DAO<O> {
    void create(O object) throws DAOException;
    List<O> read() throws DAOException;
    void update(O object) throws DAOException;
    void delete(O object) throws DAOException;
    O readById(Serializable id) throws DAOException;
}
