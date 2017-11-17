/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import domainmodel.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 738377
 */
public class AccountServlet extends HttpServlet {

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || password == null) {
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
            
            return;
        } 
        
        else if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("message", "Both values are required to login.");
           
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
            
            return;
        }
        
        UserService us = new UserService();
        List <User> users = null;        
        try {
            users = (List<User>) us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("users", users);
        
        List<Note> notes = null;
        try {
            notes = us.get(username).getNoteList();
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        
        Role admin = new Role(1);
        Role reg = new Role(0);
        
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                if(u.getRole().getRoleID() == 1) {
                    getServletContext().getRequestDispatcher("/WEB-INF/admin/users.jsp").
                    forward(request, response);
                }
                else if(u.getRole().getRoleID() == 2) {
                    getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").
                    forward(request, response);
                }
            }
        }
        
        session.setAttribute("username", username);
        request.setAttribute("message", "Invalid username or password!");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
    }

    

}
