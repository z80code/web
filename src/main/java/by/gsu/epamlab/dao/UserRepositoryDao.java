package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDao implements IRepositoryDao<User> {

    private final static String SELECT_ALL = "select * from users";
    private final static String SELECT_BY_ID = "select * from users where users.id=?";
    private final static String SELECT_BY_LOGIN = "select * from users where users.login=?";
    private final static String DELETE_BY_ID = "delete from users where users.id=?";
    private final static String ADD = "insert into users(login, password, roleId, name, info) values(?,?,?,?,?)";

    private Connection conn;
    private Statement st;
    private PreparedStatement preSt;

    public UserRepositoryDao(Connection conn) {
        this.conn = conn;
    }

    private User getUserFromResult(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getString(5),
                resultSet.getString(6)
        );
    }

    @Override
    public User getById(int id) throws SQLException {
        User user = null;
        try {
            preSt = conn.prepareStatement(SELECT_BY_ID);
            preSt.clearParameters();
            preSt.setInt(1, id);
            ResultSet rs = preSt.executeQuery();
            while (rs.next()) {
                user = getUserFromResult(rs);
            }
            return user;
        } finally {
            preSt.close();
        }
    }

    public User getByLogin(String login) throws SQLException {
        User user = null;
        try {
            preSt = conn.prepareStatement(SELECT_BY_LOGIN);
            preSt.setString(1, login);
            ResultSet rs = preSt.executeQuery();
            while (rs.next()) {
                user = getUserFromResult(rs);
            }
            return user;
        } finally {
            preSt.close();
        }
    }

    @Override
    public User add(User user) throws SQLException {
        User userExist = getByLogin(user.getLogin());
        if (userExist != null) return null;

        preSt = conn.prepareStatement(ADD);
        preSt.setString(1, user.getLogin());
        preSt.setString(2, user.getPassword());
        preSt.setInt(3, user.getRole());
        preSt.setString(4, user.getName());
        preSt.setString(5, user.getInfo());
        preSt.executeUpdate();

        return getByLogin(user.getLogin());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        preSt = conn.prepareStatement(DELETE_BY_ID);
        preSt.setInt(1, id);
        preSt.execute();
        return getById(id) == null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(SELECT_ALL);
        while (rs.next()) {
            users.add(getUserFromResult(rs));
        }
        return users;
    }
}
