package com.chobi.controller.courses;

import com.chobi.boundary.facades.AttendanceFacade;
import com.chobi.boundary.facades.CourseFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.entities.User;
import com.chobi.controller.session.SessionBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Chobii on 24/09/15.
 */
@ManagedBean
@ViewScoped
public class CoursesBean {

    @Inject
    private CourseFacade producer;
    @Inject
    private AttendanceFacade aFacade;
    private User user;

    private List<String> strings;

    private Course course;
    private List<Student> students;
    private List<Course> courses;
    private List<Course> myCourses;

    @PostConstruct
    private void init() {
        retrieveUserForSession();
        myCourses = producer.getMyCourses(user);
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public List<Course> all() {
        return producer.getAllCourses();
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }


    public Course getACourse() {
        return producer.getAllCourses().get(0);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void handleStudents() {
//        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        int id = Integer.parseInt(params.get("class"));
//        System.out.println(id);
        students.forEach(System.out::println);
        producer.saveAttendance(students, course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    private void retrieveUserForSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        SessionBean sm = (SessionBean) session.getAttribute("sessionManager");
        user = sm.getUser();
    }


}
