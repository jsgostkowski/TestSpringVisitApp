package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dto.VisitDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<VisitDto> getAll(VisitType visitType) {
        if (visitType == null) {
            return visitRepository.findAll().stream()
                    .map(VisitDto::fromEntitty)
                    .toList();
        } else {
            return visitRepository.findByVisitType(visitType).stream()
                    .map(VisitDto::fromEntitty)
                    .toList();
        }
    }
    public List<VisitDto> searchByCriteria(String doctorFirstName, String patientFirstName, LocalDate startDate, LocalDate endDate) {
        return visitRepository.findByDoctorFirstNameAndPatientFirstNameAndVisitDateBetween(
                        doctorFirstName, patientFirstName, startDate, endDate)
                .stream()
                .map(VisitDto::fromEntitty)
                .toList();
    }

    public void create(Visit visit) {
        visitRepository.save(visit);
    }

}
