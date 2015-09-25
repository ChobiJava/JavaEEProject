package com.chobi.controller;

import com.chobi.business.entities.User;
import com.chobi.service.UserRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by Chobii on 08/09/15.
 */

@Named(value = "sessionManager")
@SessionScoped
public class SessionManager implements Serializable {

    private String userName;
    private String password;
    private boolean loggedIn;
    private boolean principal = true;
    private User user;

    public User getUser() {
        return user;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

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
    private UserRepository uRep;

    @Inject
    private NavigationBean navBean;

    public String login() {
        if ((user = uRep.findUser(userName, password)) != null) {
            loggedIn = true;
            return navBean.redirectToAdmin();
        } else {
            FacesContext fContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "No such user");
            fContext.addMessage(null, msg);
            return "fail";
        }
    }

    public String logout() {
        loggedIn = false;
        FacesContext fContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) fContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        return navBean.redirectToLogin();

    }

//    public String isAdmin() {
//        return admin ? "/resources/templates/admin.xhtml" : "/resources/templates/user.xhtml";
//    }
}
