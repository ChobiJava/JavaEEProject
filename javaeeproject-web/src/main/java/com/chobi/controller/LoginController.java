package com.chobi.controller;

import com.chobi.service.UserRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 * Created by Chobii on 08/09/15.
 */

@ManagedBean
@SessionScoped
public class LoginController {


    private String userName;
    private String password;

    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Inject
    private FacesContext fContext;

    @Inject
    private UserRepository uRep;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navBean;

    public void setNavBean(NavigationBean navBean) {
        this.navBean = navBean;
    }

    public String login() {
        if (uRep.findUser(userName, password)) {
            loggedIn = true;
            return navBean.redirectToAdmin();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "No such user");
            fContext.addMessage(null, msg);
            return "fail";
        }
    }

    public String logout() {
        loggedIn = false;
        HttpSession httpSession = (HttpSession) fContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        return navBean.redirectToLogin();

    }

//    public String isAdmin() {
//        return admin ? "/resources/templates/admin.xhtml" : "/resources/templates/user.xhtml";
//    }
}
