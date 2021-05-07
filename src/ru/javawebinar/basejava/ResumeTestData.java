package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SkillsSection;
import ru.javawebinar.basejava.model.TextSection;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        // заполнение контактов
        resume.addPhone("+7(921) 855-0482");
        resume.addSkype("grigory.kislin");
        resume.addEmail("gkislin@yandex.ru");
        resume.addLinkedInProfile("https://www.linkedin.com/in/gkislin");
        resume.addGitHubProfile("https://github.com/gkislin");
        resume.addStackoverflowProfile("https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addHomePage("http://gkislin.ru/");

        // заполнение секции Позиция
        String objectiveSectionDescription = "Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям";
        TextSection objective = new TextSection(objectiveSectionDescription);
        resume.addObjectiveSection(objective);

        // заполнение секции Личные качества
        String personalSectionDescription = "Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям";
        TextSection personal = new TextSection(personalSectionDescription);
        resume.addPersonalSection(personal);

        // заполнение секции Достижения
        List<String> achievementSectionDescription = new ArrayList<>();
        achievementSectionDescription.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"...");
        achievementSectionDescription.add("Реализация двухфакторной аутентификации для онлайн платформы...");
        achievementSectionDescription.add("Налаживание процесса разработки и непрерывной интеграции ERP системы...");
        SkillsSection achievement = new SkillsSection(achievementSectionDescription);
        resume.addAchievementSection(achievement);

        // заполнение секции Квалификация
        List<String> qualificationsSectionDescription = new ArrayList<>();
        qualificationsSectionDescription.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsSectionDescription.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsSectionDescription.add("MySQL, SQLite, MS SQL, HSQLDB");
        SkillsSection qualifications = new SkillsSection(qualificationsSectionDescription);
        resume.addQualificationsSection(qualifications);

        // заполнение секции Опыт работы

        // заполнение секции Образование

        // вывод данных
        System.out.println(resume.getContacts().toString());
        System.out.println(resume.getSections().toString());
    }
}
