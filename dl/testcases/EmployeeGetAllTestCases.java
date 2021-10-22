import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.*;
public class EmployeeGetAllTestCases 
{
public static void main(String gg[])
{
try
{
Set<EmployeeDTOInterface> employees = new TreeSet<>();
EmployeeDAO employeeDAO = new EmployeeDAO();
employees = employeeDAO.getAll();
for(EmployeeDTOInterface employeeDTO: employees)
{
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
}
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}