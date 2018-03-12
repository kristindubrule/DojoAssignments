package com.codingdojo.roster.controllers;

import com.codingdojo.roster.models.Team;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import com.codingdojo.roster.models.TeamList;

@WebServlet("/deleteplayer")
public class DeletePlayer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TeamList teams = (TeamList)session.getAttribute("teams");
        Integer teamID = Integer.parseInt(request.getParameter("teamID"));
        Integer playerID = Integer.parseInt(request.getParameter("id"));

        Team team = teams.findTeam(teamID);
        team.removePlayer(playerID);

        request.setAttribute("team",team);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/showTeam.jsp");
        view.forward(request, response);
    }
}
