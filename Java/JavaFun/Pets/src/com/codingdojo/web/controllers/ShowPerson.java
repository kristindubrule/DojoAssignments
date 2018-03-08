package com.codingdojo.web.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.codingdojo.web.models.Person;

@WebServlet("/ShowPerson")
public class ShowPerson extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowPerson() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        Person person = new Person(name, age);

        request.setAttribute("person",person);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/showPerson.jsp");
        view.forward(request, response);
    }
}
