package org.example.handler;

import org.example.service.LinkedPurchaseListService;

import java.util.Scanner;

public class LinkedPurchaseListHandler extends Handler {

    private final LinkedPurchaseListService service;

    public LinkedPurchaseListHandler(LinkedPurchaseListService service) {
        this.service = service;
    }

    @Override
    public void handle(Scanner scanner) {
        service.findAll().forEach(System.out::println);
    }
}
