package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.ObjectStreamStreamSerializer;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        //super(new FileStorage(new ObjectStreamStorage(), STORAGE_DIR));
        super(new FileStorage(new ObjectStreamStreamSerializer(), STORAGE_DIR));
    }
}
