import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
public class EmployeeIdExistsTestCase
{
public static void main(String gg[])
{
String employeeId = gg[0];
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
System.out.println("employeeId is "+employeeId+" is exists : "+employeeDAO.employeeIdExists(employeeId));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}