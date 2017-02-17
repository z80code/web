package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.models.*;
import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.models.ViewFilm;
import by.gsu.epamlab.services.CinemaService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CinemaLogic {

    private CinemaService cinemaService;

    public CinemaLogic() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.cinemaService = new CinemaService();
    }

    public List<ViewFilm> getActualFilms() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        List<Session> actualSessions = new SessionLogic().getActualSession();

        Set<Integer> onlyFilmsId = new HashSet<>();

        for (Session session : actualSessions) {
            onlyFilmsId.add(session.getFilmId());
        }

        List<Film> actualFilms = cinemaService.getFilmsDAO().getByIds(onlyFilmsId.toArray(new Integer[0]));

        List<ViewFilm> actualViewFilms = new ArrayList<>();


        for (Film film : actualFilms) {

            Director director = cinemaService.getDirectorRepository().getById(film.getDirectorId());

            List<Cast> casts = cinemaService.getCastRepository().getByFilmId(film.getId());

            List<Integer> actorIds = Helper.forEach(casts, new Helper.Func<Cast, Integer>() {
                @Override
                public Integer operation(Cast source) {
                    return source.getActorId();
                }
            });

            List<Actor> actors = cinemaService.getActorRepository().getByIds(actorIds.toArray(new Integer[0]));

            List<FilmGenre> filmGenres =  cinemaService.getFilmGenreRepository().getByFilmId(film.getId());

            List<Integer> genreIds = Helper.forEach(filmGenres, new Helper.Func<FilmGenre, Integer>() {
                @Override
                public Integer operation(FilmGenre source) {
                    return source.getGenreId();
                }
            });

            List<Genre> genres = cinemaService.getGenreRepository().getByIds(genreIds.toArray(new Integer[0]));

            actualViewFilms.add(
                    new ViewFilm(
                            film.getId(),
                            film.getTitle(),
                            film.getRelease(),
                            director,
                            actors,
                            film.getDescription(),
                            film.getImage(),
                            genres
                    ));
        }
        return actualViewFilms;
    }
}
