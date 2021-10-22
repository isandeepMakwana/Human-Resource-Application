import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;

public class DesignationTitleExistsTestCase 
{
public static void main(String gg[])
{
String title = gg[0];
try
{
DesignationDTOInterface designationDTO;
DesignationDAOInterface designationDAO = new DesignationDAO();
System.out.println(title+" : "+designationDAO.titleExists(title));
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
	}