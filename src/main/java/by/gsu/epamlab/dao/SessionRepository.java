package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.Session;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository extends AbstractRepository<Session> {

    private final static String SELECT_ALL = "select * from sessions";
    private final static String SELECT_BY_ID = "select * from sessions where sessions.id=?";
    private final static String SELECT_BY_SESSION = "select * from sessions where sessions.filmId=? and sessions.dateTime=? and sessions.theaterId=?";
    private final static String DELETE_BY_ID = "delete from sessions where sessions.id=?";
    private final static String ADD_USER = "insert into sessions(filmId, dateTime, cost, theaterId) values(?,?,?,?)";

    public SessionRepository(Connection conn) {
        super(conn);
    }

    @Override
    public Session getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Session(rs) : null;
    }

    public Session getByUserSession(int filmId, Date dateTime, int theaterId) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_SESSION, filmId, dateTime, theaterId);
        return rs.next() ? new Session(rs) : null;
    }

    @Override
    public Session add(Session session) throws SQLException {
        Session sessionExist = getByUserSession(session.getFilmId(), session.getDateTime(), session.getTheaterId());
        if (sessionExist != null) return null;

        prepareUpdate(ADD_USER,
                session.getFilmId(),
                session.getDateTime(),
                session.getCost(),
                session.getTheaterId());

        return getByUserSession(session.getFilmId(), session.getDateTime(), session.getTheaterId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Session> getAll() throws SQLException {
        List<Session> sessions = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            sessions.add(new Session(rs));
        }
        return sessions;
    }
}