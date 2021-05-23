package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String searchKey) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(searchKey)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveResumeToArray(int index, Resume resume) {
        storage[size] = resume;
    }
}