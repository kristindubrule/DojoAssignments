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
import com.codingdojo.roster.models.TeamList;
import java.util.ArrayList;

@WebServlet("/teams")
public class NewTeam extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TeamList teams = (TeamList)session.getAttribute("teams");
        String teamName = request.getParameter("name");

        if (teamName.length() > 0) {
            if (teams == null) {
                teams = new TeamList();
                session.setAttribute("teams",teams);
            }
            teams.addTeam(teamName);

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/showTeams.jsp");
            view.forward(request, response);
        } else {
            request.setAttribute("message","No team name provided");
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/newTeam.jsp");
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/newTeam.jsp");
        view.forward(request, response);
    }
}
