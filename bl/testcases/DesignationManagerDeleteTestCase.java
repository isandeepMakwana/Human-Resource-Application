import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import java.util.*;
public class DesignationManagerDeleteTestCase
{
public static void main(String gg[])
{
int code = Integer.parseInt(gg[0]);
try
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
designationManager.removeDesignation(code);
System.out.println("designation deleted ");
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