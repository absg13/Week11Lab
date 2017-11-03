/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Class;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 738377
 */
public class HomeServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").
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
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String currentgrade = request.getParameter("currentgrade");
        String desiredgrade = request.getParameter("desiredgrade");
        ArrayList<Class> classes = new ArrayList<>();

        if (request.getParameter("pageName").equals("add")) {

            if (name == null || code == null || currentgrade == null || desiredgrade == null) {
                request.setAttribute("classes", classes);
                session.setAttribute("classes", classes);
                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").
                        forward(request, response);

                return;
            } else if (name.isEmpty() || code.isEmpty() || currentgrade.isEmpty() || desiredgrade.isEmpty()) {
                request.setAttribute("classes", classes);
                session.setAttribute("classes", classes);
                request.setAttribute("message", "Invalid entry.");

                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").
                        forward(request, response);

                return;
            } else if (!currentgrade.matches("^-?\\d+$")
                    || !desiredgrade.matches("^-?\\d+$")) {
                request.setAttribute("message", "Invalid entry.");

                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").
                        forward(request, response);

                return;
            }

            int currentGrade = Integer.parseInt(currentgrade);
            int desiredGrade = Integer.parseInt(desiredgrade);

            if (currentGrade < 0 || currentGrade > 100) {
                request.setAttribute("message", "Invalid entry.");

                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").
                        forward(request, response);

                return;
            } else if (desiredGrade < 0 || desiredGrade > 100) {
                request.setAttribute("message", "Invalid entry.");

                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").
                        forward(request, response);

                return;
            }

            classes.add(new Class(name, code, currentGrade, desiredGrade));
            request.setAttribute("classes", classes);
            session.setAttribute("classes", classes);
            request.setAttribute("message", "Class added");

        } else if (request.getParameter("pageName").equals("delete")) {

            for (Class c : classes) {

                String deleted = request.getParameter("delete_" + c.getName());

                if (c.getName().equals(deleted)) {
                    classes.remove(c);

                    session.setAttribute("classes", classes);
                    return;
                }
            }

            request.setAttribute("message", "Class deleted");
        }

        request.setAttribute("classes", classes);
        session.setAttribute("classes", classes);
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").
                forward(request, response);
    }
}
