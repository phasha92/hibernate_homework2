package org.example.util.composite_keys;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
public class LinkedPurchaseListKey implements Serializable {
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "course_id")
    private Integer courseId;
}
