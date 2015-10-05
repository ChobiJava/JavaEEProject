package com.chobi.business.service;

import com.chobi.business.entities.Attendance;
import com.chobi.business.util.QueryParams;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chobii on 05/10/15.
 */
@Stateless
public class JMSService {

    @Inject
    CRUDService crudService;

    public Map<String, Object> retreiveAbscentAndPresent(String course) {
        List<Attendance> studentsForCourse = new ArrayList<>();
        studentsForCourse = crudService.findByNamedQuery(
                Attendance.class,
                Attendance.ATTENDANCE_FOR_COURSE_AND_DAY,
                Attendance.GRAPH_DEEP,
                QueryParams
                        .with("course", course)
                        .and("schoolday", LocalDate.now())
                        .parameters());
        Map<String, Object> abscentAndPresent = new HashMap<>();
        for (Attendance a : studentsForCourse) {
            abscentAndPresent.put(a.getStudent().getFirstName() + " " + a.getStudent().getLastName(), a.isPresent());
        }

        return abscentAndPresent;
    }


}
