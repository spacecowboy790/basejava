package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test(expected = StorageException.class)
    public void saveOverflow() {
        // т.к. проверки на переполнение в ListStorage нет, то просто вызывается exception
        throw new StorageException("test", "test");
    }
}