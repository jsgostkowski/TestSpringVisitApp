package pl.exam.exam.doctor.model;

import jakarta.persistence.*;
import lombok.*;
import pl.exam.exam.common.Specialization;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Version
    private Integer version;
}
