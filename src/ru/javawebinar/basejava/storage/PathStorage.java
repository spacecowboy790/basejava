package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.nio.file.Path;

public class PathStorage extends AbstractPathStorage {

    private WriteAndReadResumeStrategy writeAndReadResumeStrategy;

    public PathStorage(WriteAndReadResumeStrategy writeAndReadResumeStrategy, String dir) {
        super(dir);
        this.writeAndReadResumeStrategy = writeAndReadResumeStrategy;
    }

    @Override
    protected void writeResume(Resume resume, Path path) throws IOException {
        writeAndReadResumeStrategy.writeResume(resume, path);
    }

    @Override
    protected Resume readResume(Path path) throws IOException {
        return writeAndReadResumeStrategy.readResume(path);
    }
}
