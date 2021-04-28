package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected boolean isResumeExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    public void deleteResume(Object searchKey) {
        int index = (int) searchKey;
        if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    public void updateResume(Object searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    public void saveResume(Object searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Нет места для сохранения", resume.getUuid());
        } else {
            saveResumeToArray((int) searchKey, resume);
            size++;
        }
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public List<Resume> getResumes() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(storage, size)));
    }

    protected abstract void saveResumeToArray(int searchKey, Resume resume);
}