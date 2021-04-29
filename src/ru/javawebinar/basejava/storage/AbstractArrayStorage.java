package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected boolean isResumeExist(Integer searchKey) {
        return (int) searchKey >= 0;
    }

    public void deleteResume(Integer searchKey) {
        int index = (int) searchKey;
        if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    public void updateResume(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    public void saveResume(Integer searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Нет места для сохранения", resume.getUuid());
        } else {
            saveResumeToArray(searchKey, resume);
            size++;
        }
    }

    @Override
    public Resume getResume(Integer searchKey) {
        return storage[searchKey];
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