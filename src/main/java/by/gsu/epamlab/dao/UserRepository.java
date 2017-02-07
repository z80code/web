package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository<User> {

    private final static String SELECT_ALL = "select * from users";
    private final static String SELECT_BY_ID = "select * from users where users.id=?";
    private final static String SELECT_BY_LOGIN = "select * from users where users.login=?";
    private final static String DELETE_BY_ID = "delete from users where users.id=?";
    private final static String ADD_USER = "insert into users(login, password, roleId, name, info) values(?,?,?,?,?)";

    public UserRepository(Connection conn) {
        super(conn);
    }

    @Override
    public User getById(int id) throws SQLException {
        ResultSet rs =prepareRequest(SELECT_BY_ID,id);
        return rs.next() ? new User(rs) : null;
    }

    public User getByLogin(String login) throws SQLException {
        ResultSet rs =prepareRequest(SELECT_BY_LOGIN,login);
        return rs.next() ? new User(rs) : null;
    }

    @Override
    public User add(User user) throws SQLException {
        User userExist = getByLogin(user.getLogin());
        if (userExist != null) return null;

        prepareUpdate(ADD_USER,
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                user.getName(),
                user.getInfo());

        return getByLogin(user.getLogin());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id)==1;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            users.add(new User(rs));
        }
        return users;
    }
}