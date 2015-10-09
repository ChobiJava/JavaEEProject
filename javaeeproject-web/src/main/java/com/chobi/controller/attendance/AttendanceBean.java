package com.chobi.controller.attendance;

import com.chobi.boundary.facades.AttendanceFacade;
import com.chobi.boundary.facades.CourseFacade;
import com.chobi.business.entities.*;
import com.chobi.controller.session.SessionBean;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chobii on 28/09/15.
 */

@ManagedBean
@ViewScoped
public class AttendanceBean {

    @Inject
    private CourseFacade cFacade;
    @Inject
    private AttendanceFacade aFacade;
    private List<Course> myCourses;
    private Course attendanceCourse;
    private List<Student> studentsPresent;
    private List<RedDay> redDays;
    private ScheduleModel schedule;
    private ScheduleEvent event;
    private boolean redDay;
    private Date date;
    private List<Date> dates;
    private List<Student> present;
    private List<Student> absent;

    public ScheduleModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleModel schedule) {
        this.schedule = schedule;
    }

    public String saveAttendance() {
        aFacade.saveAttendance(studentsPresent, attendanceCourse);
        return "school/admin.xhtml";
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

    public void redDayCheck(AjaxBehaviorEvent event) {
        if (attendanceCourse != null) {
            redDay = aFacade.checkRedDayForCourse(attendanceCourse);
            System.out.println(redDay);
        }
    }

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
        SessionBean sm = (SessionBean) session.getAttribute("sessionManager");
        user = sm.getUser();
    }

    public void recieveRedDays(AjaxBehaviorEvent event) {
        if (attendanceCourse != null) {
            schedule.clear();
            redDays = aFacade.findAllRedDayForCourse(attendanceCourse);
            if (!redDays.isEmpty()) {
                for (RedDay r : redDays) {
                    Date date = Date.from(r.getRedDay().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    DefaultScheduleEvent e = new DefaultScheduleEvent("", date, date, "red");
                    schedule.addEvent(e);
                }
            } else {
                redDays = new ArrayList<>();
            }
        }
    }

    public void saveRedDays() {
        for (ScheduleEvent e : schedule.getEvents()) {
            LocalDate d = Instant.ofEpochMilli(e.getStartDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            RedDay r = null;
            try {
                r = redDays.stream().filter(re -> re.getRedDay().equals(d)).collect(Collectors.toList()).get(0);
            } catch (Exception ex) { }
            if (r == null) {
                r = new RedDay(d, attendanceCourse);
                aFacade.saveRedDays(r);
            }
        }
    }

    public void checkDays(AjaxBehaviorEvent event) {
        dates = aFacade.recieveDates(attendanceCourse);
    }

    public void setPresentAndAbsent(AjaxBehaviorEvent event) {
        present = new ArrayList<>();
        absent = new ArrayList<>();
        LocalDate date2 = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        List<Attendance> attendaces = aFacade.attendanceForCourseAndDay(attendanceCourse, date2);
        for (Attendance a : attendaces) {
            if (a.isPresent()) {
                present.add(a.getStudent());
            } else {
                absent.add(a.getStudent());
            }
        }
    }

    public boolean isRedDay() {
        return redDay;
    }

    public void setRedDay(boolean redDay) {
        this.redDay = redDay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public List<Student> getPresent() {
        return present;
    }

    public void setPresent(List<Student> present) {
        this.present = present;
    }

    public List<Student> getAbsent() {
        return absent;
    }

    public void setAbsent(List<Student> absent) {
        this.absent = absent;
    }
}
