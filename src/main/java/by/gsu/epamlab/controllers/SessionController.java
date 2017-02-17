package by.gsu.epamlab.controllers;

import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.logic.SessionLogic;
import by.gsu.epamlab.models.ViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/session/*")
public class SessionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			SessionLogic sessionLogic = new SessionLogic();

			Gson gson = new Gson();
			gson = new GsonBuilder()
					//.setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
					.setDateFormat("dd MMM yyyy kk:mm").create();

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			ViewModel responseData = null;

			String argString = req.getRequestURI().replace("/api/session", "").trim();
			if (argString.length() > 0 && !(argString.length() == 1 && argString.charAt(0) != '/')) {
				argString = argString.replace("/", "");
				Integer[] indexes = Helper.csvIndexesParser(argString);

				responseData = new ViewModel(
						"ok",
						"",
						sessionLogic.getSessionByFilmIds(indexes)
				);
			} else {
				// get all actual session
				responseData = new ViewModel(
						"ok",
						"",
						sessionLogic.getActualSession()
				);
			}
			out.print(gson.toJson(responseData));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}
