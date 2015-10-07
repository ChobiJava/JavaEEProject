package com.chobi.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Chobii on 16/09/15.
 */

@Named
@ApplicationScoped
public class NavigationBean implements Serializable {

    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "/login.xhtml";
    }

    public String redirectToAdmin() {
        return "/app/admin.xhtml?faces-redirect=true";
    }

    public String toAdmin() {
        return "/app/admin.xhtml";
    }

    public String redirectToStudentView() {
        return "/app/studentView.xhtml?faces-redirect=true";
    }

    public String redirectToCourses() {
        return "/app/courses.xhtml?faces-redirect=true";
    }

    public String redirectToMyCourses() {
        return "/app/my-courses.xhtml?faces-redirect=true";
    }

    public String redirectToSchoolDays() {
        return "/app/school-days.xhtml?faces-redirect=true";
    }


}
