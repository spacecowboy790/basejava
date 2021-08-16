package ru.javawebinar.basejava;

import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private Properties props = new Properties();
    private File storageDir;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private ConnectionFactory connectionFactory;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            dbUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.user");
            dbPassword = props.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public SqlStorage getSqlStorage() {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        return new SqlStorage();
    }

    public Connection getConnection() {
        try {
            return connectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
