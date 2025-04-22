package org.example.handler;

import org.example.service.PurchaseListService;

import java.util.Scanner;

public class PurchaseListHandler extends Handler {

    private final PurchaseListService service;

    public PurchaseListHandler(PurchaseListService service) {
        this.service = service;
    }

    @Override
    public void handle(Scanner scanner) {
        service.findAll().forEach(System.out::println);
    }
}
