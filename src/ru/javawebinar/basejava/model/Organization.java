package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Organization {

    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String description;
    private String site;

    public Organization(String name) {
        this.name = name;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
