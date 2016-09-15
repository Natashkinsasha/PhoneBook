package main.DAO;


import java.sql.SQLException;

public class DAOException extends Exception {
    public DAOException(Exception e) {
        super(e);
    }
}
