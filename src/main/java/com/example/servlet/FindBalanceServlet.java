package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns={"/findBalanceServlet"})

public class FindBalanceServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
               try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE firstName = ? AND lastName = ? ");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String fN = rs.getString("firstName");
                String lN = rs.getString("lastName");
                Double bal = Double.parseDouble(String.valueOf(rs.getBigDecimal("balance")));
                resp.getWriter().write(fN+ " "+lN+" account Balance is "+bal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
