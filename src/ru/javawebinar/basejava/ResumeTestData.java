package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = createResume("123", "Григорий Кислин");

        // вывод данных
        Map<ContactType, String> contacts = resume.getContacts();
        contacts.forEach((k, v) -> System.out.println("ContactType: " + k + ", Contact: " + v));
        Map<SectionType, Section> sections = resume.getSections();
        sections.forEach((k, v) -> System.out.println("SectionType: " + k + ", Section: " + v));
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        // заполнение контактов
        resume.addContact(ContactType.MOBILE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        // заполнение секций
        addSections(resume);

        return resume;
    }

    public static void addSections(Resume resume) {
        // заполнение секции Позиция
        resume.addSection(SectionType.OBJECTIVE, new TextSection("OBJECTIVE description"));

        // заполнение секции Личные качества
        resume.addSection(SectionType.PERSONAL, new TextSection("PERSONAL description"));

        // заполнение секции Достижения
        List<String> achievements = new ArrayList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven...");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike...");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River...");
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));

        // заполнение секции Квалификация
        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        // заполнение секции Опыт работы
        List<Organization> experienceOrganizations = new ArrayList<>();

        Organization.Position positionOneOfExperienceOrganizationOne =
                new Organization.Position(2013, Month.OCTOBER, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        List<Organization.Position> positionsOfExperienceOrganizationOne = new ArrayList<>();
        positionsOfExperienceOrganizationOne.add(positionOneOfExperienceOrganizationOne);
        Link linkOfExperienceOrganizationOne = new Link("Java Online Projects", "https://javaops.ru/");
        Organization experienceOrganizationOne = new Organization(linkOfExperienceOrganizationOne, positionsOfExperienceOrganizationOne);

        Organization.Position positionOneOfExperienceOrganizationTwo =
                new Organization.Position(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы...");
        List<Organization.Position> positionsOfExperienceOrganizationTwo = new ArrayList<>();
        positionsOfExperienceOrganizationTwo.add(positionOneOfExperienceOrganizationTwo);
        Link linkOfExperienceOrganizationTwo = new Link("Wrike", "https://www.wrike.com/");
        Organization experienceOrganizationTwo = new Organization(linkOfExperienceOrganizationTwo, positionsOfExperienceOrganizationTwo);

        experienceOrganizations.add(experienceOrganizationOne);
        experienceOrganizations.add(experienceOrganizationTwo);

        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(experienceOrganizations));

        // заполнение секции Образование
        List<Organization> education = new ArrayList<>();

        Organization.Position positionOneOfEducationOrganizationOne =
                new Organization.Position(2013, Month.MARCH, 2015, Month.MARCH, "\"Functional Programming Principles in Scala\" by Martin Odersky", "");
        List<Organization.Position> positionsOfEducationOrganizationOne = new ArrayList<>();
        positionsOfEducationOrganizationOne.add(positionOneOfEducationOrganizationOne);
        Link linkOfEducationOrganizationOne = new Link("Coursera", "https://www.coursera.org/learn/progfun1");
        Organization educationOrganizationOne = new Organization(linkOfEducationOrganizationOne, positionsOfEducationOrganizationOne);

        Organization.Position positionOneOfEducationOrganizationTwo =
                new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура (программист С, С++)", "");
        Organization.Position positionTwoOfEducationOrganizationTwo =
                new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер (программист Fortran, C)", "");
        List<Organization.Position> positionsOfEducationOrganizationTwo = new ArrayList<>();
        positionsOfEducationOrganizationTwo.add(positionOneOfEducationOrganizationTwo);
        positionsOfEducationOrganizationTwo.add(positionTwoOfEducationOrganizationTwo);
        Link linkOfEducationOrganizationTwo = new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/");
        Organization educationOrganizationTwo = new Organization(linkOfEducationOrganizationTwo, positionsOfEducationOrganizationTwo);

        education.add(educationOrganizationOne);
        education.add(educationOrganizationTwo);

        resume.addSection(SectionType.EDUCATION, new OrganizationSection(education));
    }
}
