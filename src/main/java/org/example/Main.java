package org.example;

import org.example.handler.*;
import org.example.repository.*;
import org.example.service.CourseService;
import org.example.service.LinkedPurchaseListService;
import org.example.service.PurchaseListService;
import org.example.service.StudentService;
import org.example.view.Menu;
import org.hibernate.SessionFactory;

import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.handler.HandleStrategy.DEFAULT_CHOICE;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             SessionFactory sessionFactory = ConnectManager.getSessionFactory()) {

            StudentRepository studentRepository = new StudentRepository(sessionFactory);
            CourseRepository courseRepository = new CourseRepository(sessionFactory);
            PurchaseListRepository purchaseListRepository = new PurchaseListRepository(sessionFactory);
            LinkedPurchaseListRepository linkedPurchaseListRepository = new LinkedPurchaseListRepository(sessionFactory);

            StudentService studentService = new StudentService(studentRepository);
            CourseService courseService = new CourseService(courseRepository);
            PurchaseListService purchaseListService = new PurchaseListService(purchaseListRepository);
            LinkedPurchaseListService linkedPurchaseListService = new LinkedPurchaseListService(linkedPurchaseListRepository);

            HandlerRegistry handlerRegistry = new HandlerRegistry();
            handlerRegistry.register("1", new StudentHandler(studentService));
            handlerRegistry.register("2", new CourseHandler(courseService));
            handlerRegistry.register("4", new PurchaseListHandler(purchaseListService));
            handlerRegistry.register("5", new LinkedPurchaseListHandler(linkedPurchaseListService));
            handlerRegistry.register("exit", new ExitHandler());

            while (true) {
                Menu.showMainMenu();
                String input = scanner.nextLine();
                try {
                    handlerRegistry.getHandler(input).handle(scanner);
                } catch (NullPointerException e) {
                    logger.warning(DEFAULT_CHOICE);
                }
            }
        }
    }
}