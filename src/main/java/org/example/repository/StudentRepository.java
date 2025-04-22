package org.example.repository;

import org.example.model.Course;
import org.example.model.Student;
import org.example.model.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;

public class StudentRepository {

    private final SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Set<Student> findAll() {
        Set<Student> resultSet = new TreeSet<>(Comparator.comparingInt(Student::getId));
        try (Session session = sessionFactory.openSession()) {
            resultSet.addAll(session.createQuery("from Student", Student.class).list());
        }
        return resultSet;
    }

    public Student findById(Integer id) {
        Student student;
        try (Session session = sessionFactory.openSession()) {
            student = session.createQuery("from Student where id=:id", Student.class)
                    .setParameter("id", id)
                    .uniqueResultOptional()
                    .orElse(null);

        }
        return student;
    }

    public Set<Student> findStudentsByName(String namePart) {
        String query = "FROM Student WHERE LOWER(name) LIKE :namePart";
        Set<Student> resultSet = new LinkedHashSet<>();
        try (Session session = sessionFactory.openSession()) {
            resultSet.addAll(session.createQuery(query, Student.class)
                    .setParameter("namePart", "%" + namePart.toLowerCase() + "%")
                    .list());
        }
        return resultSet;
    }

    public Set<Course> findAllCoursesByStudent(int id) {
        Set<Course> resultSet = new LinkedHashSet<>();
        Student student = findById(id);
        if (student != null) {
            try (Session session = sessionFactory.openSession()) {
                resultSet.addAll(session
                        .createQuery("FROM Subscription", Subscription.class)
                        .stream()
                        .filter(subscription -> subscription.getStudent().getId().equals(id))
                        .map(Subscription::getCourse)
                        .toList()
                );
            }
        }
        return resultSet;
    }

    public Student getStudentByName(String name) {
        Student student;
        try (Session session = sessionFactory.openSession()) {
            student = session.createQuery("FROM Student WHERE LOWER(name) = :name", Student.class)
                    .setParameter("name", name.toLowerCase())
                    .uniqueResultOptional().orElse(null);
        }
        return student;
    }
}
