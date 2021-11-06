import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;

public class DesignationManagerDesignationGetByCodeTestCase
{
public static void main(String gg[])
{
int code = Integer.parseInt(gg[0]);
try
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();

DesignationInterface designation;
designation = designationManager.getDesignationByCode(code);

System.out.println("DesignationCode  : "+code);
System.out.println("Title : "+designation.getTitle());
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
