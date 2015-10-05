package com.chobi.controller;

import com.chobi.boundary.facades.AttendanceFacade;
import com.chobi.boundary.facades.CourseFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.entities.User;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private ScheduleModel schedule;
    private ScheduleEvent event;

    public ScheduleModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleModel schedule) {
        this.schedule = schedule;
    }

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

    public void selectDay(SelectEvent event) {
        System.out.println("hej");
        this.event = new DefaultScheduleEvent("", (Date) event.getObject(), (Date) event.getObject(), "red");
        schedule.addEvent(this.event);

    }

    public void deSelectDay(SelectEvent event) {
        schedule.deleteEvent((ScheduleEvent) event.getObject());
    }

    @PostConstruct
    private void init() {
        retrieveUserForSession();
        myCourses = cFacade.getMyCourses(user);
        schedule = new DefaultScheduleModel();
    }

    private void retrieveUserForSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        SessionManager sm = (SessionManager) session.getAttribute("sessionManager");
        user = sm.getUser();
    }
}
