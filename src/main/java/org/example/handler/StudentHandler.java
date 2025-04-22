package org.example.handler;

import org.example.model.Student;
import org.example.service.StudentService;
import org.example.view.Menu;

import java.util.Scanner;

public class StudentHandler extends Handler {

    private final StudentService service;

    public StudentHandler(StudentService service) {
        this.service = service;
    }

    @Override
    public void handle(Scanner scanner) {
        while (true) {
            Menu.showStudentsMenu();
            switch (scanner.nextLine()) {
                case "1" -> service.findAll().forEach(System.out::println);
                case "2" -> {
                    logger.info(INPUT_ID);
                    Integer id = readInt(scanner);
                    Student student = service.findById(id);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        logger.warning(STUDENT_NOT_FOUND);
                    }
                }
                case "3" -> {
                    logger.info(INPUT_ID);
                    Integer id = readInt(scanner);
                    service.findAllCoursesByStudent(id).forEach(System.out::println);
                }
                case "4" -> {
                    logger.info(INPUT_NAME);
                    String name = scanner.nextLine();
                    service.findStudentsByName(name).forEach(System.out::println);
                }
                case "5" -> {
                    logger.info(INPUT_NAME);
                    String name = scanner.nextLine();
                    Student student = service.getStudentByName(name);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        logger.warning(STUDENT_NOT_FOUND);
                    }
                }
                case "exit" -> {
                    return;
                }
                default -> logger.warning(DEFAULT_CHOICE);
            }
        }
    }
}
