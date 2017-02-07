package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.Place;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceRepository extends AbstractRepository<Place> {

    private final static String SELECT_ALL = "select * from places";
    private final static String SELECT_BY_ID = "select * from places where places.id=?";
    private final static String SELECT_BY_ROW_SEAT_SOLD_SESSION = "select * from places where places.row=? and places.seat=? and places.sold=? and places.sessionId=? and places.theaterId=?";
    private final static String DELETE_BY_ID = "delete from places where places.id=?";
    private final static String ADD_PLACE = "insert into places(row, seat, status, sold, sessionId, costMultiplier, theaterId) values(?,?,?,?,?,?,?)";

    public PlaceRepository(Connection conn) {
        super(conn);
    }

    @Override
    public Place getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Place(rs) : null;
    }

    public Place getByUserSession(int row, int seat, String sold, int session, int theater) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ROW_SEAT_SOLD_SESSION, row, seat, sold, session, theater);
        return rs.next() ? new Place(rs) : null;
    }

    @Override
    public Place add(Place place) throws SQLException {
        Place placeExist = getByUserSession(place.getRow(), place.getSeat(), place.getSold(), place.getSessionId(), place.getTheaterId());
        if (placeExist != null) return null;

        prepareUpdate(ADD_PLACE,
                place.getRow(),
                place.getSeat(),
                place.getStatus(),
                place.getSold(),
                place.getSessionId(),
                place.getCostMultiplier(),
                place.getTheaterId());

        return getByUserSession(place.getRow(), place.getSeat(), place.getSold(), place.getSessionId(), place.getTheaterId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Place> getAll() throws SQLException {
        List<Place> places = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            places.add(new Place(rs));
        }
        return places;
    }
}
