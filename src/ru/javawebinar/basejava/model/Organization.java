package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.List;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String site;
    private List<Position> positions;

    public Organization(String name) {
        this.name = name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization organization = (Organization) o;

        return name.equals(organization.name) && site.equals(organization.site) &&
                positions.equals(organization.positions);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + site.hashCode() + positions.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder positionsString = new StringBuilder();
        for (Position position : positions) {
            positionsString.append(position.getDescription()).append(" ")
                    .append(position.getBeginDate()).append(" ").append(position.getEndDate()).append(" ");
        }
        return name + " " + site + " " + positionsString;
    }
}
