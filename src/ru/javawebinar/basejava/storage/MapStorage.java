package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected int searchIndex(String uuid) {
        return (storage.containsKey(uuid)) ? 1 : -1;
    }

    @Override
    protected void deleteResume(int index, String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        saveAndUpdate(resume);
    }

    @Override
    protected void saveResume(int index, Resume resume) {
        saveAndUpdate(resume);
    }

    @Override
    protected Resume getResume(int index, String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    private void saveAndUpdate(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }
}
