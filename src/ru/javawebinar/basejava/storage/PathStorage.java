package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {

    private StreamSerializer streamSerializer;
    private Path directory;

    public PathStorage(StreamSerializer streamSerializer, String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.streamSerializer = streamSerializer;
    }

    @Override
    protected Path getSearchKey(String searchKey) {
        return directory.resolve(searchKey);
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
            streamSerializer.writeResume(new BufferedOutputStream(Files.newOutputStream(path)), resume);
        } catch (IOException e) {
            throw new StorageException("Path write error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void saveResume(Path path, Resume resume) {
        if (Files.exists(path)) {
            throw new StorageException("Couldn't create path ", path.getFileName().toString());
        }
        updateResume(path, resume);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return streamSerializer.readResume(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getResumes() {
        return getFilesFromDirectory().stream().map(this::getResume).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFilesFromDirectory().forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return getFilesFromDirectory().size();
    }

    protected List<Path> getFilesFromDirectory() {
        try {
            return Files.list(directory).filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }
}
