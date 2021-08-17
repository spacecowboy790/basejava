package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Executor<T> {

    T execute(PreparedStatement ps) throws SQLException;
}
