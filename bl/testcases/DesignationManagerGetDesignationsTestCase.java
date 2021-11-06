import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;

public class DesignationManagerGetDesignationsTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
Set<DesignationInterface> designations;
designations = designationManager.getDesignations();
for(DesignationInterface d:designations)
{
System.out.println("DesignationCode  : "+d.getCode());
System.out.println("Title : "+d.getTitle());
}
}
catch(BLException blException)
{
List<String> exceptions;
exceptions = new ArrayList<>();
exceptions = blException.getProperties();
exceptions.forEach((exception)->{
System.out.println(exception);
});
}
}
}
