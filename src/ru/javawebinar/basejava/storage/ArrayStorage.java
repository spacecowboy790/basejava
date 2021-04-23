package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object searchKey(Object searchKey) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(searchKey)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isResumeExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void saveResumeToArray(Object searchKey, Resume resume) {
        storage[size] = resume;
    }
}