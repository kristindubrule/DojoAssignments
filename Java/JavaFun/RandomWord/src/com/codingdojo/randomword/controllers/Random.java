package com.codingdojo.randomword.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.text.SimpleDateFormat;

@WebServlet("/Random")
public class Random extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer counter = (Integer)session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
            session.setAttribute("lastdate","None");
        } else if (request.getParameter("submit") != null) {
            request.setAttribute("word",randomString());
            session.setAttribute("lastdate",new SimpleDateFormat("MMMM d, YYYY - K:m a").format(new Date()));
            counter++;
        }

        session.setAttribute("counter",counter);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/GetRandom.jsp");
        view.forward(request, response);
    }

    private String randomString() {
        StringBuilder string = new StringBuilder(10);
        int randomNum;
        char base;
        for(int i = 0; i < 10; i++) {
            randomNum = ThreadLocalRandom.current().nextInt(0, 51);
            if (randomNum < 26) {
                base = 'A';
            } else {
                base = 'a';
            }
            string.append((char)(base + randomNum % 26));
        }
        return string.toString();
    }
}
