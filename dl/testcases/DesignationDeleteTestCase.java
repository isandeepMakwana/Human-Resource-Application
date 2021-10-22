import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;

public class DesignationDeleteTestCase 
{
public static void main(String gg[])
{
int code  = Integer.parseInt(gg[0]);
try
{
DesignationDAOInterface designationDAO = new DesignationDAO();
designationDAO.delete(code);
System.out.println("Designation deleted !");
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}