package pl.exam.exam.visit.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.model.Patient;
import pl.exam.exam.visit.model.Visit;

import java.time.LocalDate;

@Getter
@Builder
public class VisitDto {

    private int id;
    private LocalDate date;
    private int durationInMinutes;
    private Doctor doctor;
    private Patient patient;
    private VisitType visitType;

    public static VisitDto fromEntitty(Visit visit) {
        return VisitDto.builder()
                .id(visit.getId())
                .date(visit.getVisitDate())
                .durationInMinutes(visit.getDurationInMinutes())
                .patient(visit.getPatient())
                .doctor(visit.getDoctor())
                .visitType(visit.getVisitType())
                .date(visit.getVisitDate())
                .build();
    }
}
