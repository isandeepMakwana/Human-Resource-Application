import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;

public class DesignationManagerDesignationCodeExistsTestCase
{
public static void main(String gg[])
{
int code = Integer.parseInt(gg[0]);
try
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
System.out.println("Designation code is exists :"+designationManager.designationCodeExists(code));
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
