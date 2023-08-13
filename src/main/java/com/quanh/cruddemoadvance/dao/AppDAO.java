package com.quanh.cruddemoadvance.dao;

import com.quanh.cruddemoadvance.entity.Course;
import com.quanh.cruddemoadvance.entity.Instructor;
import com.quanh.cruddemoadvance.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findCourseByIdJoinFetch(int theId);

    void update(Instructor tempInstructor);

    void update(Course course);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

}
