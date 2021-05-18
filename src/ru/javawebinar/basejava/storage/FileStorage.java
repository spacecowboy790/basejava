package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileStorage extends AbstractFileStorage {

    private WriteAndReadResumeStrategy writeAndReadResumeStrategy;

    public FileStorage(WriteAndReadResumeStrategy writeAndReadResumeStrategy, File directory) {
        super(directory);
        this.writeAndReadResumeStrategy = writeAndReadResumeStrategy;
    }

    @Override
    protected void writeResume(Resume resume, OutputStream os) throws IOException {
        writeAndReadResumeStrategy.writeResume(resume, os);
    }

    @Override
    protected Resume readResume(InputStream is) throws IOException {
        return writeAndReadResumeStrategy.readResume(is);
    }
}
