package ru.korotkov.findjob.services.impl;

import org.springframework.stereotype.Service;
import ru.korotkov.findjob.model.Vacancy;
import ru.korotkov.findjob.repositor.VacancyRepository;
import ru.korotkov.findjob.services.VacancyService;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    private VacancyRepository repositoryVacancies;

    public VacancyServiceImpl(VacancyRepository repositoryVacancies) {
        this.repositoryVacancies = repositoryVacancies;
    }


    @Override
    public Vacancy addVacancy(Vacancy vacancy) {
        return  repositoryVacancies.saveAndFlush(vacancy);
    }

    @Override
    public void delete(Long id) {
        repositoryVacancies.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repositoryVacancies.deleteAll();
    }

    @Override
    public List<Vacancy> getALL() {
        return  repositoryVacancies.findAll();
    }

    @Override
    public Vacancy getVacancy(Long id){
        return repositoryVacancies.getOne(id);
    }
}
