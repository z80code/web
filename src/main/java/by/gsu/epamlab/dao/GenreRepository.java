package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.models.Film;
import by.gsu.epamlab.dao.models.Genre;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository extends AbstractRepository<Genre> {

    private final static String SELECT_ALL = "select * from genres";
    private final static String SELECT_BY_ID = "select * from genres where genres.id=?";
    private final static String SELECT_BY_NAME = "select * from genres where genres.name=?";
    private final static String DELETE_BY_ID = "delete from genres where genres.id=?";
    private final static String ADD_USER = "insert into genres(name, info) values(?,?)";

    public GenreRepository(Connection conn) {
        super(conn);
    }

    @Override
    public Genre getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Genre(rs) : null;
    }

    public Genre getByName(String name) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_NAME, name);
        return rs.next() ? new Genre(rs) : null;
    }

    @Override
    public Genre add(Genre genre) throws SQLException {
        Genre genreExist = getByName(genre.getName());
        if (genreExist != null) return null;

        prepareUpdate(ADD_USER,
                genre.getName(),
                genre.getInfo());

        return getByName(genre.getName());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Genre> getAll() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            genres.add(new Genre(rs));
        }
        return genres;
    }
}
