package com.chobi.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by Chobii on 08/09/15.
 */

@ManagedBean
@SessionScoped
public class LoginController {

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean admin = false;

    @Inject
    private FacesContext fContext;

    public String login() {
        if (user.equals("kent") && password.equals("kent")) {
            admin = true;
            return "success";
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "No such user");
        fContext.addMessage(null, msg);
        return "fail";
    }

    public String isAdmin() {
        return admin ? "/resources/templates/admin.xhtml" : "/resources/templates/user.xhtml";
    }
}
