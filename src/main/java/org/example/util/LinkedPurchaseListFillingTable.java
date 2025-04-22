package org.example.util;

import org.example.model.LinkedPurchaseList;
import org.example.model.PurchaseList;
import org.example.repository.*;

import org.example.service.CourseService;
import org.example.service.LinkedPurchaseListService;
import org.example.service.PurchaseListService;
import org.example.service.StudentService;
import org.example.util.composite_keys.LinkedPurchaseListKey;
import org.hibernate.SessionFactory;

import java.util.Set;

public class LinkedPurchaseListFillingTable {

    private static final SessionFactory sessionFactory = ConnectManager.getSessionFactory();

    private static final PurchaseListRepository purchaseListRepository = new PurchaseListRepository(sessionFactory);
    private static final PurchaseListService purchaseListService = new PurchaseListService(purchaseListRepository);

    private static final LinkedPurchaseListRepository linkedPurchaseListRepository = new LinkedPurchaseListRepository(sessionFactory);
    private static final LinkedPurchaseListService linkedPurchaseListService = new LinkedPurchaseListService(linkedPurchaseListRepository);

    private static final StudentRepository studentRepository = new StudentRepository(sessionFactory);
    private static final StudentService studentService = new StudentService(studentRepository);

    private static final CourseRepository courseRepository = new CourseRepository(sessionFactory);
    private static final CourseService courseService = new CourseService(courseRepository);

    public static void main(String[] args) {

        Set<PurchaseList> purchaseLists = purchaseListService.findAll();

        for (PurchaseList purchaseList : purchaseLists) {

            String studentName = purchaseList.getId().getStudentName();
            String courseName = purchaseList.getId().getCourseName();
            Integer StudentId = studentService.getStudentByName(studentName).getId();
            Integer CourseId = courseService.getCourseByName(courseName).getId();

            if (!linkedPurchaseListService.isExistKey(StudentId, CourseId)) {
                sessionFactory.getCurrentSession().beginTransaction();

                LinkedPurchaseListKey key = new LinkedPurchaseListKey();
                key.setStudentId(StudentId);
                key.setCourseId(CourseId);
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                linkedPurchaseList.setId(key);
                System.out.println(linkedPurchaseList);

                sessionFactory.getCurrentSession().save(linkedPurchaseList);
                sessionFactory.getCurrentSession().getTransaction().commit();
            }
        }
        sessionFactory.close();
    }
}
