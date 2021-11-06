import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;

public class DesignationManagerDesignationGetByTitleTestCase
{
public static void main(String gg[])
{
String title = gg[0];
try
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
DesignationInterface designation;
designation = designationManager.getDesignationByTitle(title);

System.out.println("Designation code : "+ designation.getCode());
System.out.println("title : "+title);
}
catch(BLException blException)
{
List<String> exceptions = new ArrayList<>();
exceptions = blException.getProperties();
exceptions.forEach((exception)->{
System.out.println(exception);
});
}
}


}