package pl.exam.exam.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dao.VisitDAO;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Integer> {

    @Query("SELECT new pl.exam.exam.visit.model.dao.VisitDAO(d.firstName, d.lastName, p.firstName, p.lastName, v.visitType) " +
            "FROM Visit v " +
            "JOIN v.doctor d " +
            "JOIN v.patient p")
    List<VisitDAO> findAllVisitsWithDoctorAndPatientData();

}
