package org.example.repository;

import org.example.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TeacherRepository {

    private final SessionFactory sessionFactory;

    public TeacherRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Set<Teacher> findAll() {
        Set<Teacher> resultSet = new TreeSet<>(Comparator.comparingInt(Teacher::getId));
        try(Session session = sessionFactory.openSession()) {
            resultSet.addAll(session.createQuery("FROM Teacher", Teacher.class).list());
        }
        return resultSet;
    }
}
