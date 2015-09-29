package com.chobi.util;

import com.chobi.boundary.facades.CourseFacade;
import com.chobi.business.entities.Course;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

/**
 * Created by Chobii on 28/09/15.
 */
@ManagedBean
@RequestScoped
public class CourseConverter implements Converter {

    @Inject
    private CourseFacade userService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return userService.getOneCourse(Integer.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", submittedValue)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Course) {
            return String.valueOf(((Course) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage("Error"));
        }
    }
}
