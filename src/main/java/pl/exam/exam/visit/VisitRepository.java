package pl.exam.exam.visit;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.model.Patient;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dto.VisitDto;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer>, JpaSpecificationExecutor<Visit> {
    List<Visit> findAll(Specification<Visit> spec);

}
