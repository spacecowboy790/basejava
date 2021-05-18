package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;

public interface WriteAndReadResumeStrategy {

    void writeResume(Resume resume, Object to) throws IOException;

    Resume readResume(Object from) throws IOException;
}
