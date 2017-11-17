/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.*;
import domainmodel.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class NotesServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        NoteService ns = new NoteService();
        UserService us = new UserService();
        String action = request.getParameter("action");
        String loggedUser = (String) session.getAttribute("username");
        
        if (action != null && action.equals("view")) {
            String selectedUsername = request.getParameter("selectedUsername");
            int noteId = Integer.parseInt(selectedUsername);
            try {

                Note note = ns.get(noteId);

                request.setAttribute("selectedUser", note);
            } catch (Exception ex) {
                Logger.getLogger(NotesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Note> notes = null;
        try {
            notes = us.get(loggedUser).getNoteList();
        } catch (Exception ex) {
            Logger.getLogger(NotesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").
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
        String action = request.getParameter("action");
        String noteId = request.getParameter("noteId");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");

        UserService us = new UserService();
        NoteService ns = new NoteService();
        String loggedUser = (String) session.getAttribute("username");


        try {
            if (action.equals("delete")) {
                String selectedUsername = request.getParameter("selectedUsername");
                int u_noteId = Integer.parseInt(selectedUsername);
                ns.delete(u_noteId);
            } else if (action.equals("edit")) {
                ns.update(Integer.parseInt(noteId), new Date(), title, contents, us.get(loggedUser));
            } else if (action.equals("add")) {
                ns.insert(Integer.parseInt(noteId), new Date(), title, contents, us.get(loggedUser));
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        List<Note> notes = null;
        
        try {
            notes = us.get(loggedUser).getNoteList();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").
                forward(request, response);
    }

}
