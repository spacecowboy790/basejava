package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.ObjectStreamStrategy;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        //super(new FileStorage(new ObjectStreamStorage(), STORAGE_DIR));
        super(new FileStorage(new ObjectStreamStrategy(), STORAGE_DIR));
    }
}
