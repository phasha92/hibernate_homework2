package org.example.service;

import org.example.model.Teacher;
import org.example.repository.TeacherRepository;

import java.util.Set;

public class TeacherService {

    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Set<Teacher> findAll() {
        return repository.findAll();
    }
}
