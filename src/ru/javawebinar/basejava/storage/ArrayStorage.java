package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteDetail(String uuid, int index) {
        if (index != -1) {
            if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        } else {
            printWrongMessage(uuid);
        }
    }

    @Override
    protected void saveDetail(Resume resume, int index) {
        if (index == -1) {
            storage[size++] = resume;
        } else {
            printMessageIsExist(resume);
        }
    }
}