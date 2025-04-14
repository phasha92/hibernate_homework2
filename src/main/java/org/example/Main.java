package org.example;

import org.example.repository.ConnectManager;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = ConnectManager.getSessionFactory()) {
        }
    }
}