package com.chobi.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Created by Chobii on 08/09/15.
 */
public class WebResources {

    @Produces
    @RequestScoped
    public FacesContext fContext() {
        return FacesContext.getCurrentInstance();
    }
}
