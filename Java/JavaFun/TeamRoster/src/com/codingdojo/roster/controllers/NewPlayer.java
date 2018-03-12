package com.codingdojo.roster.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.codingdojo.roster.models.*;
import java.util.ArrayList;
import com.codingdojo.roster.models.TeamList;

@WebServlet("/addplayer")
public class NewPlayer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TeamList teams = (TeamList)session.getAttribute("teams");
        Integer teamID = Integer.parseInt(request.getParameter("teamID"));

        Team team = teams.findTeam(teamID);
        createPlayer(request, team);

        request.setAttribute("team",team);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/showTeam.jsp");
        view.forward(request, response);
    }

    private void createPlayer(HttpServletRequest request, Team team) {
        String firstName = (String)request.getParameter("firstName");
        String lastName = (String)request.getParameter("lastName");
        Integer age = Integer.parseInt(request.getParameter("age"));
        team.addPlayer(firstName,lastName,age);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer teamID = Integer.parseInt(request.getParameter("teamID"));
        TeamList teams = (TeamList)session.getAttribute("teams");

        Team team = teams.findTeam(teamID);
        request.setAttribute("team", team);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/newPlayer.jsp");
        view.forward(request, response);
    }
}
