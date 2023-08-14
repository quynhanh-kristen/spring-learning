package com.quanh.cruddemoadvance.dao;

import com.quanh.cruddemoadvance.entity.Course;
import com.quanh.cruddemoadvance.entity.Instructor;
import com.quanh.cruddemoadvance.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the instructor
        Instructor temptInstructor = entityManager.find(Instructor.class, theId);

        List<Course> courses = temptInstructor.getCourses();

        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null); // chỗ này ko rõ làm sao nó update đc data dưới DB
        }
        // delete the instructor
        entityManager.remove(temptInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail temptInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        // delete the instructor
        System.out.println(temptInstructorDetail);
        // remove the associated object reference
        // break bi-directional link
        temptInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(temptInstructorDetail);
        // also delete associated instructor
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findCourseByIdJoinFetch(int theId) {
        // Create query
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        //execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = entityManager.find(Course.class, theId);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewByCourseId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id = :data", Course.class);
        query.setParameter("data", theId);
        Course course = query.getSingleResult();

        return course;
    }


}
