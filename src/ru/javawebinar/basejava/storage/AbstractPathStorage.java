package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class AbstractPathStorage  extends AbstractStorage<Path>{

    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path searchKey(String searchKey) {
        return null;
    }

    @Override
    protected boolean isResumeExist(Path searchKey) {
        return false;
    }

    @Override
    protected void deleteResume(Path searchKey) {

    }

    @Override
    protected void updateResume(Path searchKey, Resume resume) {

    }

    @Override
    protected void saveResume(Path searchKey, Resume resume) {

    }

    @Override
    protected Resume getResume(Path searchKey) {
        return null;
    }

    @Override
    protected List<Resume> getResumes() {
        return null;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        return 0;
    }
}
