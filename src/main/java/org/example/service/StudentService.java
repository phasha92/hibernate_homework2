package org.example.service;

import org.example.model.Course;
import org.example.model.Student;
import org.example.repository.StudentRepository;

import java.util.Set;

public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Set<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Integer id) {
        return repository.findById(id);
    }

    public Set<Course> findAllCoursesByStudent(int id) {
        return repository.findAllCoursesByStudent(id);
    }

    public Set<Student> findStudentsByName(String namePart) {
        return repository.findStudentsByName(namePart);
    }

    public Student getStudentByName(String name) {
        return repository.getStudentByName(name);
    }
}

