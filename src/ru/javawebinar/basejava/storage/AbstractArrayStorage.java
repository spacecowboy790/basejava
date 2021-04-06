package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        } else {
            printWrongMessage(uuid);
        }
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            printWrongMessage(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Нет места для сохранения");
        } else if (index < 0) {
            saveDetail(resume, index);
            size++;
        } else {
            printMessageIsExist(resume);
        }
    }

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        printWrongMessage(uuid);
        return null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int searchIndex(String uuid);

    protected abstract void saveDetail(Resume resume, int index);

    protected void printWrongMessage(String uuid) {
        System.out.println("Резюме " + uuid + " не существует");
    }

    protected void printMessageIsExist(Resume resume) {
        System.out.println("Резюме " + resume.getUuid() + " уже существует");
    }
}