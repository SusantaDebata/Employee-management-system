import p1.Employee;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;




@MultipartConfig


public class Servlet1 extends HttpServlet {
	
    public static final String UPLOAD_DIR = "images";
	public String dbFileName = "";

    public void init() {
        ServletContext context = getServletContext();
        Dao dao = (Dao) new DaoImpl();
        context.setAttribute("db", dao);
        System.out.println("dao object set....");
    }
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Do get of Servlet1");

        String name = request.getParameter("ename");
        String address = request.getParameter("eaddress");
        Integer salary = Integer.parseInt(request.getParameter("salary"));
        String email = request.getParameter("email");
         Part p=request.getPart("files");
        String fileName=p.getSubmittedFileName();
        System.out.println(fileName);
        String path=getServletContext().getRealPath("")+UPLOAD_DIR;
        System.out.println("File path:"+path);
        File file=new File(path);

        p.write(path+File.separator+fileName);

        dbFileName=UPLOAD_DIR+File.separator+fileName;
        System.out.println("DBFILENAME"+dbFileName);
        



  

      
		
	

        

        
        ServletContext context = getServletContext();
        Dao dao = (Dao) context.getAttribute("db");
        String password = dao.generatePassword(name, email);
        System.out.println(password);
        boolean flag = dao.storeEmp(name, address, salary, email, password,dbFileName);
        if (flag) {
            System.out.println("SUCCESS");
            RequestDispatcher rd = request.getRequestDispatcher("Login.html");
            rd.forward(request, response);

        } else {
            request.setAttribute("status", "FAILED");
            RequestDispatcher rd = request.getRequestDispatcher("Signup.html");
        }
	

	}
    
   
    
}