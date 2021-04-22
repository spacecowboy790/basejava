package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    private Object findSearchKey;

    @Override
    public void delete(String uuid) {
        deleteResume(getSearchKey(uuid));
    }

    @Override
    public void update(Resume resume) {
        updateResume(getSearchKey(resume.getUuid()), resume);
    }

    @Override
    public void save(Resume resume) {
        try {
            getSearchKey(resume.getUuid());
            throw new ExistStorageException(resume.getUuid());
        } catch (NotExistStorageException notExistStorageException) {
            saveResume(findSearchKey, resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        return getResume(getSearchKey(uuid));
    }

    private Object getSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (searchKey != null) {
            // сохранение индекса при условии, что индекс не число
            findSearchKey = searchKey;
            try {
                int indexForArrays = (int) searchKey;
                // сохранение индекса при условии, что индекс число (независимо от того, отрицательный или положительный)
                findSearchKey = indexForArrays;
                if (indexForArrays >= 0) {
                    // возвращение числового индекса, если он положительный, т.е. резюме существует
                    return findSearchKey;
                }
            } catch (ClassCastException classCastException) {
                // возвращение нечислового индекса
                return findSearchKey;
            }
        }
        // исключение кидается при условии, что индекс null или отрицательное число
        throw new NotExistStorageException(uuid);
    }

    protected abstract Object searchKey(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void saveResume(Object searchKey, Resume resume);

    protected abstract Resume getResume(Object searchKey);
}
