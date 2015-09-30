package com.chobi.boundary.facades;

import com.chobi.business.entities.Attendance;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.entities.User;
import com.chobi.business.service.CRUDService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chobii on 24/09/15.
 */
@Stateless
public class CourseFacade {

    @Inject
    private CRUDService crudService;

    public List<Course> getMyCourses(User user) {
        List<Course> all = crudService.findByNamedQuery(
                Course.class,
                Course.FIND_ALL,
                Course.GRAPH_DEEP
        );

        return all.stream()
                  .filter(c -> c.getTeacher().getUser().getId() == user.getId())
                  .collect(Collectors.toList());

    }

    public List<Course> getAllCourses() {
        return crudService.findByNamedQuery(
                Course.class,
                Course.FIND_ALL,
                Course.GRAPH_DEEP
        );
    }

    public Course addCourse(Course course) {
        return null;
    }

    public Course editCourse(Course course) {
        return null;
    }

    public void deleteCourse(Course course) {

    }

    public Course getOneCourse(int id) {
        return crudService.find(Course.class, id);
    }

    public void saveAttendance(List<Student> students, Course course) {
        List<Integer> studentids = new ArrayList<>();
        students.forEach(c -> studentids.add(c.getId()));
        List<Student> studentsForClass = course.getStudents();
        List<Attendance> attendanceForClass = new ArrayList<>();
        for (Student student : studentsForClass) {
            if (studentids.contains(student.getId())) {
                Attendance a = new Attendance(student, course, true);
                attendanceForClass.add(a);
            } else {
                Attendance a = new Attendance(student, course, false);
                attendanceForClass.add(a);
            }

        }
        attendanceForClass.forEach(crudService::create);
    }
}
