package com.example.servlet;

import com.main.servlet.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = ("/accountsList"))
public class AccountsListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String balance = req.getParameter("balance");

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from account");
            List<Account> accounts = new ArrayList<Account>();
            while (rs.next()) {
                Account account = new Account();
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                account.setBalance(Double.parseDouble(rs.getString("balance")));
                accounts.add(account);
            }
            req.setAttribute("accList", accounts);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/accountsList.jsp");
            dispatcher.forward(req, res);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
