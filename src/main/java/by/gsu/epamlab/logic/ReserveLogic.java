package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.Film;
import by.gsu.epamlab.dao.models.Place;
import by.gsu.epamlab.dao.models.Session;
import by.gsu.epamlab.dao.models.Theater;
import by.gsu.epamlab.models.ViewSessionInfo;

import java.sql.SQLException;
import java.util.List;

public class ReserveLogic  extends AbstractLogic {

	public ReserveLogic(CinemaDAO cinemaService) throws ClassNotFoundException, SQLException {
		super(cinemaService);
	}

	public ViewSessionInfo getSessionData(Integer sessionId) throws SQLException {
		Session userSelectedSession =cinemaService.getSessionRepository().getById(sessionId);
		Film userSelectedFilm = cinemaService.getFilmsRepository().getById(userSelectedSession.getFilmId());
		List<Place> theaterPlaces = cinemaService.getPlaceRepository().getByTheaterId(userSelectedSession.getTheaterId());
		Theater userSelectedTheater = cinemaService.getTheaterRepository().getById(userSelectedSession.getTheaterId());
	//	Integer[] userSelectedPlaces = cinemaService.getSoldPlaceRepository().getUserSession();
		return new ViewSessionInfo(
				userSelectedSession,
				userSelectedTheater,
				userSelectedFilm,
				theaterPlaces,
				new Integer[]{}
		);
	}




}
