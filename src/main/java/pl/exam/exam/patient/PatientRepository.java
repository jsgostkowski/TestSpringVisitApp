package pl.exam.exam.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.exam.exam.patient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
