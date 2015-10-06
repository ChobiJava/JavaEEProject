package com.chobi.controller;

import com.chobi.boundary.facades.AttendanceFacade;
import com.chobi.boundary.facades.CourseFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.User;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Chobii on 05/10/15.
 */
@ManagedBean
@ViewScoped
public class StatisticsManager {

    @Inject
    private AttendanceFacade aFacade;
    @Inject
    private CourseFacade cFacade;
    private User user;
    private List<Course> myCourses;
    private List<Integer> monthList;
    private int month;
    private Course course;
    private Map<String, Integer> map;

    private PieChartModel chart;

    @PostConstruct
    private void init() {
        retrieveUserForSession();
        myCourses = cFacade.getMyCourses(user);
        monthList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    }

    public PieChartModel getChart() {
        return chart;
    }

    public void setChart(PieChartModel chart) {
        this.chart = chart;
    }

    private void retrieveUserForSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        SessionManager sm = (SessionManager) session.getAttribute("sessionManager");
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
        System.out.println(course.getCourseName());
        chart = new PieChartModel();
        map = aFacade.retreiveStatisticsForCourse(course, month);
        chart.set("Present", map.get("present"));
        chart.set("Absent", map.get("absent"));
        chart.setTitle("Attendance");
        chart.setLegendPosition("w");
    }

    public List<Integer> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<Integer> monthList) {
        this.monthList = monthList;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
