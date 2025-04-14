package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.util.composite_keys.PurchaseListKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "purchase_list")
@Setter
@Getter
public class PurchaseList {
    @EmbeddedId
    private PurchaseListKey id;
    private Integer price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
