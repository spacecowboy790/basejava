package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int searchIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveDetail(Resume resume, int index) {
        index = -index - 1;
        if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }
}
