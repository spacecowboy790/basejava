package ru.javawebinar.basejava.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(new ObjectStreamPathStorage(), STORAGE_DIR.getAbsolutePath()));
    }
}
