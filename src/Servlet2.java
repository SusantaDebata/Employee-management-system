import p1.Employee;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Servlet2 extends HttpServlet {

    public void init() {

        System.out.println("servlet2 init method");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("do post at RegistrationServlet");

        String email = request.getParameter("email");
        System.out.println("USER EMAIL:"+email);
        String pass = request.getParameter("password");
        System.out.println("USER PASSWORD:"+pass);
       
        HttpSession session = request.getSession();

        request.setAttribute("umail", email);
        System.out.println("email Request set");


        ServletContext context = getServletContext();
        Dao dao = (Dao) context.getAttribute("db");
        String uname = dao.check(email, pass);
        System.out.println("USER NAME IS:" + uname);
        System.out.println("check method complete");


        String imgName=dao.getImg(email);
        session.setAttribute("img", imgName);
        if (uname != null) {
            System.out.println("LOGIN SUCCESSFULLY");
            List<Employee> list = dao.getAllEmployee();
            request.setAttribute("allEmployee", list);
            session.setAttribute("name", uname);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        } else {

            RequestDispatcher rd1 = request.getRequestDispatcher("Login.html");
            rd1.forward(request, response);
        }
    }
}