package com.codingdojo.greatnumbergame.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;
import java.util.HashMap;

@WebServlet(urlPatterns ={"/guess"})
public class Guess extends HttpServlet {

    private void guessResult(HttpServletRequest request, Integer number, Integer guess) {
        Integer result = guess - number;
        String message;
        String direction;

        if (result < 0) {
            message = "Too low!";
            direction = "low";
        } else if (result > 0) {
            message = "Too high!";
            direction = "high";
        } else {
            message = number + " was the result!";
            direction = "match";
        }
        request.setAttribute("message",message);
        request.setAttribute("direction",direction);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String result = "";
        Integer number = (Integer)session.getAttribute("number");
        Integer guess = Integer.parseInt(request.getParameter("guess"));
        Integer guessCount = (Integer)session.getAttribute("guessCount");

        if (guessCount == null)
            guessCount = 0;

        if (number == null) {
            Random rand = new Random();
            number = rand.nextInt(101);
            session.setAttribute("number",number);
            System.out.println(number);
        }

        request.setAttribute("guessed",guess);
        session.setAttribute("guessCount",++guessCount);
        guessResult(request, number, guess);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/numberGame.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/numberGame.jsp");
        view.forward(request, response);
    }
}
