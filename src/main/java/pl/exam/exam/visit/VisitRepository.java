package pl.exam.exam.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.model.Patient;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dto.VisitDto;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {
    List<Visit> findByVisitType(VisitType visitType);

    List<Visit> findByVisitDate(LocalDate startDate);
    //
    //    List<Patient> findByPatientLastName(String patientLastName);
    //
    //    List<Patient> findByPatientFirstName(String patientFirstName);
    //
    //    List<Doctor> fintByDoctorFirstName(String doctorFirstName);
    //
    //    List<Doctor> findByDoctorLastName(String doctorLastName);

    List<Visit> findByDoctorFirstNameAndPatientFirstNameAndVisitDateBetween(
            String doctorFirstName, String patientFirstName, LocalDate startDate, LocalDate endDate);

}
