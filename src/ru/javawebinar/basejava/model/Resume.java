package ru.javawebinar.basejava.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume resume) {
        return fullName.equals(resume.fullName) ? uuid.compareTo(resume.uuid) : fullName.compareTo(resume.fullName);
    }

    public void addPhone(String phone) {
        contacts.put(ContactType.PHONE, phone);
    }

    public void addSkype(String skype) {
        contacts.put(ContactType.SKYPE, skype);
    }

    public void addEmail(String email) {
        contacts.put(ContactType.EMAIL, email);
    }

    public void addLinkedInProfile(String linkedInProfilePage) {
        contacts.put(ContactType.LINKEDIN, linkedInProfilePage);
    }

    public void addGitHubProfile(String gitHubProfilePage) {
        contacts.put(ContactType.GITHUB, gitHubProfilePage);
    }

    public void addStackoverflowProfile(String stackoverflowProfilePage) {
        contacts.put(ContactType.STACKOVERFLOW, stackoverflowProfilePage);
    }

    public void addHomePage(String homePageLink) {
        contacts.put(ContactType.HOMEPAGE, homePageLink);
    }

    public void addObjectiveSection(TextSection objectiveSectionDescription) {
        sections.put(SectionType.OBJECTIVE, objectiveSectionDescription);
    }

    public void addPersonalSection(TextSection personalSectionDescription) {
        sections.put(SectionType.PERSONAL, personalSectionDescription);
    }

    public void addQualificationsSection(SkillsSection qualificationsSectionDescription) {
        sections.put(SectionType.QUALIFICATIONS, qualificationsSectionDescription);
    }

    public void addAchievementSection(SkillsSection achievementSectionDescription) {
        sections.put(SectionType.ACHIEVEMENT, achievementSectionDescription);
    }
}
