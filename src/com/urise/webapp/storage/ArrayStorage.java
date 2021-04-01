package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Нет места для сохранения");
        } else if (searchIndex(resume.getUuid()) == -1) {
            storage[size++] = resume;
        } else {
            System.out.println("Резюме " + resume.getUuid() + " уже существует");
        }
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            printWrongMessage(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            printWrongMessage(uuid);
        }
        return null;
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index != -1) {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        } else {
            printWrongMessage(uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void printWrongMessage(String uuid) {
        System.out.println("Резюме " + uuid + " не существует");
    }
}