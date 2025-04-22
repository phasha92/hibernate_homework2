package org.example.util.composite_keys;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PurchaseListKey implements Serializable {
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;
}
