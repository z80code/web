package by.gsu.epamlab.controllers;

import by.gsu.epamlab.dao.UserRepository;
import by.gsu.epamlab.dao.models.User;
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
import java.sql.Statement;

@WebServlet("/api")
public class TestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Connection conn = ConnectionFactory.getAccessConnection();

            UserRepository userRepository = new UserRepository(conn);

            PrintWriter out = resp.getWriter();
            Statement st = conn.createStatement();
            StringBuilder sb = new StringBuilder();

            userRepository.add(new User("test","54321", 0, "tolya", "from tam.by"));

            for (User user: userRepository.getAll()) {
                sb.append(user + "\n");
            }

            sb.append("\n");

            sb.append("\n");
            sb.append(userRepository.getById(5));
            sb.append("\n");
            sb.append("\n");

            userRepository.deleteById(5);
            sb.append(userRepository.getById(5));

            for (User user: userRepository.getAll()) {
                sb.append(user + "\n");
            }

            sb.append("\n");
            sb.append("\n");

            sb.append(userRepository.getByLogin("user"));
            out.println(sb.toString());

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
