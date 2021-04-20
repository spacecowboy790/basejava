package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

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
            saveResumeToArray(searchKey, resume);
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveResumeToArray(Object searchKey, Resume resume);
}