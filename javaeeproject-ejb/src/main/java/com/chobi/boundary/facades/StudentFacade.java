package com.chobi.boundary.facades;

import com.chobi.business.entities.Student;
import com.chobi.business.service.CRUDService;
import com.chobi.business.util.QueryParams;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Chobii on 08/09/15.
 */

@Stateless
public class StudentFacade {

    @Inject
    CRUDService crudService;

    public List<Student> retrieveAllStudents() {
         return crudService.findByNamedQuery(
                Student.class,
                Student.FIND_ALL,
                "student.withContactInfo"
        );
    }

    public Student getOneStudent(int id) {
        return crudService.findByNamedQuery(
                Student.class,
                Student.FIND_ONE,
                Student.GRAPH_DEEP,
                QueryParams.with("id", id)
                .parameters()
        ).get(0);
    }

    public Student addStudent(Student student) {
        return crudService.create(student);
    }

    public void editStudent(Student student) {
        crudService.update(student);
    }

    public void deleteStudent(Student student) {
        student.getCourses().clear();
        editStudent(student);

        crudService.delete(Student.class, student.getId());
    }
}
