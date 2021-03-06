package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    protected static final String UUID_1 = UUID.randomUUID().toString();
    protected static final String UUID_2 = UUID.randomUUID().toString();
    protected static final String UUID_3 = UUID.randomUUID().toString();
    protected static final String DUMMY = UUID.randomUUID().toString();

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(ResumeTestData.createResume(UUID_3, "UUID_3_name"));
        storage.save(ResumeTestData.createResume(UUID_1, "UUID_1_name"));
        storage.save(ResumeTestData.createResume(UUID_2, "UUID_2_name"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test
    public void update() {
        Resume resume = ResumeTestData.createResume(UUID_1, "UUID_1_name");
        storage.update(resume);
        assertTrue(resume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(ResumeTestData.createResume(DUMMY, "DUMMY_name"));
    }

    @Test
    public void save() {
        Resume resume = ResumeTestData.createResume(DUMMY, "DUMMY_name");
        storage.save(resume);
        assertEquals(4, storage.size());
        assertEquals(resume, storage.get(DUMMY));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(ResumeTestData.createResume(UUID_1, "UUID_1_name"));
    }

    @Test
    public void get() {
        Resume resume = ResumeTestData.createResume(UUID_1, "UUID_1_name");
        assertEquals(resume, storage.get(UUID_1));
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
        resumes.add(ResumeTestData.createResume(UUID_1, "UUID_1_name"));
        resumes.add(ResumeTestData.createResume(UUID_2, "UUID_2_name"));
        resumes.add(ResumeTestData.createResume(UUID_3, "UUID_3_name"));
        assertEquals(resumes, storage.getAllSorted());
    }
}