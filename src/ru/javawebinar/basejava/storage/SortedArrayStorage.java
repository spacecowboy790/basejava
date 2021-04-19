package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object searchIndex(Object searchKey) {
        Resume searchResume = new Resume((String) searchKey);
        int index = Arrays.binarySearch(storage, 0, size, searchResume);
        if (index > 0) return index;
        return null;
    }

    @Override
    protected void saveResumeToArray(Resume resume) {
        int index1 = (int) searchIndex(resume);
        int index = -index1 - 1;
        if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }
}
