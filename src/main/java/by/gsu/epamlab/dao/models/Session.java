package by.gsu.epamlab.dao.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private int id;
    private int filmId;
    private Date dateTime;
    private int cost;
    private int theaterId;

    public Session() {
    }

    public Session(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
             resultSet.getInt(2),
             resultSet.getDate(3),
             resultSet.getInt(4),
             resultSet.getInt(5));
    }

    public Session(int filmId, Date dateTime, int cost, int theaterId) {
        this(0, filmId, dateTime, cost, theaterId);
    }

    public Session(int id, int filmId, Date dateTime, int cost, int theaterId) {
        this.id = id;
        this.filmId = filmId;
        this.dateTime = dateTime;
        this.cost = cost;
        this.theaterId = theaterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", dateTime=" + dateTime +
                ", cost=" + cost +
                ", theaterId=" + theaterId +
                '}';
    }
}
