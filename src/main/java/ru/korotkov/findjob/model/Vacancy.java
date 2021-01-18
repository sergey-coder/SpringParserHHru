package ru.korotkov.findjob.model;

import javax.persistence.*;

@Entity
@Table(name = "Searchvacanses_Vacancies")
public class Vacancy {
    @Id
    @GeneratedValue()
    private Long idvacancies;
    private String namevacancy;
    private String salary;
    private String employer;
    private String published_at;
    private String numbervacancy;
    private String reference_hhru;

    public Vacancy() {
    }

    public Vacancy(String namevacancy, String salary, String employer, String published_at, String numbervacancy, String reference_hhru) {
        this.reference_hhru = reference_hhru;
        this.namevacancy=namevacancy;
        this.salary=salary;
        this.employer=employer;
        this.published_at=published_at;
        this.numbervacancy=numbervacancy;
    }

    public Long getIdvacancies() {
        return idvacancies;
    }

    public void setIdvacancies(Long idvacancies) {
        this.idvacancies = idvacancies;
    }


    public String getNameVacancy() {
        return namevacancy;
    }

    public void setNameVacancy(String nameVacancy) {
        this.namevacancy = nameVacancy;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getNumberVacancy() {
        return numbervacancy;
    }

    public void setNumberVacancy(String numberVacancy) {
        this.numbervacancy = numberVacancy;
    }

    public String getReference_hhru() {
        return reference_hhru;
    }

    public void setReference_hhru(String reference_hhru) {
        this.reference_hhru = reference_hhru;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "nameVacancy='" + namevacancy + '\'' +
                ", salary='" + salary + '\'' +
                ", employer='" + employer + '\'' +
                ", published_at='" + published_at + '\'' +
                ", numberVacancy='" + numbervacancy + '\'' +
                '}';
    }
}
