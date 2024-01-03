package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.DoctorService;
import pl.exam.exam.patient.PatientService;
import pl.exam.exam.visit.model.Visit;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("visits", visitService.getAll());
        return "visit/list";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("visitTypes", VisitType.values());
        model.addAttribute("today", LocalDate.now());
        return "visit/create";
    }

    @PostMapping("/create")
    public String create(Visit visit) {
        visitService.create(visit);
        return "redirect:/visits";
    }

    // TODO: 03/01/2024: button z listy wizyst do strony dodawania(formularza)
    // TODO: 03/01/2024 skrypty do sortowania + profile da bazy danych. 

}
