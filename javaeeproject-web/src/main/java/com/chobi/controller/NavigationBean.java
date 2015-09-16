package com.chobi.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Chobii on 16/09/15.
 */

@ManagedBean
@SessionScoped
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


}
