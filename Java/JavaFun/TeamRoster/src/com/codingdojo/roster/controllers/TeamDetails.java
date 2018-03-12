package com.codingdojo.roster.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.codingdojo.roster.models.Team;
import java.util.ArrayList;
import com.codingdojo.roster.models.TeamList;


@WebServlet(urlPatterns = {"/teamdetails"})
public class TeamDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TeamList teams = (TeamList)session.getAttribute("teams");
        Integer teamID = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("team",teams.findTeam(teamID));
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/showTeam.jsp");
        view.forward(request, response);
    }
}
