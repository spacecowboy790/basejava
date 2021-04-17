package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (isExistResume(index, uuid)) {
            deleteResume(index);
        }
    }

    @Override
    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (isExistResume(index, resume.getUuid())) {
            updateResume(index, resume);
        }
    }

    @Override
    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        try {
            if (isExistResume(index, resume.getUuid())) {
                throw new ExistStorageException(resume.getUuid());
            }
        } catch (NotExistStorageException notExistStorageException) {
            saveResume(index, resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (isExistResume(index, uuid)) {
            return getResume(index, uuid);
        }
        return null;
    }

    private boolean isExistResume(int index, String uuid) {
        if (index >= 0) {
            return true;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract int searchIndex(String uuid);

    protected abstract void deleteResume(Object searchKey);

    protected abstract void updateResume(int index, Resume resume);

    protected abstract void saveResume(int index, Resume resume);

    protected abstract Resume getResume(int index, String uuid);
}
