package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
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

    public void deleteDetail(int index) {
        if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    public void updateDetail(int index, Resume resume) {
        storage[index] = resume;
    }

    public void saveDetail(int index, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Нет места для сохранения", resume.getUuid());
        } else if (index < 0) {
            saveDetailForArrays(resume, index);
            size++;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public Resume getDetail(int index, String uuid) {
        return storage[index];
    }

    public void clearDetail() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int sizeDetail() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAllDetail() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveDetailForArrays(Resume resume, int index);
}