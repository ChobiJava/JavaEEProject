package com.chobi.boundary.facades;

import com.chobi.business.entities.Attendance;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.RedDay;
import com.chobi.business.entities.Student;
import com.chobi.business.service.CRUDService;
import com.chobi.business.util.QueryParams;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Integer> retrieveStatisticsForCourse(Course course, int month) {
        int present = 0;
        int absent = 0;
        Map<String, Integer> stats = new HashMap<>();
        List<Attendance> attendanceForMonth = crudService.findByNamedQuery(
                Attendance.class,
                Attendance.ATTENDANCE_FOR_COURSE_AND_MONTH,
                Attendance.GRAPH_DEEP,
                QueryParams
                        .with("course", course.getCourseName())
                        .and("schoolday", month)
                        .parameters()
        );

        for (Attendance a : attendanceForMonth) {
            if (a.isPresent()) {
                present++;
            } else absent++;
        }
        stats.put("present", present);
        stats.put("absent", absent);

        return stats;
    }

    public Map<String, Integer> retrieveStatisticsForStudent(Student student, int month) {
        int present = 0;
        int absent = 0;
        Map<String, Integer> stats = new HashMap<>();
        List<Attendance> attendanceForMonth = crudService.findByNamedQuery(
                Attendance.class,
                Attendance.ATTENDANCE_FOR_STUDENT_AND_MONTH,
                Attendance.GRAPH_DEEP,
                QueryParams
                        .with("student", student.getId())
                        .and("schoolday", month)
                        .parameters()
        );

        System.out.println(attendanceForMonth.isEmpty());

        for (Attendance a : attendanceForMonth) {
            if (a.isPresent()) {
                present++;
            } else absent++;
        }

        System.out.println(present + " + " + absent);
        stats.put("present", present);
        stats.put("absent", absent);

        return stats;
    }

    public boolean checkRedDayForCourse(Course course) {
        LocalDate today = LocalDate.now();
        RedDay r = new RedDay();
        try {
            r = crudService.findByNamedQuery(
                    RedDay.class,
                    RedDay.FIND_REDDAY_FOR_COURSE_AND_DATE,
                    RedDay.GRAPH_DEEP,
                    QueryParams
                            .with("course", course)
                            .and("redday", today)
                            .parameters()
            ).get(0);
        } catch (Exception e) {

        }

        return r.getId() != 0;
    }

    public List<RedDay> findAllRedDayForCourse(Course course) {
        List<RedDay> redDays = new ArrayList<>();

        redDays = crudService.findByNamedQuery(
                RedDay.class,
                RedDay.FIND_REDDAYS_FOR_COURSE,
                RedDay.GRAPH_DEEP,
                QueryParams
                        .with("course", course)
                        .parameters()
        );

        return redDays;
    }

    public void saveRedDays(RedDay redDay) {
        crudService.create(redDay);
    }
}
