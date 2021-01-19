package ru.korotkov.findjob.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korotkov.findjob.model.Vacancy;
import ru.korotkov.findjob.services.VacancyService;
import ru.korotkov.findjob.util.VacancyUtil;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class GreetingController {

    @Resource
    private VacancyService vacancyService;

    private VacancyUtil vacancyUtil;

    public GreetingController(VacancyUtil vacancyUtil) {
        this.vacancyUtil = vacancyUtil;
    }

    @GetMapping("/")
    public String getMainView() {
        return "main";
    }

    @PostMapping("/")
    public String getDeleteAll() {
        vacancyService.deleteAll();
        return "main";
    }

    @GetMapping("/vacancies")
    public String getAllVacancies(@RequestParam(value = "specialization", defaultValue = "") String specialization,
                                  @RequestParam(value = "nameCity", defaultValue = "") String nameCity,
                                  Model model) {

        if(!specialization.equals("") && !nameCity.equals("")){
            vacancyUtil.saveVacancies(vacancyService, vacancyUtil.sendHTTPSrequest(specialization,nameCity));
        }
        Page<Vacancy> page = vacancyService.getPageList(1,15);
         model.addAttribute("vacancyList",page);
        List<Vacancy> vacancyList = page.getContent();
        model.addAttribute("currentPage",1);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("vacancyList",vacancyList);
                return "vacancies";

    }

    @GetMapping("/page/{pageNo}")
    public  String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        Page<Vacancy> page = vacancyService.getPageList(pageNo,15);
        List<Vacancy> vacancyList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("vacancyList",vacancyList);
        return "vacancies";
    }

    @GetMapping("/vacancies/{id}")
    public String getVacancy(@PathVariable("id") Long id,
                             Model model) {
        model.addAttribute("vacancy",vacancyService.getVacancy(id));
        return "showVacancies";
    }

    @DeleteMapping("/")
    public String deleteAll() {
        vacancyService.deleteAll();
        return "redirect:/";
    }

    @RequestMapping(value = "/vacancies/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id) {
       vacancyService.delete(id);

       return "redirect:/vacancies";
    }
}
