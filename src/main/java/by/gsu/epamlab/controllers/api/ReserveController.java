package by.gsu.epamlab.controllers.api;

import by.gsu.epamlab.contants.Constants;
import by.gsu.epamlab.dao.models.Place;
import by.gsu.epamlab.enums.IndexPosition;
import by.gsu.epamlab.enums.RequestStatus;
import by.gsu.epamlab.exceptions.DatabaseException;
import by.gsu.epamlab.exceptions.IllegalParameters;
import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.logic.ReserveLogic;
import by.gsu.epamlab.models.ViewModel;
import by.gsu.epamlab.models.ViewUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static by.gsu.epamlab.contants.Constants.*;

@WebServlet(API_RESERVE_URL + ANY_SUB_PATH)
public class ReserveController extends CinemaHttpController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		setOutputParameters(resp);

		String argString = req.getRequestURI().replace(API_RESERVE_URL, EMPTY_STRING).trim();

		PrintWriter out = resp.getWriter();

		ReserveLogic reserveLogic = null;

		ViewModel responseData = new ViewModel();

		try {

			if (argString.length() > 0) {
				reserveLogic = new ReserveLogic(getCinemaService());
				Integer[] indexes = Helper.csvIndexesParser(argString);
				responseData.setData(reserveLogic.getSessionData(indexes[IndexPosition.ZERO.getIndex()]));
				responseData.setStatus(RequestStatus.OK);
			} else {
				throw new IllegalParameters();
			}
		} catch (ClassNotFoundException | SQLException e) {
			responseData = new ViewModel(RequestStatus.ERROR, e.getMessage(), null);
			throw new DatabaseException();
		} catch (IllegalParameters | NumberFormatException e) {
			responseData = new ViewModel(RequestStatus.ERROR, BAD_PARAMETERS, null);
			throw new IllegalParameters();
		} finally {
			if (out != null) {
				out.print(gson.toJson(responseData));
				out.close();
			}
			if(reserveLogic!=null){
				try {
					reserveLogic.close();
				} catch (SQLException e) {
					throw new DatabaseException(e);
				}
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ViewUser user = (ViewUser) req.getSession().getAttribute(Constants.USER);

	 String str =	req.getReader().readLine();
		Place[] selectedPlaceList = gson.fromJson(str, Place[].class);

		PrintWriter out = resp.getWriter();
		// TODO add to database
		out.println("Not implemented yet.");
	}
}
