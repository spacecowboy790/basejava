package ru.javawebinar.basejava.model;

import java.util.List;

public class SkillsSection extends AbstractSection {

    private List<String> skills;

    public SkillsSection(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return skills.toString();
    }
}
