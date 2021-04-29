package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        deleteResume(getSearchKeyIfResumeExist(uuid));
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        updateResume(getSearchKeyIfResumeExist(resume.getUuid()), resume);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        saveResume(getSearchKeyIfResumeNotExist(resume.getUuid()), resume);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getResume(getSearchKeyIfResumeExist(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted");
        List<Resume> resumes = getResumes();
        Collections.sort(resumes);
        return resumes;
    }

    private SK getSearchKeyIfResumeExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (isResumeExist(searchKey)) {
            return searchKey;
        }
        LOG.warning("Резюме " + uuid + " не существует");
        throw new NotExistStorageException(uuid);
    }

    private SK getSearchKeyIfResumeNotExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (!isResumeExist(searchKey)) {
            return searchKey;
        }
        LOG.warning("Резюме " + uuid + " уже существует");
        throw new ExistStorageException(uuid);
    }

    protected abstract SK searchKey(String searchKey);

    protected abstract boolean isResumeExist(SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract void updateResume(SK searchKey, Resume resume);

    protected abstract void saveResume(SK searchKey, Resume resume);

    protected abstract Resume getResume(SK searchKey);

    protected abstract List<Resume> getResumes();
}
