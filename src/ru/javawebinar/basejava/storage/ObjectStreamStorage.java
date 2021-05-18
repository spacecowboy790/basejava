package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamStorage implements WriteAndReadResumeStrategy {

    @Override
    public void writeResume(Resume resume, Object to) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream((OutputStream) to)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume readResume(Object from) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream((InputStream) from)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
