package ru.javawebinar.basejava.storage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(new ObjectStreamPathStorage(), STORAGE_DIR.getAbsolutePath()));
    }
}
