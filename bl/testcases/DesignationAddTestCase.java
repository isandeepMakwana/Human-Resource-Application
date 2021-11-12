import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import java.util.*;
public class DesignationAddTestCase
{
public static void main(String gg[])
{
String title = "Techer";
try{
DesignationInterface designation;
designation = new Designation();
designation.setTitle(title);
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
designationManager.addDesignation(designation);
System.out.println("Designation add with : "+designation.getCode()+".");
}
catch(BLException blException)
{
System.out.println(blException.getMessage());
List<String> exceptions = blException.getProperties();
for(String exception : exceptions)
{
System.out.println(exception);
}
}

}



}