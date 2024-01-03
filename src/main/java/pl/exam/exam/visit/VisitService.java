package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dto.VisitDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<VisitDto> getAll() {
        return visitRepository.findAll().stream()
                .map(VisitDto::fromEntitty)
                .toList();
    }

    public void create(Visit visit) {
        visitRepository.save(visit);
    }

}
