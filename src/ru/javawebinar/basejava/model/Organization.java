package ru.javawebinar.basejava.model;

import java.util.List;

public class Organization {

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
    public String toString() {
        StringBuilder positionsString = new StringBuilder();
        for (Position position : positions) {
            positionsString.append(position.getDescription()).append(" ")
            .append(position.getBeginDate()).append(" ").append(position.getEndDate()).append(" ");
         }
        return name + " " + site + " " + positionsString;
    }
}
