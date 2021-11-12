import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.enums.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import java.util.*;
public class EmployeeManagerEmployeesCountTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager;
try
{
employeeManager = EmployeeManager.getEmployeeManager();
System.out.println("total no. of Employees  : "+employeeManager.getEmployeeCount());
}catch(BLException blException)
{
if(blException.hasGenricException())
{
System.out.println(blException.getMessage());
}
List<String> exceptions;
exceptions = blException.getProperties();
for(String exception : exceptions)
{
System.out.println(exception);
}
}
}
}