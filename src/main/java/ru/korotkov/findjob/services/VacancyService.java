package ru.korotkov.findjob.services;

import ru.korotkov.findjob.model.Vacancy;

import java.util.List;

public interface VacancyService {
    Vacancy addVacancy(Vacancy vacancy);
    void delete(Long id);
    void deleteAll();
    List<Vacancy> getALL();
    Vacancy getVacancy(Long id);
}
