package com.chobi.controller.students;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Student;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chobii on 25/09/15.
 */

@Named
@RequestScoped
public class StudentsBean {

    @Inject
    private StudentFacade sFacade;

    private List<Student> students;
    private StreamedContent image;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String studid = context.getExternalContext().getRequestParameterMap().get("student_id");
            Student student = students.stream()
                    .filter(s -> s.getId() == Integer.parseInt(studid))
                    .collect(Collectors.toList())
                    .get(0);
            return new DefaultStreamedContent(new ByteArrayInputStream(student.getPhoto()));
        }
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Inject
    private StudentFacade facade;

    @PostConstruct
    private void init() {
        students = facade.retrieveAllStudents();

    }

}
