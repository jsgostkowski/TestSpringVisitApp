package pl.exam.exam.visit.model;

import jakarta.persistence.*;
import lombok.*;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.model.Patient;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private VisitType visitType;

    private LocalDate visitDate;

    private int durationInMinutes;
}
