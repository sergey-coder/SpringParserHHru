package ru.korotkov.findjob.repositor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.korotkov.findjob.model.Vacancy;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy,Long> {
}
