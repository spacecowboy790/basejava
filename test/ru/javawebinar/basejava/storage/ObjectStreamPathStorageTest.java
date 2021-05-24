package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.ObjectStreamStreamSerializer;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        //super(new PathStorage(new ObjectStreamPathStorage(), STORAGE_DIR.getAbsolutePath()));
        super(new PathStorage(new ObjectStreamStreamSerializer(), STORAGE_DIR.getAbsolutePath()));
    }
}
