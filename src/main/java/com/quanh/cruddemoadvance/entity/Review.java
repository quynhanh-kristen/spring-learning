package com.quanh.cruddemoadvance.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    private Course course;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", course=" + course +
                '}';
    }
}
