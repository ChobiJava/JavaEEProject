package com.chobi.boundary.facades;

import com.chobi.business.entities.Attendance;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.service.CRUDService;
import com.chobi.business.util.QueryParams;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chobii on 26/09/15.
 */

@Stateless
public class AttendanceFacade {

    @Inject
    CRUDService crudService;

    public List<Attendance> attendanceForCourseAndDay(Course course, LocalDate schoolDay) {
        return crudService.findByNamedQuery(
                Attendance.class,
                Attendance.ATTENDANCE_FOR_COURSE_AND_DAY,
                Attendance.GRAPH_DEEP,
                QueryParams.with("course", course.getCourseName())
                           .and("schoolday", schoolDay)
                           .parameters());
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
