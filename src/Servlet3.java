import p1.Employee;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Servlet3 extends HttpServlet {
        public void init() {
                System.out.println("update init method Call");
        }

        public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                System.out.println("UPDATE DATA GET");
                String ename = req.getParameter("t2");
                String eaddr = req.getParameter("t3");
                String email = req.getParameter("t5");
                int salary = 0;
                int eid = 0;
                try {
                        salary = Integer.parseInt(req.getParameter("t4"));

                        eid = Integer.parseInt(req.getParameter("t1"));
                        System.out.println(eid);
                        System.out.println(ename);
                        System.out.println(eaddr);
                        System.out.println(salary);
                        System.out.println(email);

                        HttpSession session=req.getSession();

                        session.setAttribute("name", ename);
                        req.setAttribute("umail", email);
                        ServletContext context = getServletContext();
                        Dao dao = (Dao) context.getAttribute("db");
                        System.out.println("update method call start");
                        dao.updateEmp(eid, ename, eaddr, salary, email);
                        System.out.println("update method call complete");

                        String imgName=dao.getImg(email);
                        session.setAttribute("img", imgName);

                        List<Employee> list = dao.getAllEmployee();
                        req.setAttribute("allEmployee", list);
                        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                        rd.forward(req, res);
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }

        }

}