package com.chobi.controller;

import com.chobi.business.entities.User;
import com.chobi.business.entities.UserType;
import com.chobi.service.UserRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by Chobii on 08/09/15.
 */

@ManagedBean(name = "sessionManager")
@SessionScoped
public class SessionManager implements Serializable {

    private boolean loggedIn;
    private boolean principal;
    private User user;
    private String userName;
    private String password;

    @Inject
    private UserRepository uRep;

    @Inject
    private NavigationBean navBean;

    public String login() {
        if ((user = uRep.findUser(userName, password)) != null) {
            loggedIn = true;
            checkifprincipal();
            System.out.println(user.getUserName());
            return "/app/admin.xhtml";
        } else {
            FacesContext fContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "No such user");
            fContext.addMessage(null, msg);
            return "fail";
        }
    }

    private void checkifprincipal() {
        principal = user.getUserType().equals(UserType.PRINCIPAL);
    }

    public String logout() {
        loggedIn = false;
        FacesContext fContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) fContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        return navBean.redirectToLogin();
    }

    public User getUser() {
        return user;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public boolean isLoggedIn() {
        return loggedIn;
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
}
