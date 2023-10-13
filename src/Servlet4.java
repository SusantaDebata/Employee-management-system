import p1.Employee;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Servlet4 extends HttpServlet {
    public void init() {
        System.out.println("Delete servlet");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("idss"));
            System.out.println("idss complete");
            ServletContext context = getServletContext();
            Dao dao = (Dao) context.getAttribute("db");
            dao.deleteEmp(id);
            System.out.println("delete com");
            List<Employee> list = dao.getAllEmployee();
            if (list != null) {
                req.setAttribute("allEmployee", list);
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, res);
            }
        } catch (Exception e) {
        }
    }
}
