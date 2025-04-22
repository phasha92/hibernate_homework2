package org.example.handler;

import org.example.service.TeacherService;

import java.util.Scanner;

public class TeacherHandler extends Handler{

    private final TeacherService service;

    public TeacherHandler(TeacherService service) {
        this.service = service;
    }
    @Override
    public void handle(Scanner scanner) {
        service.findAll().forEach(System.out::println);
    }
}
