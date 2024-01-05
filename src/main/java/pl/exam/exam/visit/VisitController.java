package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.exam.exam.common.VisitType;
import pl.exam.exam.doctor.DoctorService;
import pl.exam.exam.patient.PatientService;
import pl.exam.exam.patient.model.Patient;
import pl.exam.exam.visit.model.Visit;
import pl.exam.exam.visit.model.dto.VisitDto;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    //    @GetMapping
//    public String getAll(@RequestParam(name = "visitType", required = false) VisitType visitType,
//
//                         Model model) {
//        List<VisitDto> visits = visitService.getAll(visitType);
//        model.addAttribute("visits", visits);
//        model.addAttribute("visitTypes", VisitType.values());
//        return "visit/list";
//    }
    @GetMapping
    public String getAll(@RequestParam(name = "visitType", required = false) VisitType visitType,
                         @RequestParam(name = "doctorFirstName", required = false) String doctorFirstName,
                         @RequestParam(name = "patientFirstName", required = false) String patientFirstName,
                         @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                         @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate,
                         Model model) {

        List<VisitDto> visits;

        if (visitType != null) {
            visits = visitService.getAll(visitType);
        } else if (doctorFirstName != null || patientFirstName != null || startDate != null || endDate != null) {
            visits = visitService.searchByCriteria(doctorFirstName, patientFirstName, startDate, endDate);
        } else {
            visits = visitService.getAll(null);
        }

        model.addAttribute("visits", visits);
        model.addAttribute("visitTypes", VisitType.values());
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
