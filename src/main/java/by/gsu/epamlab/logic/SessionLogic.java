package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.models.Session;
import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.services.CinemaService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SessionLogic {

	private CinemaService cinemaService;

	public SessionLogic(CinemaService cinemaService) throws ClassNotFoundException, SQLException {
		this.cinemaService = cinemaService;
	}

	public List<Session> getActualSession() throws SQLException {
		List<Session> allSessions = cinemaService.getSessionRepository().getAll();

		Date dateNow = Helper.getCurrentDameTime();

		return SessionLogic.getDateFilter(dateNow, allSessions);
	}

	public List<Session> getSessionByFilmIds(Integer... ids) throws SQLException {
		return cinemaService.getSessionRepository().getByFilmIds(ids);
	}

	private static List<Session> getDateFilter(Date date, Collection<Session> collection) {
		List<Session> filteredByDate = new ArrayList<>();
		Session tempSession;
		for (Session aCollection : collection) {
			tempSession = aCollection;
			if (tempSession.getDateTime().compareTo(date) >= 0) {
				filteredByDate.add(tempSession);
			}
		}
		return filteredByDate;
	}
}
