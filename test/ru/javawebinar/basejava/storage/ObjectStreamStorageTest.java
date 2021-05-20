package ru.javawebinar.basejava.storage;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new FileStorage(new ObjectStreamStorage(), STORAGE_DIR));
    }
}
