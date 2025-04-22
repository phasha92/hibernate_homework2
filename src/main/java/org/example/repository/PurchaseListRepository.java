package org.example.repository;

import org.example.model.PurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class PurchaseListRepository {

    private final SessionFactory sessionFactory;

    public PurchaseListRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Set<PurchaseList> findAll() {
        Set<PurchaseList> resultSet = new LinkedHashSet<>();
        try (Session session = sessionFactory.openSession()) {
            resultSet.addAll(session.createQuery("from PurchaseList", PurchaseList.class).list());
        }
        return resultSet;
    }

}
