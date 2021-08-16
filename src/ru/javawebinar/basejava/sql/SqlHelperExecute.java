package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.model.Resume;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlHelperExecute {

    Resume execute(PreparedStatement ps) throws SQLException;
}
