package ru.javawebinar.basejava.model;

import java.util.List;

public class SkillsSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private List<String> skills;

    public SkillsSection(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillsSection skillsSection = (SkillsSection) o;

        return skills.equals(skillsSection.skills);
    }

    @Override
    public int hashCode() {
        return skills.hashCode();
    }

    @Override
    public String toString() {
        return skills.toString();
    }
}
