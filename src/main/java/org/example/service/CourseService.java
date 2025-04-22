package org.example.service;

import org.example.model.Course;
import org.example.model.Teacher;
import org.example.repository.CourseRepository;
import org.example.util.types.CourseType;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Set<Course> findAll() {
        return repository.findAll();
    }

    public Course findById(Integer id) {
        return repository.findById(id);
    }

    public Teacher getTeacherById(Integer id) {
        return repository.findTeacherByCourse(id);
    }

    public Set<Course> findCourseByName(String name) {
        return repository.findAllCoursesByName(name);
    }

    public Set<Course> findCoursesByType(String type) {
        Set<Course> resultSet = new LinkedHashSet<>();
        String finalType = type.toUpperCase();
        if (Arrays.stream(CourseType.values())
                .noneMatch(t -> t.name().equalsIgnoreCase(finalType))) {
            return resultSet;
        }
        return repository.findAllCoursesByType(type);
    }

    public Course getCourseByName(String name) {
        return repository.getCourseByName(name);
    }
}
