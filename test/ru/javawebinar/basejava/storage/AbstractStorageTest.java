package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String DUMMY = "dummy";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_3, "UUID_3_name"));
        storage.save(new Resume(UUID_1, "UUID_1_name"));
        storage.save(new Resume(UUID_2, "UUID_2_name"));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        try {
            storage.get(UUID_1);
        } catch (StorageException exception) {
            System.out.println("Удаление резюме прошло успешно");
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1, "UUID_1_name");
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(DUMMY, "DUMMY_name"));
    }

    @Test
    public void save() {
        Resume resume = new Resume(DUMMY, "DUMMY_name");
        storage.save(resume);
        assertEquals(4, storage.size());
        assertEquals(resume, storage.get(DUMMY));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1, "UUID_1_name"));
    }

    @Test
    public void get() {
        assertEquals(new Resume(UUID_1, "UUID_1_name"), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        List<Resume> resumes = new ArrayList<>();
        resumes.add(new Resume(UUID_1, "UUID_1_name"));
        resumes.add(new Resume(UUID_2, "UUID_2_name"));
        resumes.add(new Resume(UUID_3, "UUID_3_name"));
        assertEquals(resumes, storage.getAllSorted());
    }
}