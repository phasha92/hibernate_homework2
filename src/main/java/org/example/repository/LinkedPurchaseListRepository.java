package org.example.repository;

import org.example.model.LinkedPurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedPurchaseListRepository {

    private final SessionFactory sessionFactory;

    public LinkedPurchaseListRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isExistKey(Integer studentId, Integer courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from LinkedPurchaseList where student_id=:studentId and course_id=:courseId",
                            LinkedPurchaseList.class)
                    .setParameter("studentId", studentId)
                    .setParameter("courseId", courseId)
                    .uniqueResultOptional().isPresent();
        }
    }

    public Set<LinkedPurchaseList> findAll() {
        Set<LinkedPurchaseList> resultSet = new LinkedHashSet<>();
        try (Session session = sessionFactory.openSession()) {
            resultSet.addAll(session.createQuery("from LinkedPurchaseList", LinkedPurchaseList.class).list());
        }
        return resultSet;
    }
}
