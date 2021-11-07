package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.enums.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeManager implements EmployeeManagerInterface
{
private Map<String , EmployeeInterface> employeeIdWiseEmployeesMap;
private Map<String , EmployeeInterface> panNumberWiseEmployeesMap;
private Map<String , EmployeeInterface> aadharCardNumberWiseEmployeesMap;
private Set<EmployeeInterface> employeesSet;

private static EmployeeManagerInterface employeeManager =null;
private EmployeeManager() throws BLException
{
populateDataStructures();
}

private void populateDataStructures() throws BLException
{
this.employeeIdWiseEmployeesMap = new HashMap<>();
this.panNumberWiseEmployeesMap = new HashMap<>();
this.aadharCardNumberWiseEmployeesMap = new HashMap<>();
this.employeesSet = new TreeSet<>();
try
{
Set<EmployeeDTOInterface> dlEmployees;
dlEmployees  = new EmployeeDAO().getAll();
EmployeeInterface employee;
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
DesignationInterface designation;
for(EmployeeDTOInterface dlEmployee : dlEmployees)
{
employee = new Employee();
employee.setEmployeeId(dlEmployee.getEmployeeId());
employee.setName(dlEmployee.getName());
designation = designationManager.getDesignationByCode(dlEmployee.getDesignationCode());
employee.setDesignation(designation);
employee.setDateOfBirth(dlEmployee.getDateOfBirth());
if(dlEmployee.getGender() == 'M')
{
employee.setGender(GENDER.MALE);
}
else
{
employee.setGender(GENDER.FEMALE);
}
employee.setIsIndian(dlEmployee.getIsIndian());
employee.setBasicSalary(dlEmployee.getBasicSalary());
employee.setPANNumber(dlEmployee.getPANNumber());
employee.setAadharCardNumber(dlEmployee.getAadharCardNumber());

this.employeeIdWiseEmployeesMap.put(employee.getEmployeeId().toUpperCase() , employee);
this.employeeIdWiseEmployeesMap.put(employee.getPANNumber().toUpperCase() , employee);
this.employeeIdWiseEmployeesMap.put(employee.getAadharCardNumber().toUpperCase() , employee);
this.employeesSet.add(employee);
}
}
catch(DAOException  daoException)
{
BLException blException;
blException = new BLException();
blException.setGenricException(daoException.getMessage());
throw blException;
}
}

public static EmployeeManagerInterface getEmployeeManager() throws BLException
{
if(employeeManager == null) employeeManager= new EmployeeManager();
return employeeManager;
}

public void addEmployee(EmployeeInterface employee) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}
public void updateEmployee(EmployeeInterface employee) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}
public void removeEmployee(String employeeId) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}


public EmployeeInterface getEmployeeByEmployeeId(String employeeId) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public EmployeeInterface getEmployeeByPANNumber(String panNumber) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public EmployeeInterface getEmployeeByAadharCardNumber(String aadharCardNumber) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}


public boolean employeeIdExists(String employeeId) throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public boolean panNumberExists(String panNumber) throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public boolean aadharCardNumberExists(String aadharCardNumber) throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public int getEmployeeCount() throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}


public Set<EmployeeInterface> getEmployees() throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public Set<EmployeeInterface> getEmployeesByDesignationCode(int designationCode) throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public boolean isDesignationAlloted(int designationCode) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public int getEmployeeCountByDesignationCode(int designationCode) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

}
