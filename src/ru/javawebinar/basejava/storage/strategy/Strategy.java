package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;

public interface Strategy {

    void writeResume(Object to, Resume resume) throws IOException;

    Resume readResume(Object from) throws IOException;
}
