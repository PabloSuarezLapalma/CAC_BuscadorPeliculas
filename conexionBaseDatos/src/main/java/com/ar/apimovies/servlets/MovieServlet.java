package com.ar.apimovies.servlets;
import com.ar.apimovies.dao.MovieDAO;
import com.ar.apimovies.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;


public class MovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String movieName = req.getParameter("titulo");
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies;
        ObjectMapper objectMapper = new ObjectMapper();
        String json;

        if (movieName != null && !movieName.isEmpty()) {
            movies = movieDAO.getMoviesByTitle(movieName);
        } else {
            movies = movieDAO.getAllMovies();
        }

        json = objectMapper.writeValueAsString(movies);
        resp.getWriter().write(json);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Movie movie = objectMapper.readValue(sb.toString(), Movie.class);

        MovieDAO movieDAO = new MovieDAO();
        Long movieId = movieDAO.insertMovie(movie);

        resp.getWriter().write("Película agregada exitosamente");
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(sb.toString());
        String movieIdStr = jsonNode.get("id").asText();

        if (movieIdStr == null || movieIdStr.isEmpty()) {
            resp.getWriter().write("ID de película no proporcionada");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        int movieId = Integer.parseInt(movieIdStr);
        MovieDAO movieDAO = new MovieDAO();
        int result =  movieDAO.deleteMovie(movieId);
        if (result == 0) {
            resp.getWriter().write("No se pudo eliminar la película");
            resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
        }
        else {
            resp.getWriter().write("Película eliminada exitosamente");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Movie movie = objectMapper.readValue(sb.toString(), Movie.class);

        if (movie.getId() == null) {
            resp.getWriter().write("ID de película no proporcionada");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MovieDAO movieDAO = new MovieDAO();
        int result =  movieDAO.updateMovie(movie);
        if (result == 0) {
            resp.getWriter().write("No se pudo actualizar la película");
            resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
        }
        else {
            resp.getWriter().write("Película actualizada exitosamente");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
