package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 3;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        deleteDetail(uuid, index);
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        updateDetail(resume, index);
    }

    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Нет места для сохранения");
        } else saveDetail(resume, index);
    }

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        return getDetail(uuid, index);
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

    protected abstract void deleteDetail(String uuid, int index);

    protected abstract void updateDetail(Resume resume, int index);

    protected abstract void saveDetail(Resume resume, int index);

    protected abstract Resume getDetail(String uuid, int index);

    protected void printWrongMessage(String uuid) {
        System.out.println("Резюме " + uuid + " не существует");
    }

    protected void printMessageIsExist(Resume resume) {
        System.out.println("Резюме " + resume.getUuid() + " уже существует");
    }
}