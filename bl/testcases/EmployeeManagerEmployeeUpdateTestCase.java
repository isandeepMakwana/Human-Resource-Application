import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.enums.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerEmployeeUpdateTestCase
{
public static void main(String gg[])
{
EmployeeInterface employee;
try
{
employee = new Employee();
employee.setEmployeeId("A10000003");
employee.setName("Samer Gupta");
employee.setDateOfBirth(new Date());
DesignationInterface designation = new Designation();
designation.setCode(3);
employee.setDesignation(designation);
employee.setGender(GENDER.MALE);
employee.setIsIndian(true);
employee.setBasicSalary(new BigDecimal(350000));
employee.setPANNumber("IND54321");
employee.setAadharCardNumber("UID12345");
EmployeeManager.getEmployeeManager().updateEmployee(employee);
System.out.println("Employee updated with employeeId "+employee.getEmployeeId()+" .");
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