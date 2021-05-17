package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void writeResume(Resume resume, Path path) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(resume);
            Files.write(path, baos.toByteArray());
        }
    }

    @Override
    protected Resume readResume(Path path) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(Files.readAllBytes(path));
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
