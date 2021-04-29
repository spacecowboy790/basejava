package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected String searchKey(String searchKey) {
        return storage.containsKey(searchKey) ? searchKey : null;
    }

    @Override
    protected boolean isResumeExist(String searchKey) {
        return searchKey != null;
    }

    @Override
    protected void deleteResume(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void updateResume(String searchKey, Resume resume) {
        storage.replace(searchKey, resume);
    }

    @Override
    protected void saveResume(String searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return storage.get(searchKey);
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
