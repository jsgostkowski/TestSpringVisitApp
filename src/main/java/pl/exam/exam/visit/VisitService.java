package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exam.exam.visit.model.dao.VisitDto;

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
}
