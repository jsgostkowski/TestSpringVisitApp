package pl.exam.exam.visit.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.exam.exam.common.VisitType;

@Getter
@AllArgsConstructor
public class VisitDAO {

    private final String doctorFirstName;
    private final String doctorLastName;
    private final String patientFirstName;
    private final String patientLastName;
    private final VisitType visitType;
}
