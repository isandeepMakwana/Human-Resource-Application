import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
public class DesignationGetAllTestCases 
{
public static void main(String gg[])
{
try
{
Set<DesignationDTOInterface> designations;
designations = new TreeSet<>();
DesignationDAOInterface designationDAO = new DesignationDAO();
designations= designationDAO.getAll();

designations.forEach((designationDTO) -> {
System.out.println("code : " +designationDTO.getCode()+" title  : "+designationDTO.getTitle());});
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}