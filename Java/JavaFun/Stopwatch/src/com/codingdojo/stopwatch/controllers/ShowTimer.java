package com.codingdojo.stopwatch.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import com.codingdojo.stopwatch.models.Timer;
import org.joda.time.DateTime;

@WebServlet("/stopwatch")
public class ShowTimer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submit");
        HttpSession session = request.getSession();

        System.out.println("Post");

        // Get current timing information, if available
        Timer curtimer = (Timer)session.getAttribute("curtimer");
        ArrayList<Timer> timers = (ArrayList<Timer>)session.getAttribute("timers");
        DateTime startTime = (DateTime)session.getAttribute("startTime");

        // If a button was clicked (this isn't initial page load)
        if (action.equals("Start")) {
            if (curtimer == null)
                curtimer = new Timer();
            curtimer.startTimer();

            if (timers == null) {
                timers = new ArrayList<>();
                session.setAttribute("timers", timers);
            }
            timers.add(curtimer);
            session.setAttribute("curtimer", curtimer);
        }
        else if (action.equals("Stop") && curtimer != null) {
            curtimer.stopTimer();
            session.removeAttribute("curtimer");
        }
        else if (action.equals("Reset")) {
            session.removeAttribute("curtimer");
            session.removeAttribute("timers");
        }

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/showTimer.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/showTimer.jsp");
        view.forward(request, response);
    }
}
