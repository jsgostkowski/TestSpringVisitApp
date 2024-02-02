package pl.exam.exam.visit.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class VisitCriteria {
    private String doctorLastName;
    private String visitType;
    private String patientLastName;
    private LocalDateTime visitDate;
    private int durationTime;


}
