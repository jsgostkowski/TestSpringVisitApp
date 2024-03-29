package pl.exam.exam.visit;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.exam.exam.common.VisitSortBy;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.DoctorService;
import pl.exam.exam.doctor.model.Doctor;
import pl.exam.exam.patient.PatientService;
import pl.exam.exam.patient.model.Patient;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.VisitCriteria;
import pl.exam.exam.visit.model.dto.VisitDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;


    @GetMapping
    public String getAll(VisitCriteria visitCriteria, @RequestParam(name = "sort", defaultValue = "asc") String sort, Model model) {

        List<VisitDto> visits = visitService.search(visitCriteria.getVisitType(),
                visitCriteria.getPatientLastName(),
                visitCriteria.getDoctorLastName(),
                visitCriteria.getVisitDate(),
                visitCriteria.getStartDate(),
                visitCriteria.getEndDate(),
                sort
        );

        model.addAttribute("visits", visits);
        model.addAttribute("visitTypes", VisitType.values());
        model.addAttribute("visitsSortBy", VisitSortBy.values());
        return "visit/list";
    }


    @GetMapping("/create")
    public String getCreateForm(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("visitTypes", VisitType.values());
        model.addAttribute("visitDate", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        return "visit/create";
    }

    @PostMapping("/create")
    public String create(VisitDto visit, Model model) {
        model.addAttribute("visit", visit);
        visitService.create(visit);
        return "redirect:/visits";
    }
}
