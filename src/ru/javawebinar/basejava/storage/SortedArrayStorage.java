package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object searchIndex(Object searchKey) {
        Resume searchResume = new Resume((String) searchKey);
        return Arrays.binarySearch(storage, 0, size, searchResume);
    }

    @Override
    protected void saveResumeToArray(Object searchKey, Resume resume) {
        int index = (int) searchKey;
        index = -index - 1;
        if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }
}
