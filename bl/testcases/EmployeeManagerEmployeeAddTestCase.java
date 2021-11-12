import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.enums.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerEmployeeAddTestCase
{
public static void main(String gg[])
{
EmployeeInterface employee;
try
{
employee = new Employee();
employee.setName("vikas thakur");
employee.setDateOfBirth(new Date());
DesignationInterface designation = new Designation();
designation.setCode(2);
employee.setDesignation(designation);
employee.setGender(GENDER.MALE);
employee.setIsIndian(true);
employee.setBasicSalary(new BigDecimal(100000));
employee.setPANNumber("IND12845");
employee.setAadharCardNumber("UID83245");
EmployeeManager.getEmployeeManager().addEmployee(employee);
System.out.println("Employee added with employeeId "+employee.getEmployeeId()+" .");
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