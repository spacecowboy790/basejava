package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected int searchIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteDetail(int index) {
        storage.remove(index);
    }

    @Override
    protected void updateDetail(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void saveDetail(int index, Resume resume) {
        if (index < 0) {
            storage.add(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    protected Resume getDetail(int index, String uuid) {
        return storage.get(index);
    }

    @Override
    protected void clearDetail() {
        storage.clear();
    }

    @Override
    protected int sizeDetail() {
        return storage.size();
    }

    @Override
    public Resume[] getAllDetail() {
        Resume[] resumes = new Resume[storage.size()];
        return storage.toArray(resumes);
    }
}
