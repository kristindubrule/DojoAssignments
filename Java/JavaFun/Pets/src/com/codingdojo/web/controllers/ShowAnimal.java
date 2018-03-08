package com.codingdojo.web.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.codingdojo.web.models.*;
import java.util.Enumeration;

@WebServlet(urlPatterns={"/animals/cat","/animals/dog"})
public class ShowAnimal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Animal animal;
        if (request.getParameter("type") == "cat") {
            animal = new Cat(request.getParameter("name"),
                    request.getParameter("breed"),
                    Double.parseDouble(request.getParameter("weight")));
        } else {
            animal = new Dog(request.getParameter("name"),
                    request.getParameter("breed"),
                    Double.parseDouble(request.getParameter("weight")));
        }

        request.setAttribute("animal", animal);
        request.setAttribute("pet", request.getParameter("type"));
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/showAnimal.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}