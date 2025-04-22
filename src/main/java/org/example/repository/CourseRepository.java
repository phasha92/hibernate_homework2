package org.example.repository;

import org.example.model.Course;
import org.example.model.Teacher;
import org.example.util.types.CourseType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;

public class CourseRepository {
    private final SessionFactory sessionFactory;

    public CourseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Set<Course> findAll() {
        Set<Course> resultSet = new TreeSet<>(Comparator.comparingInt(Course::getId));
        try (Session session = sessionFactory.openSession()) {
            resultSet.addAll(session.createQuery("FROM Course", Course.class).list());
        }
        return resultSet;
    }

    public Course findById(Integer id) {
        Course course;
        try (Session session = sessionFactory.openSession()) {
            course = session.createQuery("FROM Course WHERE id=:id", Course.class)
                    .setParameter("id", id)
                    .uniqueResultOptional().orElse(null);
        }
        return course;
    }

    public Teacher findTeacherByCourse(int id) {
        return findById(id).getTeacher();
    }

    public Set<Course> findAllCoursesByName(String namePart) {
        Set<Course> resultSet = new TreeSet<>(Comparator.comparingInt(Course::getId));
        try (Session session = sessionFactory.openSession()) {
            resultSet.addAll(session.createQuery("FROM Course WHERE LOWER(name) LIKE :namePart", Course.class)
                    .setParameter("namePart", "%" + namePart.toLowerCase() + "%")
                    .list());
        }
        return resultSet;
    }

    public Set<Course> findAllCoursesByType(String type) {
        Set<Course> resultSet = new LinkedHashSet<>();
        try (Session session = sessionFactory.openSession()) {
            CourseType courseType = CourseType.valueOf(type.toUpperCase());
            String hql = "FROM Course c JOIN FETCH c.teacher WHERE c.type = :type";
            resultSet.addAll(session.createQuery(hql, Course.class)
                    .setParameter("type", courseType)
                    .list());
        }
        return resultSet;
    }

    public Course getCourseByName(String name) {
        Course course;
        try (Session session = sessionFactory.openSession()) {
            course = session.createQuery("FROM Course WHERE LOWER(name) = :name", Course.class)
                    .setParameter("name", name.toLowerCase())
                    .uniqueResultOptional().orElse(null);
        }
        return course;
    }

}