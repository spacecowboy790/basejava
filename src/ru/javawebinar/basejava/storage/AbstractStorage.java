package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void delete(String uuid) {
        deleteResume(isExistResume(uuid));
    }

    @Override
    public void update(Resume resume) {
        updateResume(isExistResume(resume.getUuid()), resume);
    }

    @Override
    public void save(Resume resume) {
        try {
            isExistResume(resume.getUuid());
            throw new ExistStorageException(resume.getUuid());
        } catch (NotExistStorageException notExistStorageException) {
            saveResume(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        return getResume(isExistResume(uuid));
    }

    private Object isExistResume(String uuid) {
        Object index = searchIndex(uuid);
        if (index != null) {
            return index;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Object searchIndex(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract Resume getResume(Object searchKey);
}
