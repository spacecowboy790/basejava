package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            deleteDetail(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index >= 0) {
            updateDetail(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        saveDetail(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            return getDetail(index, uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void clear() {
        clearDetail();
    }

    @Override
    public int size() {
        return sizeDetail();
    }

    @Override
    public Resume[] getAll() {
        return getAllDetail();
    }

    protected abstract int searchIndex(String uuid);

    protected abstract void deleteDetail(int index);

    protected abstract void updateDetail(int index, Resume resume);

    protected abstract void saveDetail(int index, Resume resume);

    protected abstract Resume getDetail(int index, String uuid);

    protected abstract void clearDetail();

    protected abstract int sizeDetail();

    protected abstract Resume[] getAllDetail();
}
