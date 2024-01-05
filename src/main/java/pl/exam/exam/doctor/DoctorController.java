package pl.exam.exam.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.exam.exam.doctor.model.Doctor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

}
