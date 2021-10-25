import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.text.*;
import java.util.*;
import java.math.*;

public class EmployeeGetByEmployeeIdTestCase
{
public static void main(String gg[])
{
String employeeId = gg[0];
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
EmployeeDTOInterface employeeDTO;
employeeDTO = employeeDAO.getByEmployeeId(employeeId);
System.out.println("Employee Id : " +employeeDTO.getEmployeeId());
System.out.println("Name : "+employeeDTO.getName());
System.out.println("Designation Code : "+employeeDTO.getDesignationCode());
SimpleDateFormat sdf;
sdf= new SimpleDateFormat("dd/MM/yyyy");
System.out.println("DateOfBirth : "+sdf.format(employeeDTO.getDateOfBirth()));
System.out.println("Gender : "+employeeDTO.getGender());
System.out.println("IsIndian : "+employeeDTO.getIsIndian());
System.out.println("Basic Salary : "+employeeDTO.getBasicSalary());
System.out.println("pan Number : "+employeeDTO.getPANNumber());
System.out.println("aadhar Card Number : "+employeeDTO.getAadharCardNumber());
System.out.println("*************************************");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}