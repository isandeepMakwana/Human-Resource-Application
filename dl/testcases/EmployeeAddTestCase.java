import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.text.*;
import java.util.*;
import java.math.*;

public class EmployeeAddTestCase
{
public static void main(String gg[])
{
String name = gg[0];
Date dateOfBirth =null;
int designationCode= Integer.parseInt(gg[1]);
try{
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth = sdf.parse(gg[2]);
}catch(Exception e)
{
System.out.println(e.getMessage());
}
try
{
char gender = gg[3].charAt(0);
boolean isIndian= Boolean.parseBoolean(gg[4]);
BigDecimal basicSalary = new BigDecimal(gg[5]);
String panNumber = gg[6];
String aadharCardNumber = gg[7];
EmployeeDTOInterface employeeDTO;
employeeDTO = new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

EmployeeDAOInterface employeeDAO;
employeeDAO  = new EmployeeDAO();
employeeDAO.add(employeeDTO);
System.out.println("Employee added");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}