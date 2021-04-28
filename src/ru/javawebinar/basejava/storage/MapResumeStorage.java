package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected Object searchKey(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected boolean isResumeExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        storage.replace(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected void saveResume(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getResumes() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
