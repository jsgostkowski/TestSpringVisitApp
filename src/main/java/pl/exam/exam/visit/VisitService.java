package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
}
