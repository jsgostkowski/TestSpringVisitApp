package pl.exam.exam.visit.model;

import jakarta.persistence.*;
import lombok.*;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.model.Patient;
import java.time.LocalDateTime;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String visitType;

    private LocalDateTime visitDate;



    private int durationInMinutes;
}
