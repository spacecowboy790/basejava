package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Position {

    private LocalDate beginDate;
    private LocalDate endDate;
    private String description;

    public Position(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return beginDate.equals(position.beginDate) && endDate.equals(position.endDate) &&
                description.equals(position.description);
    }

    @Override
    public int hashCode() {
        return beginDate.hashCode() + endDate.hashCode() + description.hashCode();
    }

    @Override
    public String toString() {
        return description + " " + beginDate + " " + endDate;
    }
}
