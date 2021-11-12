import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.enums.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerGetEmployeesByDesignationCodeTestCase
{
public static void main(String gg[])
{
int designationCode = Integer.parseInt(gg[0].trim());
EmployeeManagerInterface employeeManager;
Set<EmployeeInterface> employees;
try
{
employeeManager = EmployeeManager.getEmployeeManager();
employees = employeeManager.getEmployeesByDesignationCode(designationCode);
employees.forEach((employee)->{
System.out.print("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Date Of Birth : "+employee.getDateOfBirth());
DesignationInterface designation = employee.getDesignation();
System.out.println("designation code : "+designation.getCode());
System.out.println("designation : "+designation.getTitle());
System.out.println("Gender : "+employee.getGender());
System.out.println("IsIndian : "+employee.getIsIndian());
System.out.println("Basic Salary : "+employee.getBasicSalary());
System.out.println("Pan Number : "+employee.getPANNumber());
System.out.println("Aadhar card Number : "+employee.getAadharCardNumber());
System.out.println("_______________________________________________ ");
});
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