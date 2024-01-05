package pl.exam.exam.visit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.exam.exam.common.VisitSortBy;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dto.VisitDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<VisitDto> findVisitByType(VisitType visitType) {

        return visitRepository.findByVisitType(visitType).stream()
                .map(VisitDto::fromEntitty)
                .toList();
    }

    public List<VisitDto> findByVisitDate(LocalDate startDate, LocalDate endDate) {
        return visitRepository.findByVisitDateBetween(startDate, endDate).stream().map(VisitDto::fromEntitty).toList();
    }

    public List<VisitDto> findByDoctorLastName(String doctorLastName) {
        return visitRepository.findVisitByDoctorLastNameIgnoreCase(doctorLastName).stream().map(VisitDto::fromEntitty).toList();
    }

    public List<VisitDto> findByPatientLastName(String patientLastName) {
        return visitRepository.findVisitByPatientLastNameIgnoreCase(patientLastName).stream().map(VisitDto::fromEntitty).toList();
    }

    public List<VisitDto> findByDoctorLastNameAndPatientLastName(String doctorLastName, String patientLastName) {
        return visitRepository.findVisitByDoctorLastNameIgnoreCaseAndPatientLastNameIgnoreCase(doctorLastName, patientLastName).stream().map(VisitDto::fromEntitty).toList();
    }

    public List<VisitDto> getAll() {
        return visitRepository.findAll().stream().map(VisitDto::fromEntitty).toList();
    }

    public List<VisitDto> searchByCriteria(String doctorLastName, String patientLastName, LocalDate startDate, LocalDate endDate) {
        return visitRepository.findByDoctorLastNameIgnoreCaseAndPatientLastNameIgnoreCaseAndVisitDateBetween(
                        doctorLastName, patientLastName, startDate, endDate)
                .stream()
                .map(VisitDto::fromEntitty)
                .toList();
    }

    @Transactional
    public void create(Visit visit) {
        visitRepository.save(visit);
    }

    public List<VisitDto> search(VisitType visitType, String patientLastName, String doctorLastName,
                                 LocalDate startDate, LocalDate endDate, VisitSortBy sortBy) {
        List<VisitDto> visits;

        if (notEmpty(doctorLastName, patientLastName, startDate, endDate)) {
            visits = searchByCriteria(doctorLastName, patientLastName, startDate, endDate);
        } else if (notEmpty(visitType)) {
            visits = findVisitByType(visitType);
        } else if (notEmpty(doctorLastName, patientLastName)) {
            visits = findByDoctorLastNameAndPatientLastName(doctorLastName, patientLastName);
        } else if (notEmpty(patientLastName)) {
            visits = findByPatientLastName(patientLastName);
        } else if (notEmpty(doctorLastName)) {
            visits = findByDoctorLastName(doctorLastName);
        } else if (notEmpty(startDate, endDate)) {
            visits = findByVisitDate(startDate, endDate);
        } else {
            visits = getAll();
        }

        if (sortBy != null) {
            if (sortBy == VisitSortBy.DATE) {
                visits = visits.stream().sorted(Comparator.comparing(VisitDto::getDate)).toList();
            } else if (sortBy == VisitSortBy.DOCTOR) {
                visits = visits.stream().sorted(Comparator.comparing(x -> x.getDoctor().getLastName())).toList();
            } else if (sortBy == VisitSortBy.PATIENT) {
                visits = visits.stream().sorted(Comparator.comparing(x -> x.getPatient().getLastName())).toList();
            } else if (sortBy == VisitSortBy.DURATION) {
                visits = visits.stream().sorted(Comparator.comparing(VisitDto::getDurationInMinutes)).toList();
            }
        }

        return visits;
    }

    private boolean notEmpty(Object... values) {
        for (Object value : values) {
            if (value == null) {
                return false;
            }
            if (value instanceof String && ((String) value).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
