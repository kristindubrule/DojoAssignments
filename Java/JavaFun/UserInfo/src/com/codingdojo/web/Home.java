package com.codingdojo.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;

@WebServlet("/Home")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Sets the content type of the response
        HashMap<String,String> inputs = new HashMap<String,String>(4);

        inputs.put("firstName",request.getParameter("firstName"));
        inputs.put("lastName",request.getParameter("lastName"));
        inputs.put("favoriteLanguage",request.getParameter("favoriteLanguage"));
        inputs.put("homeTown",request.getParameter("homeTown"));
        response.setContentType("text/html");
        // writes the response
        PrintWriter out = response.getWriter();

        for( String key : inputs.keySet()) {
            if (inputs.get(key) == null) {
                inputs.put(key,"Unknown");
            }
        }

        out.write("<h1>Welcome, " + inputs.get("firstName") + " " + inputs.get("lastName") + "!</h1>");
        out.write("<h2>Your favorite language is: " + inputs.get("favoriteLanguage") + "</h2>");
        out.write("<h2>Your hometown is: " + inputs.get("homeTown") + "</h2>");
    }
}
