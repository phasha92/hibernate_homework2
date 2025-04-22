package org.example.view;

public class Menu {


    public static void showMainMenu() {
        System.out.println("""
                С чем будем работать?
                1 - студенты
                2 - курсы
                3 - преподаватели
                4 - посмотреть список покупок
                5 - посмотреть связный список покупок
                exit - выйти
                """);
    }

    public static void showStudentsMenu() {
        System.out.println("""
                Сделайте выбор:
                1 - вывести всех студентов
                2 - найти студента по ID
                3 - найти все курсы студента
                4 - найти студентов по имени
                5 - найти студента по имени
                exit - выйти
                """);
    }

    public static void showCoursesMenu() {
        System.out.println("""
                Сделайте выбор:
                1 - вывести все курсы
                2 - найти курсы по названию
                3 - найти преподавателя по курсу
                4 - найти курсы по направлению
                5 - найти курс по названию
                exit - выйти
                """);
    }
}
