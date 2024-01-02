package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dao.VisitDAO;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    @GetMapping
    public String getAll(Model model) {
        List<VisitDAO> visits = visitService.findAllVisitsWithDoctorAndPatientData();
        model.addAttribute("visits", visits);
        return "visit/list";
    }


}
