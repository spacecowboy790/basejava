package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;

    // Unique identifier
    private final String uuid;
    private String fullName;

    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void addContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void addSection(SectionType sectionType, AbstractSection abstractSection) {
        sections.put(sectionType, abstractSection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName) &&
                contacts.equals(resume.contacts) && sections.equals(resume.sections);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode() + fullName.hashCode() + contacts.hashCode() + sections.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume resume) {
        return fullName.equals(resume.fullName) ? uuid.compareTo(resume.uuid) : fullName.compareTo(resume.fullName);
    }
}
