
import p1.Employee;
import java.util.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DaoImpl implements Dao {
    // Connection open
    Connection con = null;

    private Connection getConnection() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

        } catch (Exception e) {
        }
        return con;
    }

    // Connection close
    private void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

    // Insertion of Employee
    public boolean storeEmp(String name, String address, int salary, String email, String password, String fileName) {

        boolean flag = false;
        try {

            Connection con = getConnection();
            System.out.println("storeEmp:" + con);
            if (con != null) {
                System.out.println("store emp if");
                String qs = "insert into Employee values(employee_id_seq.NEXTVAL,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(qs);

                ps.setString(1, name);
                ps.setString(2, address);
                ps.setInt(3, salary);
                ps.setString(4, email);
                ps.setString(5, password);
                ps.setString(6, fileName);

                int result = ps.executeUpdate();

                if (result == 1) {
                    flag = true;
                    System.out.println("inserted");
                }

            }

        } catch (Exception e) {
            e.printStackTrace(); // e.getMessage()
        } finally {
            closeConnection(con);
        }
        return flag;

    }

    // Updattion of employee

    // Deletion of employee

    public List<Employee> getAllEmployee() {
        Connection con = null;
        List<Employee> list = new ArrayList<Employee>();
        {
            try {
                con = getConnection();
                String qs = "select eid,ename,eaddr,salary,email from employee";
                PreparedStatement ps = con.prepareStatement(qs);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Employee s = new Employee();
                    s.setId(rs.getInt(1));
                    s.setName(rs.getString(2));
                    s.setAddress(rs.getString(3));
                    s.setSalary(rs.getInt(4));
                    s.setEmail(rs.getString(5));
                    list.add(s);
                }
            } catch (SQLException f) {
                System.out.println(f.getMessage());
            } finally {
                closeConnection(con);
            }
            return list;

        }

    }

    public String generatePassword(String ename, String email) {
        String inputForPassword = ename + email;
        int passwordLength = 8; // Length of the desired password
        StringBuilder password = new StringBuilder();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(inputForPassword.getBytes());

            for (byte b : hash) {
                int value = (b & 0xFF) % 36; // Convert the byte to a value between 0 and 35
                char charToAdd = (value < 10) ? (char) ('0' + value) : (char) ('a' + (value - 10));

                if (password.length() < passwordLength) {
                    password.append(charToAdd);
                }
                if (password.length() >= passwordLength) {
                    break;
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password.toString();

    }

    public String check(String email1, String pass) {

        String uname = null;

        System.out.println("check method");
        try {
            con = getConnection();
            if (con != null) {
                System.out.println("check conn success");
                String qs = "SELECT ENAME FROM EMPLOYEE WHERE EMAIL=? AND PASSWORD=?";
                PreparedStatement ps = con.prepareStatement(qs);
                ps.setString(1, email1);
                ps.setString(2, pass);
                boolean flag = ps.execute();
                if (flag == true) {
                    System.out.println("check if success");
                    ResultSet res = ps.getResultSet();
                    while (res.next()) {
                        uname = res.getString(1);

                    }
                    // System.out.println("check method mail"+mail);
                    // HttpSession session = request.getSession();
                    // session.setAttribute("mail",mail );
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                closeConnection(con);
            } catch (Exception e) {

            }
        }
        return uname;

    }

    public String getImg(String email) {
        System.out.println("GET_IMG EMAIL:" + email);

        Connection con = null;
        String img = null;
        try {
            con = getConnection();
            System.out.println("GetImgCon:" + con);
            if (con != null) {
                if (email != null) {
                    String qs = "SELECT PHOTO_FILENAME FROM EMPLOYEE WHERE EMAIL=?";
                    PreparedStatement ps = con.prepareStatement(qs);
                    ps.setString(1, email);

                    boolean flag = ps.execute();
                    if (flag == true) {
                        System.out.println("GETIMG if success");
                        ResultSet res = ps.getResultSet();
                        while (res.next()) {
                            img = res.getString(1);

                        }
                    }
                }
            }

        } catch (Exception e) {

        } finally {
            try {
                closeConnection(con);
            } catch (Exception e) {

            }
        }
        return img;
    }

    public void updateEmp(int id, String name, String addr, int salary, String email) {
        Connection con = null;

        try {
            con = getConnection();
            System.out.println("Update conn");
            String qs = "update employee set ename=?,eaddr=?,salary=?,email=? where eid=?";
            PreparedStatement ps = con.prepareStatement(qs);

            ps.setString(1, name);
            ps.setString(2, addr);
            ps.setInt(3, salary);
            ps.setString(4, email);
            ps.setInt(5, id);
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("UPDATE Record inserted successfully");

            }
        } catch (Exception e) {
            e.printStackTrace(); // e.getMessage()
        } finally {
            closeConnection(con);
        }
    }

    public void deleteEmp(int id) {

        Connection con = null;

        try {
            con = getConnection();
            String qs = "delete from employee where eid=?";
            PreparedStatement ps = con.prepareStatement(qs);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Record deleted successfully....");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(con);
        }

    }

}
