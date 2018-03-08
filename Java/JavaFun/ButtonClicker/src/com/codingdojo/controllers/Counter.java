package com.codingdojo.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Counter")
public class Counter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer counter = (Integer)(session.getAttribute("counter"));
        if (counter != null)
            session.setAttribute("counter",++counter);
        else
            session.setAttribute("counter",0);

        request.setAttribute("counter",counter);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/ShowCounter.jsp");
        view.forward(request, response);
    }
}

