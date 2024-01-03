package pl.exam.exam.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.exam.exam.visit.model.Visit;


import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Integer> {


}
