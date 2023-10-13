
import p1.Employee;

import java.sql.ResultSet;
import java.util.*;



public interface Dao {
    public boolean storeEmp(String name, String address, int salary, String email,String password,String fileName);

    public void updateEmp(int id,String name,String addr,int salary,String email);

    public void deleteEmp(int id);

    public List<Employee> getAllEmployee();
    
    public String generatePassword(String ename,String email);

    public String check(String email1,String pass);

    public String getImg(String email);
    
   
}
