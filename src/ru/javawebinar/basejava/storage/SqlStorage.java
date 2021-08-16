package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {

    private static final Logger LOG = Logger.getLogger(SqlStorage.class.getName());

    private final SqlHelper sqlHelper = new SqlHelper();

    @Override
    public void clear() {
        sqlHelper.makeOperation(ps -> {
            ps.executeUpdate();
            return null;
        }, "DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return sqlHelper.makeOperation(ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                LOG.warning("Резюме " + uuid + " не существует");
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        }, "SELECT * FROM resume r WHERE r.uuid =?");
    }

    @Override
    public void update(Resume r) {
        LOG.info("Update " + r);
        String resumeUuid = r.getUuid();
        sqlHelper.makeOperation(ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, resumeUuid);
            if (ps.executeUpdate() == 0) {
                LOG.warning("Резюме " + resumeUuid + " не существует");
                throw new NotExistStorageException(resumeUuid);
            }
            return null;
        }, "UPDATE resume SET full_name=? WHERE uuid=?");
    }

    @Override
    public void save(Resume r) {
        LOG.info("Save " + r);
        String resumeUuid = r.getUuid();
        sqlHelper.makeOperation(ps -> {
            ps.setString(1, resumeUuid);
            ps.setString(2, r.getFullName());
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                if (e.getSQLState().equals("23505")) {
                    LOG.warning("Резюме " + resumeUuid + " уже существует");
                    throw new ExistStorageException(resumeUuid);
                }
            }
            return null;
        }, "INSERT INTO resume (uuid, full_name) VALUES (?,?)");
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        sqlHelper.makeOperation(ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                LOG.warning("Резюме " + uuid + " не существует");
                throw new NotExistStorageException(uuid);
            }
            return null;
        }, "DELETE FROM resume r WHERE r.uuid =?");
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted");
        List<Resume> resumes = new ArrayList<>();
        sqlHelper.makeOperation(ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString(1).trim(), rs.getString(2).trim()));
            }
            return null;
        }, "SELECT * FROM resume r ORDER BY r.full_name, r.uuid");
        return resumes;
    }

    @Override
    public int size() {
        final int[] size = {0};
        sqlHelper.makeOperation(ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            size[0] = rs.getInt(1);
            return null;
        }, "SELECT COUNT(*) FROM resume");
        return size[0];
    }
}