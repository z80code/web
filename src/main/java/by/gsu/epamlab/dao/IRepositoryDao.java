package by.gsu.epamlab.dao;

import org.omg.CORBA.Object;

import java.sql.SQLException;
import java.util.List;

public interface IRepositoryDao<T> {

    T  getById(int id) throws SQLException;
    T add(T item) throws SQLException;
    boolean deleteById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}
