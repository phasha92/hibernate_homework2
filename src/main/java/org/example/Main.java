package org.example;

import org.example.model.Course;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repository.ConnectManager;
import org.example.repository.CourseRepository;
import org.example.repository.StudentRepository;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.example.util.types.CourseType;
import org.hibernate.SessionFactory;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String INPUT_ID = "Введите ID студента";
    private static final String INPUT_COURSE = "Введите название курса";
    private static final String INPUT_NAME = "Введите имя студента";
    private static final String DEFAULT_CHOICE = "Нет такого варианта";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             SessionFactory sessionFactory = ConnectManager.getSessionFactory()) {

            StudentRepository studentRepository = new StudentRepository(sessionFactory);
            CourseRepository courseRepository = new CourseRepository(sessionFactory);

            StudentService studentService = new StudentService(studentRepository);
            CourseService courseService = new CourseService(courseRepository);

            while (true) {
                showMainMenu();
                String input = scanner.nextLine();

                switch (input) {
                    case "1" -> handleStudents(scanner, studentService);
                    case "2" -> handleCourses(scanner, courseService);
                    case "exit" -> {
                        return;
                    }
                    default -> logger.warning(DEFAULT_CHOICE);
                }
            }
        }

    }

    private static void showMainMenu() {
        logger.info("""
                С чем будем работать?
                1 - студенты
                2 - курсы
                exit - выйти
                """);
    }

    private static void handleStudents(Scanner scanner, StudentService service) {

        while (true) {
            logger.info("""
                    Сделайте выбор:
                    1 - вывести всех студентов
                    2 - найти студента по ID
                    3 - найти все курсы студента
                    4 - найти студентов по имени
                    exit - выйти
                    """);

            switch (scanner.nextLine()) {
                case "1" -> service.findAll().forEach(s -> logger.info(s.toString()));
                case "2" -> {
                    logger.info(INPUT_ID);
                    Integer id = readInt(scanner);
                    Student student = service.findById(id);
                    logger.info(student != null ? student.toString() : "Студент не найден");
                }
                case "3" -> {
                    logger.info(INPUT_ID);
                    Integer id = readInt(scanner);
                    service.findAllCoursesByStudent(id).forEach(c -> logger.info(c.toString()));
                }
                case "4" -> {
                    logger.info(INPUT_NAME);
                    String name = scanner.nextLine();
                    service.findStudentsByName(name).forEach(s -> logger.info(s.toString()));
                }
                case "exit" -> {
                    return;
                }
                default -> logger.warning(DEFAULT_CHOICE);
            }
        }
    }

    private static void handleCourses(Scanner scanner, CourseService service) {

        while (true) {
            logger.info("""
                    Сделайте выбор:
                    1 - вывести все курсы
                    2 - найти курс по названию
                    3 - найти преподавателя по курсу
                    4 - найти курсы по направлению
                    exit - выйти
                    """);

            switch (scanner.nextLine()) {
                case "1" -> service.findAll().forEach(c -> logger.info(c.toString()));
                case "2" -> {
                    logger.info(INPUT_COURSE);
                    String name = scanner.nextLine();
                    service.findCourseByName(name).forEach(c -> logger.info(c.toString()));
                }
                case "3" -> {
                    logger.info(INPUT_COURSE);
                    Integer id = readInt(scanner);
                    Teacher teacher = service.getTeacherById(id);
                    logger.info(teacher != null ? teacher.toString() : "Преподаватель не найден");
                }
                case "4" -> {
                    logger.info("Введите направление курса:");
                    for (CourseType type : CourseType.values()) {
                        logger.info(type.name());
                    }
                    String type = scanner.nextLine();
                    Set<Course> courses = service.findCoursesByType(type);
                    if (courses.isEmpty()) {
                        logger.info("Курс не найден");
                    } else {
                        courses.forEach(c -> logger.info(c.toString()));
                    }
                }
                case "exit" -> {
                    return;
                }
                default -> logger.warning(DEFAULT_CHOICE);
            }
        }

    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            logger.warning("Введите корректное число:");
            scanner.next();
        }
        return scanner.nextInt();
    }
}