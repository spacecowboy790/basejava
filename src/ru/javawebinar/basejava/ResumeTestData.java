package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = createResume("123", "Григорий Кислин");

        // вывод данных
        Map<ContactType, String> contacts = resume.getContacts();
        contacts.forEach((k, v) -> System.out.println("ContactType: " + k + ", Contact: " + v));
        Map<SectionType, AbstractSection> sections = resume.getSections();
        sections.forEach((k, v) -> System.out.println("SectionType: " + k + ", Section: " + v));
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        // заполнение контактов
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        // заполнение секции Позиция
        String objectiveSectionDescription = "Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям";
        AbstractSection objective = new TextSection(objectiveSectionDescription);
        resume.addSection(SectionType.OBJECTIVE, objective);

        // заполнение секции Личные качества
        String personalSectionDescription = "Аналитический склад ума, сильная логика, креативность, инициативность. " +
                "Пурист кода и архитектуры.";
        AbstractSection personal = new TextSection(personalSectionDescription);
        resume.addSection(SectionType.PERSONAL, personal);

        // заполнение секции Достижения
        List<String> achievementSectionDescription = new ArrayList<>();
        achievementSectionDescription.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"...");
        achievementSectionDescription.add("Реализация двухфакторной аутентификации для онлайн платформы...");
        achievementSectionDescription.add("Налаживание процесса разработки и непрерывной интеграции ERP системы...");
        AbstractSection achievement = new SkillsSection(achievementSectionDescription);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);

        // заполнение секции Квалификация
        List<String> qualificationsSectionDescription = new ArrayList<>();
        qualificationsSectionDescription.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsSectionDescription.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsSectionDescription.add("MySQL, SQLite, MS SQL, HSQLDB");
        AbstractSection qualifications = new SkillsSection(qualificationsSectionDescription);
        resume.addSection(SectionType.QUALIFICATIONS, qualifications);


        // заполнение секции Опыт работы
        Organization experienceOrganization1 = new Organization("Java Online Projects");
        experienceOrganization1.setSite("https://javaops.ru/");

        List<Position> positionsOfExperienceOrganization1 = new ArrayList<>();
        Position positionOneOfExperienceOrganization1 = new Position("\"Автор проекта.\" + '\\t' + \"Создание, организация и проведение Java онлайн проектов и стажировок.\"");
        positionOneOfExperienceOrganization1.setBeginDate(LocalDate.parse("2013-10-01"));
        positionOneOfExperienceOrganization1.setEndDate(LocalDate.now());
        positionsOfExperienceOrganization1.add(positionOneOfExperienceOrganization1);
        experienceOrganization1.setPositions(positionsOfExperienceOrganization1);

        Organization experienceOrganization2 = new Organization("Wrike");
        experienceOrganization2.setSite("https://www.wrike.com/");

        List<Position> positionsOfExperienceOrganization2 = new ArrayList<>();
        Position positionOneOfExperienceOrganization2 = new Position("Старший разработчик (backend)" + '\t' + "Проектирование и разработка онлайн платформы управления проектами Wrike...");
        positionOneOfExperienceOrganization2.setBeginDate(LocalDate.parse("2014-10-01"));
        positionOneOfExperienceOrganization2.setEndDate(LocalDate.parse("2016-01-01"));
        positionsOfExperienceOrganization2.add(positionOneOfExperienceOrganization2);
        experienceOrganization2.setPositions(positionsOfExperienceOrganization2);

        List<Organization> experienceOrganizationsDescription = new ArrayList<>();
        experienceOrganizationsDescription.add(experienceOrganization1);
        experienceOrganizationsDescription.add(experienceOrganization2);

        AbstractSection experienceOrganizations = new OrganizationsSection(experienceOrganizationsDescription);
        resume.addSection(SectionType.EXPERIENCE, experienceOrganizations);


        // заполнение секции Образование
        Organization studyOrganization1 = new Organization("Coursera");
        studyOrganization1.setSite("https://www.coursera.org/learn/progfun1");

        List<Position> positionsOfStudyOrganization1 = new ArrayList<>();
        Position positionOneOfStudyOrganization1 = new Position("\"Functional Programming Principles in Scala\" by Martin Odersky");
        positionOneOfStudyOrganization1.setBeginDate(LocalDate.parse("2013-03-01"));
        positionOneOfStudyOrganization1.setEndDate(LocalDate.parse("2013-05-01"));
        positionsOfStudyOrganization1.add(positionOneOfStudyOrganization1);
        studyOrganization1.setPositions(positionsOfStudyOrganization1);

        Organization studyOrganization2 = new Organization("Luxoft");
        studyOrganization2.setSite("http://www.luxoft-training.ru/training/catalog/course.html?ID=22366");

        List<Position> positionsOfStudyOrganization2 = new ArrayList<>();
        Position positionOneOfStudyOrganization2 = new Position("Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        positionOneOfStudyOrganization2.setBeginDate(LocalDate.parse("2011-03-01"));
        positionOneOfStudyOrganization2.setEndDate(LocalDate.parse("2011-04-01"));
        positionsOfStudyOrganization2.add(positionOneOfStudyOrganization2);
        studyOrganization2.setPositions(positionsOfStudyOrganization2);

        Organization studyOrganization3 = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики");
        studyOrganization3.setSite("https://itmo.ru/ru/");

        List<Position> positionsOfStudyOrganization3 = new ArrayList<>();
        Position positionOneOfStudyOrganization3 = new Position("Инженер (программист Fortran, C)");
        positionOneOfStudyOrganization3.setBeginDate(LocalDate.parse("1987-09-01"));
        positionOneOfStudyOrganization3.setEndDate(LocalDate.parse("1993-07-01"));
        Position positionTwoOfStudyOrganization3 = new Position("Аспирантура (программист С, С++)");
        positionTwoOfStudyOrganization3.setBeginDate(LocalDate.parse("1993-09-01"));
        positionTwoOfStudyOrganization3.setEndDate(LocalDate.parse("1996-07-01"));
        positionsOfStudyOrganization3.add(positionOneOfStudyOrganization3);
        positionsOfStudyOrganization3.add(positionTwoOfStudyOrganization3);
        studyOrganization3.setPositions(positionsOfStudyOrganization3);

        List<Organization> studyOrganizationsDescription = new ArrayList<>();
        studyOrganizationsDescription.add(studyOrganization1);
        studyOrganizationsDescription.add(studyOrganization2);
        studyOrganizationsDescription.add(studyOrganization3);

        AbstractSection studyOrganizations = new OrganizationsSection(studyOrganizationsDescription);
        resume.addSection(SectionType.EDUCATION, studyOrganizations);

        return resume;
    }
}
