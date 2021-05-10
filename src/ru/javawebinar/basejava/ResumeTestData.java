package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

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
        experienceOrganization1.setBeginDate(LocalDate.parse("2013-10-01"));
        experienceOrganization1.setEndDate(LocalDate.now());
        experienceOrganization1.setDescription("Автор проекта." + '\t' + "Создание, организация и проведение Java онлайн проектов и стажировок.");
        experienceOrganization1.setSite("https://javaops.ru/");

        Organization experienceOrganization2 = new Organization("Wrike");
        experienceOrganization2.setBeginDate(LocalDate.parse("2014-10-01"));
        experienceOrganization2.setEndDate(LocalDate.parse("2016-01-01"));
        experienceOrganization2.setDescription("Старший разработчик (backend)" + '\t' + "Проектирование и разработка онлайн платформы управления проектами Wrike...");
        experienceOrganization2.setSite("https://www.wrike.com/");

        List<Organization> experienceOrganizationsDescription = new ArrayList<>();
        experienceOrganizationsDescription.add(experienceOrganization1);
        experienceOrganizationsDescription.add(experienceOrganization2);

        AbstractSection experienceOrganizations = new OrganizationsSection(experienceOrganizationsDescription);
        resume.addSection(SectionType.EXPERIENCE, experienceOrganizations);


        // заполнение секции Образование
        Organization studyOrganization1 = new Organization("Coursera");
        studyOrganization1.setBeginDate(LocalDate.parse("2013-03-01"));
        studyOrganization1.setEndDate(LocalDate.parse("2013-05-01"));
        studyOrganization1.setDescription("\"Functional Programming Principles in Scala\" by Martin Odersky");
        studyOrganization1.setSite("https://www.coursera.org/learn/progfun1");

        Organization studyOrganization2 = new Organization("Luxoft");
        studyOrganization2.setBeginDate(LocalDate.parse("2011-03-01"));
        studyOrganization2.setEndDate(LocalDate.parse("2011-04-01"));
        studyOrganization2.setDescription("Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        studyOrganization2.setSite("http://www.luxoft-training.ru/training/catalog/course.html?ID=22366");

        List<Organization> studyOrganizationsDescription = new ArrayList<>();
        studyOrganizationsDescription.add(studyOrganization1);
        studyOrganizationsDescription.add(studyOrganization2);

        AbstractSection studyOrganizations = new OrganizationsSection(studyOrganizationsDescription);
        resume.addSection(SectionType.EDUCATION, studyOrganizations);


        // вывод данных
        Map<ContactType, String> contacts = resume.getContacts();
        contacts.forEach((k, v) -> System.out.println("ContactType: " + k + ", Contact: " + v));
        Map<SectionType, AbstractSection> sections = resume.getSections();
        sections.forEach((k, v) -> System.out.println("SectionType: " + k + ", Section: " + v));
    }
}
