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
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private VisitType visitType;
    // TODO: 30/01/2024 zamienic LocalDate na LocalDateTime - w insertSQL rowniez 
    private LocalDate visitDate;

    private int durationInMinutes;
}
