package by.gsu.epamlab.logic;

import by.gsu.epamlab.services.CinemaService;

import java.sql.SQLException;

abstract class AbstractLogic {

	CinemaService cinemaService;

	public AbstractLogic(CinemaService cinemaService) throws ClassNotFoundException, SQLException {
		this.cinemaService = cinemaService;
	}

	public void close() throws SQLException {
		if (cinemaService != null) {
			cinemaService.close();
		}
	}
}
