package pl.exam.exam.visit.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.model.Patient;
import pl.exam.exam.visit.model.Visit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class VisitDto {

    private Integer id;
    private LocalDateTime visitDate;
    private String formattedVisitDate;
    private int durationInMinutes;
    private Doctor doctor;
    private Patient patient;
    private String visitType;

    public static VisitDto fromEntity(Visit visit) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return VisitDto.builder()
                .id(visit.getId())
                .visitDate(visit.getVisitDate())
                .formattedVisitDate(visit.getVisitDate() != null ? visit.getVisitDate().format(dateTimeFormatter) : null)
                .durationInMinutes(visit.getDurationInMinutes())
                .patient(visit.getPatient())
                .doctor(visit.getDoctor())
                .visitType(visit.getVisitType())
                .build();
    }
}
