package com.ar.apimovies.servlets;

import com.ar.apimovies.dao.UserDAO;
import com.ar.apimovies.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("nombre");
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();
        ObjectMapper objectMapper = new ObjectMapper();

        if (username != null && !username.isEmpty()) {
            users = userDAO.getUsersByName(username);
        } else {
            users = userDAO.getAllUsers();
        }

        String usersJson = objectMapper.writeValueAsString(users);

        response.getWriter().write(usersJson);
        response.setStatus(HttpServletResponse.SC_OK);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(df);
        User user = objectMapper.readValue(sb.toString(), User.class);

        if (user.getFechaNacimiento() == null) {
            response.getWriter().write("Fecha de nacimiento no proporcionada");
        }
        else{
            UserDAO userDAO = new UserDAO();
            Long resp = userDAO.insertUser(user);
            if (resp == 0) {
                response.getWriter().write("No se pudo agregar el usuario");
                response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            }
            else{
                response.getWriter().write("Usuario agregado exitosamente");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(sb.toString());
        String userIdStr = jsonNode.get("id").asText();

        if (userIdStr == null || userIdStr.isEmpty()) {
            response.getWriter().write("ID de usuario no proporcionado");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        int userId = Integer.parseInt(userIdStr);
        UserDAO userDAO = new UserDAO();
        int result= userDAO.deleteUser(userId);
        if (result == 0) {
            response.getWriter().write("No se pudo eliminar el usuario");
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
        }
        else {
            response.getWriter().write("Usuario eliminado exitosamente");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(df);
        User user = objectMapper.readValue(sb.toString(), User.class);

        if (user.getId() == null) {
            response.getWriter().write("ID de usuario no proporcionado");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        UserDAO userDAO = new UserDAO();
        int result = userDAO.updateUser(user);
        if (result == 0) {
            response.getWriter().write("No se pudo actualizar el usuario");
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
        }
        else {
            response.getWriter().write("Usuario actualizado exitosamente");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
