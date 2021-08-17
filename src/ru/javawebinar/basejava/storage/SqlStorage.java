package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {

    private static final Logger LOG = Logger.getLogger(SqlStorage.class.getName());

    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.makeOperation("DELETE FROM resume",
                ps -> {
                    ps.execute();
                    return null;
                });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return sqlHelper.makeOperation("SELECT * FROM resume r WHERE r.uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        LOG.warning("Резюме " + uuid + " не существует");
                        throw new NotExistStorageException(uuid);
                    }
                    return new Resume(uuid, rs.getString("full_name"));
                });
    }

    @Override
    public void update(Resume r) {
        LOG.info("Update " + r);
        String resumeUuid = r.getUuid();
        sqlHelper.makeOperation("UPDATE resume SET full_name=? WHERE uuid=?",
                ps -> {
                    ps.setString(1, r.getFullName());
                    ps.setString(2, resumeUuid);
                    if (ps.executeUpdate() == 0) {
                        LOG.warning("Резюме " + resumeUuid + " не существует");
                        throw new NotExistStorageException(resumeUuid);
                    }
                    return null;
                });
    }

    @Override
    public void save(Resume r) {
        LOG.info("Save " + r);
        String resumeUuid = r.getUuid();
        sqlHelper.makeOperation("INSERT INTO resume (uuid, full_name) VALUES (?,?)",
                ps -> {
                    ps.setString(1, resumeUuid);
                    ps.setString(2, r.getFullName());
                    ps.executeUpdate();
                    return null;
                });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        sqlHelper.makeOperation("DELETE FROM resume r WHERE r.uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    if (ps.executeUpdate() == 0) {
                        LOG.warning("Резюме " + uuid + " не существует");
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted");
        return sqlHelper.makeOperation("SELECT * FROM resume r ORDER BY r.full_name, r.uuid",
                ps -> {
                    List<Resume> resumes = new ArrayList<>();
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        resumes.add(new Resume(rs.getString(1), rs.getString(2)));
                    }
                    return resumes;
                });
    }

    @Override
    public int size() {
        return sqlHelper.makeOperation("SELECT COUNT(*) FROM resume",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    return rs.getInt(1);
                });
    }
}