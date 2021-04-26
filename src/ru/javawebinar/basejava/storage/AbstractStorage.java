package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void delete(String uuid) {
        deleteResume(getSearchKeyIfResumeExist(uuid));
    }

    @Override
    public void update(Resume resume) {
        updateResume(getSearchKeyIfResumeExist(resume.getUuid()), resume);
    }

    @Override
    public void save(Resume resume) {
        saveResume(getSearchKeyIfResumeNotExist(resume.getUuid()), resume);
    }

    @Override
    public Resume get(String uuid) {
        return getResume(getSearchKeyIfResumeExist(uuid));
    }

    private Object getSearchKeyIfResumeExist(String uuid) {
        Object searchKey = searchKey(uuid);
        if (isResumeExist(searchKey)) {
            return searchKey;
        }
        throw new NotExistStorageException(uuid);
    }

    private Object getSearchKeyIfResumeNotExist(String uuid) {
        Object searchKey = searchKey(uuid);
        if (!isResumeExist(searchKey)) {
            return searchKey;
        }
        throw new ExistStorageException(uuid);
    }

    protected abstract Object searchKey(String searchKey);

    protected abstract boolean isResumeExist(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void saveResume(Object searchKey, Resume resume);

    protected abstract Resume getResume(Object searchKey);
}
