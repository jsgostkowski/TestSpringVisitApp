package pl.exam.exam.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    @GetMapping
    public String getAll() {
        return "visit/list";
    }


}
