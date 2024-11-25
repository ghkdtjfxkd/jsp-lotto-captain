package controller;

import dao.MemberDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Member;

import java.io.IOException;

public class MemberController extends HttpServlet {
    private final MemberDAO memberDAO = new MemberDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "register":
                handleRegister(request, response);
                break;
            case "login":
                handleLogin(request, response);
                break;
            case "update":
                handleUpdate(request, response);
                break;
            case "admin":
                handleAdmin(request, response);
                break;
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Member member = new Member(id, password, name, email);
        if (memberDAO.registerMember(member)) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("register.jsp?error=Registration failed");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        Member member = memberDAO.login(id, password);
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
            response.sendRedirect("mypage.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        if (member != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            member.setName(name);
            member.setEmail(email);
            memberDAO.updateMember(member);
            response.sendRedirect("mypage.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Session expired");
        }
    }

    private void handleAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") != null) {
            request.setAttribute("members", memberDAO.getAllMembers());
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
