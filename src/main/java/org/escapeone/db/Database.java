package org.escapeone.db;

import org.escapeone.db.exception.DatabaseException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Database {

    private static Connection connection;
    private static final Logger logger = Logger.getLogger(Database.class.getName());

    public Database(){}

    public static Connection getConnection() {
        try {
            // verifica se a conexão é nula ou fechada
            if (connection == null || connection.isClosed()) {
                try (InputStream fs = Database.class.getClassLoader().getResourceAsStream("application.properties")) {
                    Properties props = new Properties();
                    props.load(fs);

                    String url = props.getProperty("db.url");
                    String user = props.getProperty("db.user");
                    String password = props.getProperty("db.password");

                    connection = DriverManager.getConnection(url, user, password);
                    logger.info("Database connected successfully!");
                } catch (IOException | SQLException e) {
                    logger.severe("Failed to connect to database: " + e.getMessage());
                    throw new DatabaseException("Database connection error", e);
                }
            }
        } catch (SQLException e) {
            // SQLException do isClosed()
            logger.severe("Failed to check if connection is closed: " + e.getMessage());
            throw new DatabaseException("Database connection error", e);
        }

        return connection;
    }


    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed.");
            } catch (SQLException e) {
                logger.severe("Failed to close database connection: " + e.getMessage());
                throw new DatabaseException("Error closing database connection", e);
            }
        }
    }
}
