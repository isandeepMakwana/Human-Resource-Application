import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import java.util.*;
public class DesignationManagerUpdateTestCase
{
public static void main(String gg[])
{
try
{
DesignationInterface designation;
designation = new Designation();
designation.setCode(1);
designation.setTitle("watchMan");
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
designationManager.updateDesignation(designation);
System.out.println("designation Updated !");
}
catch(BLException blException)
{
System.out.println(blException.getMessage());
if(blException.hasGenricException())
System.out.println(blException.getGenricException());
List<String> exceptions = blException.getProperties();
for(String exception : exceptions)
{
System.out.println(exception);
}
}
}
}