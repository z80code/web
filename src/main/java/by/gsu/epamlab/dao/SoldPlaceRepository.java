package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.SoldPlace;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoldPlaceRepository extends AbstractRepository<SoldPlace> {

    private final static String SELECT_ALL = "select * from soldPlaces";
    private final static String SELECT_BY_ID = "select * from soldPlaces where soldPlaces.id=?";
    private final static String SELECT_BY_USERID_SESSIONID = "select * from soldPlaces where soldPlaces.userId=? and soldPlaces.sessionId =?";
    private final static String SELECT_BY_USER_PLACE_SESSION = "select * from soldPlaces where soldPlaces.userId=? and soldPlaces.placeId=? and orders.sessionId=?";
    private final static String DELETE_BY_ID = "deletePlace from soldPlaces where soldPlaces.id=?";
    private final static String ADD_USER = "insert into orders(userId, placeId, sessionId) values(?,?,?)";

    public SoldPlaceRepository(Connection conn) {
        super(conn);
    }

    @Override
    SoldPlace createByResultSet(ResultSet rs) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public SoldPlace getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new SoldPlace(rs) : null;
    }

    public List<SoldPlace> getByIds(Integer... ids) throws SQLException {
        List<SoldPlace> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    private SoldPlace getByUniSession(int user, int place, int session) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_USER_PLACE_SESSION, user, place, session);
        return rs.next() ? new SoldPlace(rs) : null;
    }

	public List<SoldPlace> getUserSession(int user, int session) throws SQLException {
		List<SoldPlace> orders = new ArrayList<>();
		ResultSet rs = prepareRequest(SELECT_BY_USERID_SESSIONID, user, session);
		while (rs.next()) {
			orders.add(new SoldPlace(rs));
		}
		return orders;
	}


    @Override
    public SoldPlace add(SoldPlace order) throws SQLException {
        SoldPlace orderExist = getByUniSession(order.getUserId(),order.getPlaceId(), order.getSessionId());
        if (orderExist != null) return null;

        prepareUpdate(ADD_USER,
                order.getUserId(),
                order.getPlaceId(),
                order.getSessionId());

        return getByUniSession(order.getUserId(),order.getPlaceId(), order.getSessionId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<SoldPlace> getAll() throws SQLException {
        List<SoldPlace> orders = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            orders.add(new SoldPlace(rs));
        }
        return orders;
    }
}
