package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStreamPathStorage implements WriteAndReadResumeStrategy {

    @Override
    public void writeResume(Resume resume, Object to) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(resume);
            Files.write((Path) to, baos.toByteArray());
        }
    }

    @Override
    public Resume readResume(Object from) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(Files.readAllBytes((Path) from));
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
