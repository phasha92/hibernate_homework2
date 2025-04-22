package org.example.handler;

import org.example.model.Course;
import org.example.model.Teacher;
import org.example.service.CourseService;
import org.example.util.types.CourseType;
import org.example.view.Menu;

import java.util.Scanner;
import java.util.Set;

public class CourseHandler extends Handler {
    private final CourseService service;

    public CourseHandler(CourseService service) {
        this.service = service;
    }

    @Override
    public void handle(Scanner scanner) {
        while (true) {
            Menu.showCoursesMenu();
            switch (scanner.nextLine()) {
                case "1" -> service.findAll().forEach(System.out::println);
                case "2" -> {
                    logger.info(INPUT_COURSE);
                    String name = scanner.nextLine();
                    service.findCourseByName(name).forEach(c -> logger.info(c.toString()));
                }
                case "3" -> {
                    logger.info(INPUT_COURSE);
                    Integer id = readInt(scanner);
                    Teacher teacher = service.getTeacherById(id);
                    System.out.println(teacher != null ? teacher.toString() : TEACHER_NOT_FOUND);
                }
                case "4" -> {
                    logger.info(INPUT_COURSE_TYPE);
                    for (CourseType type : CourseType.values()) {
                        logger.info(type.name());
                    }
                    String type = scanner.nextLine();
                    Set<Course> courses = service.findCoursesByType(type);
                    if (courses.isEmpty()) {
                        logger.warning(COURSE_NOT_FOUND);
                    } else {
                        courses.forEach(System.out::println);
                    }
                }
                case "5" -> {
                    logger.info(INPUT_COURSE);
                    String name = scanner.nextLine();
                    Course course = service.getCourseByName(name);
                    if (course != null) {
                        System.out.println(course);
                    } else {
                        logger.warning(COURSE_NOT_FOUND);
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
