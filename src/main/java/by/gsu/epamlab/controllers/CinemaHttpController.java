package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.DatabaseException;
import by.gsu.epamlab.factories.ConnectionFactory;
import by.gsu.epamlab.services.CinemaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static by.gsu.epamlab.contants.Constants.VIEW_DATETIME_FORMAT;

public class CinemaHttpController extends HttpServlet {

	Gson gson = new GsonBuilder().setDateFormat(VIEW_DATETIME_FORMAT).create();

	CinemaService getCinemaService() {
		try {
			return new CinemaService(ConnectionFactory.getMySQLConnection());
		} catch (ClassNotFoundException | SQLException e) {
			throw new DatabaseException(e);
		}
	}

	void setOutputParameters(HttpServletResponse resp) {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
	}
}
