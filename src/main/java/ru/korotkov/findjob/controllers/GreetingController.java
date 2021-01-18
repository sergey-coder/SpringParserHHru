package ru.korotkov.findjob.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.reactive.function.client.WebClient;
import ru.korotkov.findjob.model.Vacancy;
import ru.korotkov.findjob.services.VacancyService;
import ru.korotkov.findjob.util.VacancyUtil;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.net.URI;
import java.util.Objects;

@Controller
public class GreetingController {
    // проверить как на самом деле реализуются кнопки
    // дату публикации отформатировать
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
        //вывод должен быть постраничный
        // реализация фильтров
        // сортировка

        if(!specialization.equals("") && !nameCity.equals("")){
            vacancyUtil.saveVacancies(vacancyService, vacancyUtil.sendHTTPSrequest(specialization,nameCity));
        }

        model.addAttribute("vacancies",vacancyService.getALL());
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
    public String delete (@PathVariable("id") Long id, Model model) {
       vacancyService.delete(id);

       return "redirect:/vacancies";
    }
}
