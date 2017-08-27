package com.example.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = ("/createAccountServlet"))

public class CreateAccountServlet extends HttpServlet {
    @Override
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String fName = req.getParameter("firstName");
        String lName = req.getParameter("lastName");
        String balance = req.getParameter("balance");

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres","postgres" , "postgres");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into account VALUES(?,?,?)");
            preparedStatement.setString(1,fName);
            preparedStatement.setString(2,lName);
            preparedStatement.setDouble(3,Double.parseDouble(balance));
            preparedStatement.executeUpdate();
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/accountsList");
            dispatcher.forward(req, res);
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
