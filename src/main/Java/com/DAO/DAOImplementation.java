package com.DAO;

import com.exception.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class DAOImplementation<O> implements DAO<O> {

    @Autowired
    private SessionFactory factory;
    private final Class<O> type;

    public DAOImplementation(Class<O> type) {
        this.type = type;
    }

    private Class<O> getType() {
        return this.type;
    }

    public void create(O object) throws DAOException {
        factory.getCurrentSession().save(object);
    }

    public List<O> read() throws DAOException {
        Session session = factory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<O> criteria = builder.createQuery(getType());
        Root<O> root = criteria.from(getType());
        criteria.select(root);
        return session.createQuery(criteria).getResultList();
    }

    public void update(O object) throws DAOException {
        factory.getCurrentSession().update(object);
    }

    public void delete(O object) throws DAOException {
        factory.getCurrentSession().remove(object);

    }

    public O readById(Serializable id) throws DAOException {
        return factory.getCurrentSession().get(getType(), id);
    }
}
