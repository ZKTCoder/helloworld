package com.example.hello.dao;

import com.example.hello.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findByParam(int id, String name) {

        String hql = "from User where id = ?0 and name = ?1";
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        query.setParameter(1, name);
        return query.list();
    }

}
