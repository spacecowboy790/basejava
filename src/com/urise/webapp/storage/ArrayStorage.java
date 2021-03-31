package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Нет места для сохранения");
        } else if (searchIndex(r.getUuid()) == -1) {
            storage[size++] = r;
        } else {
            System.out.println("Такое резюме уже существует");
        }
    }

    public void update(Resume r) {
        if (searchIndex(r.getUuid()) != -1) {
            int index = searchIndex(r.getUuid());
            Resume resume = new Resume();
            resume.setUuid("new " + r.getUuid());
            storage[index] = resume;
        } else {
            printWrongMessage();
        }
    }

    public Resume get(String uuid) {
        if (searchIndex(uuid) != -1) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            printWrongMessage();
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
            printWrongMessage();
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

    private void printWrongMessage() {
        System.out.println("Резюме не существует");
    }
}