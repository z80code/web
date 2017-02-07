package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.Order;
import by.gsu.epamlab.dao.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends AbstractRepository<Order> {

    private final static String SELECT_ALL = "select * from orders";
    private final static String SELECT_BY_ID = "select * from orders where orders.id=?";
    private final static String SELECT_BY_USER_SESSION = "select * from orders where orders.userId=? and orders.sessionId=?";
    private final static String DELETE_BY_ID = "delete from orders where orders.id=?";
    private final static String ADD_USER = "insert into orders(userId, sessionId, totalPrice) values(?,?,?)";

    public OrderRepository(Connection conn) {
        super(conn);
    }

    @Override
    public Order getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Order(rs) : null;
    }

    public Order getByUserSession(int user, int session) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_USER_SESSION, user, session);
        return rs.next() ? new Order(rs) : null;
    }

    @Override
    public Order add(Order order) throws SQLException {
        Order orderExist = getByUserSession(order.getUserId(), order.getSessionId());
        if (orderExist != null) return null;

        prepareUpdate(ADD_USER,
                order.getUserId(),
                order.getSessionId(),
                order.getTotalPrice());

        return getByUserSession(order.getUserId(), order.getSessionId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            orders.add(new Order(rs));
        }
        return orders;
    }
}
