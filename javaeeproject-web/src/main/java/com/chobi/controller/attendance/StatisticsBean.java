package com.chobi.controller.attendance;

import com.chobi.boundary.facades.AttendanceFacade;
import com.chobi.boundary.facades.CourseFacade;
import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.entities.User;
import com.chobi.controller.session.SessionBean;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chobii on 05/10/15.
 */
@ManagedBean
@ViewScoped
public class StatisticsBean {

    @Inject
    private AttendanceFacade aFacade;
    @Inject
    private CourseFacade cFacade;
    @Inject
    private StudentFacade sFacade;
    private User user;
    private Student student;
    private Set<Student> myStudents;
    private List<Course> myCourses;
    private List<Integer> monthList;
    private int courseMonth;
    private int studentMonth;
    private Course course;
    private Map<String, Integer> map;
    private Map<String, Integer> map2;

    private PieChartModel chart;
    private PieChartModel chart2;

    @PostConstruct
    private void init() {
        retrieveUserForSession();
        myCourses = cFacade.getMyCourses(user);
        monthList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        myStudents = sFacade.retrieveMyStudents(user);
        chart2 = new PieChartModel();
        chart = new PieChartModel();

    }

    public PieChartModel getChart() {
        return chart;
    }

    public void setChart(PieChartModel chart) {
        this.chart = chart;
    }

    private void retrieveUserForSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        SessionBean sm = (SessionBean) session.getAttribute("sessionManager");
        user = sm.getUser();
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void populateChart() {
        if (course == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a Course and try again");
            facesContext.addMessage(null, msg);
        }
        if (course != null && courseMonth == 0) {
            System.out.println(courseMonth);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a Month and try again");
            facesContext.addMessage(null, msg);
        }
        else {
            System.out.println("im here?");
            chart = new PieChartModel();
            map = aFacade.retrieveStatisticsForCourse(course, courseMonth);

            chart.set("Present", map.get("present"));
            chart.set("Absent", map.get("absent"));
            chart.setTitle("Attendance");
            chart.setLegendPosition("w");
        }
    }

    public void populateChart2() {
        chart2 = new PieChartModel();
        map2 = aFacade.retrieveStatisticsForStudent(student, studentMonth);
        System.out.println(student == null);
        System.out.println(courseMonth);
        System.out.println(map2.get("present"));
        System.out.println("hello?");
        chart2.set("Present", map2.get("present"));
        chart2.set("Absent", map2.get("absent"));
        chart2.setTitle("Attendance");
        chart2.setLegendPosition("w");
    }

    public List<Integer> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<Integer> monthList) {
        this.monthList = monthList;
    }

    public int getCourseMonth() {
        return courseMonth;
    }

    public void setCourseMonth(int courseMonth) {
        this.courseMonth = courseMonth;
    }

    public int getStudentMonth() {
        return studentMonth;
    }

    public void setStudentMonth(int studentMonth) {
        this.studentMonth = studentMonth;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<Student> getMyStudents() {
        return myStudents;
    }

    public void setMyStudents(Set<Student> myStudents) {
        this.myStudents = myStudents;
    }

    public PieChartModel getChart2() {
        return chart2;
    }

    public void setChart2(PieChartModel chart2) {
        this.chart2 = chart2;
    }
}
