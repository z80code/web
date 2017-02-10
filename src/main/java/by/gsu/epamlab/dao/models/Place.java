package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Place {
    private int id;
    private String row;
    private String section;
    private int seat;
    private int theaterId;
    private int cost;

    public Place() {
    }
    public Place(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getInt(5),
                resultSet.getInt(6));
    }

    public Place(String row, String section, int seat, int theaterId, int cost) {
        this(0, row, section, seat, theaterId, cost);
    }

    public Place(int id, String row, String section, int seat, int theaterId, int cost) {
        this.id = id;
        this.row = row;
        this.section = section;
        this.seat = seat;
        this.theaterId = theaterId;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", row='" + row + '\'' +
                ", section='" + section + '\'' +
                ", seat=" + seat +
                ", theaterId=" + theaterId +
                ", cost=" + cost +
                '}';
    }
}

