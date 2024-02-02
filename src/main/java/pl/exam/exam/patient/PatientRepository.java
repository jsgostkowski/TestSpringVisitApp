package pl.exam.exam.patient;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import pl.exam.exam.patient.model.Patient;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Patient> findWithLockingById(int id);
}
