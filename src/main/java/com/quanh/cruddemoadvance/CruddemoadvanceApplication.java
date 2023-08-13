package com.quanh.cruddemoadvance;

import com.quanh.cruddemoadvance.dao.AppDAO;
import com.quanh.cruddemoadvance.entity.Course;
import com.quanh.cruddemoadvance.entity.Instructor;
import com.quanh.cruddemoadvance.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoadvanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoadvanceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		appDAO.deleteCourseById(theId);
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course id: " + theId);
		Course course = appDAO.findCourseById(theId);

		System.out.println("Updating course id: " + theId);
		course.setTitle("Enjoy the Simple Things");

		appDAO.update(course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Updating instructor id: " + theId);
		instructor.setLastName("Tester");

		appDAO.update(instructor);

	}

	private void findInstructorWithCourseJoinFetch(AppDAO appDAO) {
		int theId = 1;
		Instructor temptInstructor = appDAO.findCourseByIdJoinFetch(theId);
		System.out.println("Instructor: " + temptInstructor);
		System.out.println("The associated courses" + temptInstructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		// find courses for instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		instructor.setCourses(courses);
		System.out.println("the associated courses: " + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());
	}

	private void createInstructorWithCourse (AppDAO appDAO) {
		Instructor instructor = new Instructor("Quanh", "Nguyen", "quanh@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("#", "Coding, game, reading book");
		instructor.setInstructorDetail(instructorDetail);

		Course tempCourse1 = new Course("Air guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		instructor.add(tempCourse1);
		instructor.add(tempCourse2);

		appDAO.save(instructor);
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		appDAO.deleteInstructorDetailById(theId);
	}

	private void findInstructorDetail(AppDAO appDAO){
		int theId = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);

		System.out.println("Instructor detail: " + instructorDetail);
		System.out.println(" The associated instructor: " + instructorDetail.getInstructor().getFirstName());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 3;
		Instructor temptInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + temptInstructor);
		System.out.println("The associated instructor detail only: " + temptInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Quanh", "Nguyen", "quanh@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("#", "Coding, game, reading book");
		instructor.setInstructorDetail(instructorDetail);



		Instructor instructor2 = new Instructor("Thuy", "Nguyen", "thuy@gmail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("#", "Guitar");
		// NOTE: this will also save the details object
		// because of CASCADE.ALL
		System.out.println("Saving instructor: " + instructor2.toString());
		instructor2.setInstructorDetail(instructorDetail2);
		appDAO.save(instructor);
		appDAO.save(instructor2);
	}
}
