package ru.korotkov.findjob.services;

import org.springframework.data.domain.Page;
import ru.korotkov.findjob.model.Vacancy;

import java.util.List;

public interface VacancyService {

    void addVacancy(Vacancy vacancy);

    void delete(Long id);

    void deleteAll();

    List<Vacancy> getALL();

    Vacancy getVacancy(Long id);

    Page<Vacancy> getPageList(int page, int size);
}
