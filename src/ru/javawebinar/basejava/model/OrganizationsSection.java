package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationsSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private List<Organization> organizations;

    public OrganizationsSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationsSection organizationsSection = (OrganizationsSection) o;

        return organizations.equals(organizationsSection.organizations);

    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
