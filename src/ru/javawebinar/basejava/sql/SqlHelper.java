package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    public Resume makeOperation(SqlHelperExecute sqlHelper, String sql) {
        try (Connection conn = Config.get().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return sqlHelper.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
