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

    private Object getSearchKeyIfResumeExist(Object searchKeyForAction) {
        Object searchKeyIfResumeExist = searchKey(searchKeyForAction);
        if (isResumeExist(searchKeyIfResumeExist)) {
            return searchKeyIfResumeExist;
        }
        throw new NotExistStorageException((String) searchKeyForAction);
    }

    private Object getSearchKeyIfResumeNotExist(Object searchKeyForAction) {
        Object searchKeyIfResumeNotExist = searchKey(searchKeyForAction);
        if (!isResumeExist(searchKeyIfResumeNotExist)) {
            return searchKeyIfResumeNotExist;
        }
        throw new ExistStorageException((String) searchKeyForAction);
    }

    protected abstract Object searchKey(Object searchKey);

    protected abstract boolean isResumeExist(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void saveResume(Object searchKey, Resume resume);

    protected abstract Resume getResume(Object searchKey);
}
