import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;

public class EmployeeUpdateTestCase
{
public static void main(String gg[])
{
String employeeId = gg[0];
String name = gg[1];
Date dateOfBirth =null;
int designationCode= Integer.parseInt(gg[2]);
try{
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth = sdf.parse(gg[3]);
}catch(ParseException pe)
{
System.out.println(pe.getMessage());
}
try
{
char gender = gg[4].charAt(0);
boolean isIndian= Boolean.parseBoolean(gg[5]);
BigDecimal basicSalary = new BigDecimal(gg[6]);
String panNumber = gg[7];
String aadharCardNumber = gg[8];
EmployeeDTOInterface employeeDTO;
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
if(gender == 'M')
{
employeeDTO.setGender(GENDER.MALE);
}
else
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

EmployeeDAOInterface employeeDAO;
employeeDAO  = new EmployeeDAO();
employeeDAO.update(employeeDTO);
System.out.println("Employee updated");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}