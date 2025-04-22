package org.example.service;

import org.example.model.LinkedPurchaseList;
import org.example.repository.LinkedPurchaseListRepository;

import java.util.Set;

public class LinkedPurchaseListService {

    private final LinkedPurchaseListRepository linkedPurchaseListRepository;

    public LinkedPurchaseListService(LinkedPurchaseListRepository linkedPurchaseListRepository) {
        this.linkedPurchaseListRepository = linkedPurchaseListRepository;
    }

    public boolean isExistKey(Integer studentId, Integer courseId) {
        return linkedPurchaseListRepository.isExistKey(studentId, courseId);
    }

    public Set<LinkedPurchaseList> findAll() {
        return linkedPurchaseListRepository.findAll();
    }
}
