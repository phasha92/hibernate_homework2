package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.util.composite_keys.SubscriptionKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}