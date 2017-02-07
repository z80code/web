package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Place {
    private int id;
    private int row;
    private int seat;
    private String status;
    private String sold;
    private int sessionId;
    private int theaterId;

    public Place() {
    }
    public Place(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getInt(6),
                resultSet.getInt(6));
    }

    public Place(int row, int seat, String status, String sold, int sessionId, int theaterId) {
        this(0, row,  seat,  status,  sold,  sessionId,  theaterId);
    }

    public Place(int id, int row, int seat, String status, String sold, int sessionId, int theaterId) {
        this.id = id;
        this.row = row;
        this.seat = seat;
        this.status = status;
        this.sold = sold;
        this.sessionId = sessionId;
        this.theaterId = theaterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }
}

