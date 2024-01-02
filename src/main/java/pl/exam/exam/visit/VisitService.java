package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dao.VisitDAO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<VisitDAO> findAllVisitsWithDoctorAndPatientData() {
        return visitRepository.findAllVisitsWithDoctorAndPatientData();
    }
    private List<Visit> findAll(){
        return visitRepository.findAll();
    }
}
