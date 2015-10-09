package com.chobi.util;

import com.chobi.boundary.facades.CourseFacade;
import com.chobi.boundary.facades.StudentFacade;
import com.chobi.boundary.facades.TeacherFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.entities.Teacher;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Chobii on 25/09/15.
 */
@Named
@RequestScoped
public class EntityConverter implements Converter {

    private static final String EDIT_STUDENT = "/school/students/student.xhtml";
    private static final String EDIT_TEACHER = "/app/edit-teacher.xhtml";
    private static final String EDIT_COURSE  = "/school/courses/course.xhtml";
    private static final String ATTENDANCE = "/app/attendance.xhtml";
    private static final String ADD_COURSE = "/school/courses/addcourse.xhtml";
    private static final String SCHOOL_DAY = "/school/management/schooldays.xhtml";

    @Inject
    private StudentFacade studentFacade;
    @Inject
    private TeacherFacade teacherFacade;
    @Inject
    private CourseFacade courseFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String view = context.getViewRoot().getViewId();
        System.out.println(view);
        if (value == null || value.isEmpty()) {

            return null;
        } else if (view.equals(EDIT_STUDENT)) {
            try {
                int id = Integer.valueOf(value);
                return studentFacade.getOneStudent(id);
            } catch (NumberFormatException e) {
                throw new ConverterException("Not a valid Student: " + value, e);
            }

        } else if (view.equals(EDIT_TEACHER)) {
            try {
                int id = Integer.valueOf(value);
                return teacherFacade.getOneTeacher(id);
            } catch (NumberFormatException e) {
                throw new ConverterException("Not a valid Teacher: " + value, e);
            }
        } else if (view.equals(EDIT_COURSE)) {
            try {
                int id = Integer.valueOf(value);
                return courseFacade.getOneCourseWithStudents(id);
            } catch (NumberFormatException e) {
                throw new ConverterException("Not a valid Course: " + value, e);
            }
        } else if(view.equals(ATTENDANCE)) {
            try {
                int id = Integer.valueOf(value);
                return courseFacade.getOneCourse(id);
            } catch (NumberFormatException e) {
                throw new ConverterException("Not a valid Course: " + value, e);
            }
        }else if (view.equals(ADD_COURSE)) {
            try {
                int id = Integer.valueOf(value);
                return studentFacade.getOneStudent(id);
            } catch (NumberFormatException e) {
                throw new ConverterException("Not a valid Student: " + value, e);
            }
        } else if (view.equals(SCHOOL_DAY)) {
            try {
                int id = Integer.valueOf(value);
                return courseFacade.getOneCourse(id);
            } catch (NumberFormatException e) {
                throw new ConverterException("Not a valid Student: " + value, e);
            }
        } else {
            throw new ConverterException("How did you get here?");
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Student) {
            int id = ((Student) value).getId();
            return (id > 0) ? String.valueOf(id) : null;
        } else if (value instanceof Teacher) {
            int id = ((Teacher) value).getId();
            return (id > 0) ? String.valueOf(id) : null;
        } else if (value instanceof Course) {
            int id = ((Course) value).getId();
            return (id > 0) ? String.valueOf(id) : null;
        } else {
            throw new ConverterException("Can't find the requested page: " + value);
        }
    }
}
