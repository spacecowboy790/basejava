package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    protected Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path searchKey(String searchKey) {
        return Paths.get(directory.toAbsolutePath() + "/" + searchKey);
    }

    @Override
    protected boolean isResumeExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString());
        }
    }

    @Override
    protected void updateResume(Path path, Resume resume) {
        try {
            writeResume(resume, path);
        } catch (IOException e) {
            throw new StorageException("Path write error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void saveResume(Path path, Resume resume) {
        Path newPath = path;
        if (Files.exists(newPath)) {
            throw new StorageException("Couldn't create path ", path.getFileName().toString());
        }
        updateResume(newPath, resume);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return readResume(path);
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getResumes() {
        List<Resume> resumes = new ArrayList<>();
        try {
            List<Path> list = Files.walk(directory).filter(Files::isRegularFile).collect(Collectors.toList());
            for (Path path : list) {
                resumes.add(getResume(path));
            }
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
        return resumes;
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
        long size = 0;
        try (Stream<Path> files = Files.list(directory)) {
            size = files.count();
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
        return (int) size;
    }

    protected abstract void writeResume(Resume resume, Path path) throws IOException;

    protected abstract Resume readResume(Path path) throws IOException;
}
