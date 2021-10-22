import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;

public class DesignationGetByTitleTestCase 
{
public static void main(String gg[])
{
String title = gg[0];
try
{
DesignationDTOInterface designationDTO;
DesignationDAOInterface designationDAO = new DesignationDAO();
designationDTO = designationDAO.getByTitle(title);
System.out.println("code : " +designationDTO.getCode()+" title  : "+designationDTO.getTitle());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
	}