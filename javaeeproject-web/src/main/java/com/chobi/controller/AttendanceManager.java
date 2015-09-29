package com.chobi.controller;

import com.chobi.boundary.facades.AttendanceFacade;
import com.chobi.boundary.facades.CourseFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.entities.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Chobii on 28/09/15.
 */

@ManagedBean
@ViewScoped
public class AttendanceManager {

    @Inject
    private CourseFacade cFacade;
    @Inject
    private AttendanceFacade aFacade;
    private List<Course> myCourses;
    private Course attendanceCourse;
    private List<Student> studentsPresent;

    public void saveAttendance() {
        aFacade.saveAttendance(studentsPresent, attendanceCourse);
    }

    public List<Student> getStudentsPresent() {
        return studentsPresent;
    }

    public void setStudentsPresent(List<Student> studentsPresent) {
        this.studentsPresent = studentsPresent;
    }

    public Course getAttendanceCourse() {
        return attendanceCourse;
    }

    public void setAttendanceCourse(Course attendanceCourse) {
        this.attendanceCourse = attendanceCourse;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    private User user;

    @PostConstruct
    private void init() {
        retrieveUserForSession();
        myCourses = cFacade.getMyCourses(user);
    }

    private void retrieveUserForSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        SessionManager sm = (SessionManager) session.getAttribute("sessionManager");
        user = sm.getUser();
    }
}
