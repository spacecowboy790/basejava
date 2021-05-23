package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStreamStrategy implements Strategy {

    @Override
    public void writeResume(Object to, Resume resume) throws IOException {
        if (to instanceof File) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream((File) to)))) {
                oos.writeObject(resume);
            }
        } else if (to instanceof Path) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(resume);
                Files.write((Path) to, baos.toByteArray());
            }
        }
    }

    @Override
    public Resume readResume(Object from) throws IOException {
        if (from instanceof File) {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream((File) from)))) {
                return (Resume) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new StorageException("Error read resume", null, e);
            }
        } else if (from instanceof Path) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(Files.readAllBytes((Path) from));
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                return (Resume) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new StorageException("Error read resume", null, e);
            }
        }
        return null;
    }
}
