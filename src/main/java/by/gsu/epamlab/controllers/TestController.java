package by.gsu.epamlab.controllers;

import by.gsu.epamlab.logic.CinemaLogic;
import by.gsu.epamlab.models.ViewFilm;
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
import java.util.List;

@WebServlet("/api/films")
public class TestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            CinemaLogic cinemaLogic = new CinemaLogic();
            List<ViewFilm> listActual =  cinemaLogic.getActualFilms();
            Gson gson = new Gson();
            gson = new GsonBuilder()
                    //.setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
                    .setDateFormat("dd MMM yyyy").create();
            System.out.println(gson.toJson(listActual));

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json");
            PrintWriter out =  resp.getWriter();

            ViewModel responseData = new ViewModel(
                    "ok",
                    "",
                    listActual
            );
            out.print(gson.toJson(responseData));

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CinemaLogic cinemaLogic = new CinemaLogic();
            List<ViewFilm> listActual =  cinemaLogic.getActualFilms();
            Gson gson = new Gson();
            gson = new GsonBuilder()
                    //.setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
                    .setDateFormat("dd MMM yyyy").create();
            System.out.println(gson.toJson(listActual));

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json");
            PrintWriter out =  resp.getWriter();

            ViewModel responseData = new ViewModel(
                    "ok",
                    "",
                    listActual
            );
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
