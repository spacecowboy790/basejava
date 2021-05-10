package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationsSection extends AbstractSection {

    private List<Organization> organizations;

    public OrganizationsSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
