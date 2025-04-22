package org.example.handler;

import java.util.Scanner;

public interface HandleStrategy {
    String INPUT_ID = "Введите ID студента";
    String INPUT_COURSE = "Введите название курса";
    String INPUT_NAME = "Введите имя студента";
    String DEFAULT_CHOICE = "Нет такого варианта";
    String STUDENT_NOT_FOUND = "Студент не найден";
    String COURSE_NOT_FOUND = "Курс не найден";
    String ENTER_VALID_NUMBER = "Введите корректное число:";
    String TEACHER_NOT_FOUND = "Преподаватель не найден";
    String INPUT_COURSE_TYPE = "Введите направление курса:";

    void handle(Scanner scanner);
}
