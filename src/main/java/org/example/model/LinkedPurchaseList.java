package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.util.composite_keys.LinkedPurchaseListKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "linked_purchase_list")
@Getter
@Setter
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseListKey id;
}
