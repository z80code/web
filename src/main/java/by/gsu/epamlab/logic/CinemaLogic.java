package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.models.*;
import by.gsu.epamlab.models.ViewFilm;
import by.gsu.epamlab.services.CinemaService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class CinemaLogic {

    private CinemaService cinemaService;

    public CinemaLogic() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.cinemaService = new CinemaService();
    }

    private List<Session> newestThanFilter(Date date, Collection<Session> collection) {
        List<Session> filteredByDate = new ArrayList<>();
        Session tempSession;
        for (Iterator<Session> it = collection.iterator(); it.hasNext(); ) {
            tempSession = it.next();
            if (tempSession.getDateTime().compareTo(date) >= 0) {
                filteredByDate.add(tempSession);
            }
        }
        return filteredByDate;
    }

    interface Action<T1, T2> {
        T2 operation(T1 source);
    }

    private <T1, T2> List<T2> forEach(Collection<T1> collection, Action<T1, T2> action) {
        List<T2> resultList = new ArrayList<>();
        for (T1 item : collection) {
            resultList.add(action.operation(item));
        }
        return resultList;
    }

    public List<ViewFilm> getActualFilms() throws SQLException {

        List<Session> allSessions = cinemaService.getSessionRepository().getAll();

        Date dateNow = new Date(new java.util.Date().getTime());
        List<Session> actualSessions = newestThanFilter(dateNow, allSessions);

        SortedSet<Integer> onlyFilmsId = new TreeSet<>();
        for (Session session : actualSessions) {
            onlyFilmsId.add(session.getFilmId());
        }

        List<Film> actualFilms = cinemaService.getFilmsDAO().getByIds( onlyFilmsId.toArray(new Integer[0]));

        List<ViewFilm> actualViewFilms = new ArrayList<>();


        for (Film film : actualFilms) {

            Director director = cinemaService.getDirectorRepository().getById(film.getDirectorId());

            List<Cast> casts = cinemaService.getCastRepository().getByFilmId(film.getId());

            List<Integer> actorIds = forEach(casts, new Action<Cast, Integer>() {
                @Override
                public Integer operation(Cast source) {
                    return source.getActorId();
                }
            });

            List<Actor> actors = cinemaService.getActorRepository().getByIds(actorIds.toArray(new Integer[0]));

            List<FilmGenre> filmGenres =  cinemaService.getFilmGenreRepository().getByFilmId(film.getId());

            List<Integer> genreIds = forEach(filmGenres, new Action<FilmGenre, Integer>() {
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
