package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    private int id;
    private int userId;
    private int sessionId;
    private int totalPrice;

    public Order() {
    }
    public Order(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
             resultSet.getInt(2),
             resultSet.getInt(3),
             resultSet.getInt(4));
    }

    public Order(int userId, int sessionId, int totalPrice) {
        this(0, userId, sessionId, totalPrice);
    }

    public Order(int id, int userId, int sessionId, int totalPrice) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", sessionId=" + sessionId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
