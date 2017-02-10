package by.gsu.epamlab.controllers;

import by.gsu.epamlab.dao.*;
import by.gsu.epamlab.dao.models.*;
import by.gsu.epamlab.factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/api")
public class TestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Connection conn = ConnectionFactory.getAccessConnection();

            PrintWriter out = resp.getWriter();

            StringBuilder sb = new StringBuilder();

            ActorRepository actors = new ActorRepository(conn);

            for (Actor item: actors.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            CastRepository casts = new CastRepository(conn);
            for (Cast item: casts.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            DirectorRepository directors = new DirectorRepository(conn);
            for (Director item: directors.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            FilmGenreRepository filmGenres = new FilmGenreRepository(conn);
            for (FilmGenre item: filmGenres.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            FilmRepository films = new FilmRepository(conn);
            for (Film item: films.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            GenreRepository genres = new GenreRepository(conn);
            for (Genre item: genres.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            PlaceRepository places = new PlaceRepository(conn);
            for (Place item: places.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            RoleRepository roles = new RoleRepository(conn);
            for (Role item: roles.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            SessionRepository sessions = new SessionRepository(conn);
            for (Session item: sessions.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            SoldPlaceRepository soldPlaces = new SoldPlaceRepository(conn);
            for (SoldPlace item: soldPlaces.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            TheaterRepository theaters = new TheaterRepository(conn);
            for (Theater item: theaters.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");

            UserRepository users = new UserRepository(conn);
            for (User item: users.getAll()) {
                sb.append(item + "\n");
            }
            sb.append(" * * * * * \n");


//            Statement st = conn.createStatement();

//
//            userRepository.add(new User("test","54321", 0, "tolya", "from tam.by"));
//
//            for (User user: userRepository.getAll()) {
//                sb.append(user + "\n");
//            }
//
//            sb.append("\n");
//
//            sb.append("\n");
//            sb.append(userRepository.getById(5));
//            sb.append("\n");
//            sb.append("\n");
//
//            userRepository.deleteById(5);
//            sb.append(userRepository.getById(5));
//
//            for (User user: userRepository.getAll()) {
//                sb.append(user + "\n");
//            }
//
//            sb.append("\n");
//            sb.append("\n");
//
//            sb.append(userRepository.getByName("user"));
            out.println(sb);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
