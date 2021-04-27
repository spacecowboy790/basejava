package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer searchKey(String searchKey) {
        Resume searchResume = new Resume(searchKey, "");
        return Arrays.binarySearch(storage, 0, size, searchResume, RESUME_COMPARATOR);
    }

    @Override
    protected void saveResumeToArray(int index, Resume resume) {
        index = -index - 1;
        if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }
}
