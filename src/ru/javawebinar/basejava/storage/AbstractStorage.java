package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            deleteResume(index, uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index >= 0) {
            updateResume(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index < 0) {
            saveResume(index, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            return getResume(index, uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract int searchIndex(String uuid);

    protected abstract void deleteResume(int index, String uuid);

    protected abstract void updateResume(int index, Resume resume);

    protected abstract void saveResume(int index, Resume resume);

    protected abstract Resume getResume(int index, String uuid);
}
