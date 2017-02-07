package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.Cast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CastRepository extends AbstractRepository<Cast> {

    private final static String SELECT_ALL = "select * from cast";
    private final static String SELECT_BY_ID = "select * from cast where cast.id=?";
    private final static String SELECT_BY_FILM_ACTOR = "select * from cast where cast.filmId=? and cast.actorId=?";
    private final static String DELETE_BY_ID = "delete from cast where cast.id=?";
    private final static String ADD_CAST = "insert into users(filmId, actorId) values(?,?)";

    public CastRepository(Connection conn) {
        super(conn);
    }

    @Override
    public Cast getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Cast(rs) : null;
    }

    public Cast getByFilmAndActor(int film, int actor) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_FILM_ACTOR, film, actor);
        return rs.next() ? new Cast(rs) : null;
    }

    @Override
    public Cast add(Cast cast) throws SQLException {
        Cast userExist = getByFilmAndActor(cast.getFilmId(), cast.getActorId());
        if (userExist != null) return null;

        prepareUpdate(ADD_CAST,
                cast.getFilmId(),
                cast.getActorId());

        return getByFilmAndActor(cast.getFilmId(), cast.getActorId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Cast> getAll() throws SQLException {
        List<Cast> users = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            users.add(new Cast(rs));
        }
        return users;
    }
}
