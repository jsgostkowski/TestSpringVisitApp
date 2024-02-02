package pl.exam.exam.visit;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.exam.exam.common.VisitSortBy;
import pl.exam.exam.doctor.DoctorRepository;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.PatientRepository;
import pl.exam.exam.patient.model.Patient;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dto.VisitDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    public static Specification<Visit> filterByParams(String visitType, String doctorLastName, String patientLastName, LocalDateTime visitDate, int durationTime) {
        return new Specification<Visit>() {
            @Override
            public Predicate toPredicate(Root<Visit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicats = new ArrayList<>();
                if (visitType != null && !visitType.isEmpty()) {
                    Predicate p = criteriaBuilder.equal(root.get("visitType"), visitType);
                    predicats.add(p);
                }

                if (doctorLastName != null && !doctorLastName.isEmpty()) {
                    Predicate p = criteriaBuilder.equal(root.get("doctor").get("lastName"), doctorLastName);
                    predicats.add(p);
                }

                if (patientLastName != null && !patientLastName.isEmpty()) {
                    Predicate p = criteriaBuilder.equal(root.get("patient").get("lastName"), patientLastName);
                    predicats.add(p);
                }
                if (visitDate != null) {
                    Predicate p = criteriaBuilder.equal(root.get("visitDate"), visitDate);
                    predicats.add(p);
                }
                if (durationTime > 0) {
                    Predicate p = criteriaBuilder.equal(root.get("durationInMinutes"), durationTime);
                    predicats.add(p);
                }

                if (predicats.isEmpty()) {
                    return query.getRestriction();
                }

                return criteriaBuilder.and(predicats.toArray(new Predicate[predicats.size()]));
            }
        };
    }


    @Transactional
    public void create(VisitDto visitDto) {
        Doctor doctor = doctorRepository.findWithLockingById(visitDto.getDoctor().getId()).orElseThrow(() -> new IllegalArgumentException("COS Z DOKTOREM JEST NIE TAK"));
        Patient patient = patientRepository.findWithLockingById(visitDto.getPatient().getId()).orElseThrow(() -> new IllegalArgumentException("COS Z PACJENTEM JEST NIE TAK"));
        Visit visit = new Visit();
        visit.setVisitDate(visitDto.getDate());
        visit.setVisitType(visitDto.getVisitType());
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.setDurationInMinutes(visitDto.getDurationInMinutes());

        visitRepository.save(visit);
//        visitRepository.save(visit);
    }


    public List<VisitDto> search(String visitType, String patientLastName, String doctorLastName, LocalDateTime visitDate, int durationTime) {
        var result = visitRepository.findAll(filterByParams(visitType, doctorLastName, patientLastName, visitDate, durationTime));
        return result.stream().map(VisitDto::fromEntitty).toList();
    }
}
