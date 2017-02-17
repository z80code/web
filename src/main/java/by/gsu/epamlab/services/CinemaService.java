package by.gsu.epamlab.services;

import by.gsu.epamlab.dao.*;
import by.gsu.epamlab.factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class CinemaService {

    private FilmRepository filmsDAO;
    private FilmGenreRepository filmGenreRepository;
    private GenreRepository genreRepository;
    private DirectorRepository directorRepository;
    private CastRepository castRepository;
    private ActorRepository actorRepository;
    private SessionRepository sessionRepository;
    private SoldPlaceRepository soldPlaceRepository;
    private PlaceRepository placeRepository;
    private TheaterRepository theaterRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public CinemaService() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection connection = ConnectionFactory.getMySQLConnection();
        this.filmsDAO = new FilmRepository(connection);
        this.filmGenreRepository = new FilmGenreRepository(connection);
        this.genreRepository = new GenreRepository(connection);
        this.directorRepository = new DirectorRepository(connection);
        this.castRepository = new CastRepository(connection);
        this.actorRepository = new ActorRepository(connection);
        this.sessionRepository = new SessionRepository(connection);
        this.soldPlaceRepository = new SoldPlaceRepository(connection);
        this.placeRepository = new PlaceRepository(connection);
        this.theaterRepository = new TheaterRepository(connection);
        this.userRepository = new UserRepository(connection);
        this.roleRepository = new RoleRepository(connection);
    }

    public FilmRepository getFilmsDAO() {
        return filmsDAO;
    }

    public FilmGenreRepository getFilmGenreRepository() {
        return filmGenreRepository;
    }

    public GenreRepository getGenreRepository() {
        return genreRepository;
    }

    public DirectorRepository getDirectorRepository() {
        return directorRepository;
    }

    public CastRepository getCastRepository() {
        return castRepository;
    }

    public ActorRepository getActorRepository() {
        return actorRepository;
    }

    public SessionRepository getSessionRepository() {
        return sessionRepository;
    }

    public SoldPlaceRepository getSoldPlaceRepository() {
        return soldPlaceRepository;
    }

    public PlaceRepository getPlaceRepository() {
        return placeRepository;
    }

    public TheaterRepository getTheaterRepository() {
        return theaterRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }
}