package org.example.service;

import org.example.model.PurchaseList;
import org.example.repository.PurchaseListRepository;

import java.util.Set;

public class PurchaseListService {

    private final PurchaseListRepository purchaseListRepository;

    public PurchaseListService(PurchaseListRepository purchaseListRepository) {
        this.purchaseListRepository = purchaseListRepository;
    }

    public Set<PurchaseList> findAll() {
        return purchaseListRepository.findAll();
    }
}
