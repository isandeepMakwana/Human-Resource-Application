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
this.panNumberWiseEmployeesMap.put(employee.getPANNumber().toUpperCase() , employee);
this.aadharCardNumberWiseEmployeesMap.put(employee.getAadharCardNumber().toUpperCase() , employee);
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
if(employee== null)
{
blException.setGenricException("employee is null ");
throw blException;
}
String employeeId = employee.getEmployeeId();
String name = employee.getName();
DesignationInterface designation = employee.getDesignation();
int designationCode =0;
Date dateOfBirth = employee.getDateOfBirth();
char gender = employee.getGender();
boolean isIndian  = employee.getIsIndian();
BigDecimal basicSalary = employee.getBasicSalary();
String panNumber  = employee.getPANNumber();
String aadharCardNumber = employee.getAadharCardNumber();
if(employeeId!=null)
{
employeeId = employeeId.trim();
if(employeeId.length() > 0){
blException.addException("employeeId" , "employeeId is should be Zero");
}
}
if(name == null)
{
blException.addException("name" , "name is null ");
}
else
{
name= name.trim();
if(name.length()==0)
{
blException.addException("name" , "name required !");
}
}
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
if(designation ==null)
{
blException.addException("designation","Designation required ");
}
else
{
designationCode = designation.getCode();
if(designationManager.designationCodeExists(designation.getCode()) == false)
{
blException.addException("designation" , "Invalid designation !");
}
}

if(dateOfBirth== null)
{
blException.addException("dateOfBirth" ,"dateOfBirth is required !  ");
}

if(gender ==' ')
{
blException.addException("gender" , "gender required .");
}

if(basicSalary== null)
{
blException.addException("basicSalary" , "basic Salary required .");
}
else
{
if(basicSalary.signum() == -1)
{
blException.addException("basicSalary" , "Invalid basic Salary");
}
}

if(panNumber == null)
{
blException.addException("panNumber" , "pan Number is null");
}
else
{
panNumber = panNumber.trim();
if(panNumber.length() == 0)
{
blException.addException("panNumber" , "panNumber is required");
}
}


if(aadharCardNumber == null)
{
blException.addException("aadharCardNumber" , "aadhar card Number is null");
}
else
{
aadharCardNumber = aadharCardNumber.trim();
if(aadharCardNumber.length() == 0)
{
blException.addException("aadharCardNumber" , "aadhar Card Number is required");
}
}
if(panNumber!=null && panNumber.length()>0)
{
if(this.panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase()))
{
blException.addException("panNumber" , "pan Number "+panNumber+" already exists .");
}
}
if(aadharCardNumber!=null && aadharCardNumber.length() > 0)
{
if(this.aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase()))
{
blException.addException("aadharCardNumber" , "aadharCardNumber "+aadharCardNumber+"already exists .");
}
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
EmployeeDTOInterface dlEmployee;
dlEmployee = new EmployeeDTO();
dlEmployee.setName(name);
dlEmployee.setDesignationCode(designation.getCode());
dlEmployee.setDateOfBirth(dateOfBirth);
if(gender == 'M')
dlEmployee.setGender(GENDER.MALE);
else 
dlEmployee.setGender(GENDER.FEMALE);
dlEmployee.setIsIndian(isIndian);
dlEmployee.setBasicSalary(basicSalary);
dlEmployee.setPANNumber(panNumber);
dlEmployee.setAadharCardNumber(aadharCardNumber);
employeeDAO.add(dlEmployee);
employee.setEmployeeId(dlEmployee.getEmployeeId());

EmployeeInterface dsEmployee;
dsEmployee = new Employee();
dsEmployee.setEmployeeId(employee.getEmployeeId());
dsEmployee.setName(name);
dsEmployee.setDesignation(((DesignationManager)designationManager).getDSDesignationByCode(designation.getCode()));
dsEmployee.setDateOfBirth((Date)dateOfBirth.clone());
if(gender == 'M')
dsEmployee.setGender(GENDER.MALE);
else 
dsEmployee.setGender(GENDER.FEMALE);
dsEmployee.setIsIndian(isIndian);
dsEmployee.setBasicSalary(basicSalary);
dsEmployee.setPANNumber(panNumber);
dsEmployee.setAadharCardNumber(aadharCardNumber);

this.employeesSet.add(dsEmployee);
this.employeeIdWiseEmployeesMap.put(dsEmployee.getEmployeeId().toUpperCase() , dsEmployee);
this.panNumberWiseEmployeesMap.put(panNumber.toUpperCase() , dsEmployee);
this.aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase() , dsEmployee);
}
catch(DAOException daoException)
{
blException.setGenricException(daoException.getMessage());
throw blException;
}
}
public void updateEmployee(EmployeeInterface employee) throws BLException
{
BLException blException = new BLException();
if(employee== null)
{
blException.setGenricException("employee is null ");
throw blException;
}
String employeeId = employee.getEmployeeId();
String name = employee.getName();
DesignationInterface designation = employee.getDesignation();
int designationCode =designation.getCode();
Date dateOfBirth = employee.getDateOfBirth();
char gender = employee.getGender();
boolean isIndian  = employee.getIsIndian();
BigDecimal basicSalary = employee.getBasicSalary();
String panNumber  = employee.getPANNumber();
String aadharCardNumber = employee.getAadharCardNumber();

if(employeeId==null)
{
blException.addException("employeeId" , "employee Id is null");
throw blException;
}
else
{
employeeId = employeeId.trim();
if(employeeId.length()==0 || this.employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase()) == false)
{
blException.addException("employeeId" , "Invalid EmployeeId .");
throw blException;
}
}
if(name == null)
{
blException.addException("name" , "name is null ");
}
else
{
name= name.trim();
if(name.length()==0)
{
blException.addException("name" , "name required !");
}
}
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
if(designation ==null)
{
blException.addException("designation","Designation required ");
}
else
{
designationCode = designation.getCode();
if(designationManager.designationCodeExists(designation.getCode()) == false)
{
blException.addException("designation" , "Invalid designation !");
}
}

if(dateOfBirth== null)
{
blException.addException("dateOfBirth" ,"dateOfBirth is required !  ");
}

if(gender ==' ')
{
blException.addException("gender" , "gender required .");
}

if(basicSalary== null)
{
blException.addException("basicSalary" , "basic Salary required .");
}
else
{
if(basicSalary.signum() == -1)
{
blException.addException("basicSalary" , "Invalid basic Salary");
}
}

if(panNumber == null)
{
blException.addException("panNumber" , "pan Number is null");
}
else
{
panNumber = panNumber.trim();
if(panNumber.length() == 0)
{
blException.addException("panNumber" , "panNumber is required");
}
}


if(aadharCardNumber == null)
{
blException.addException("aadharCardNumber" , "aadhar card Number is null");
}
else
{
aadharCardNumber = aadharCardNumber.trim();
if(aadharCardNumber.length() == 0)
{
blException.addException("aadharCardNumber" , "aadhar Card Number is required");
}
}

EmployeeInterface ee;
if(panNumber!=null && panNumber.length()>0)
{
ee = this.panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
if(ee!=null && ee.getEmployeeId().equalsIgnoreCase(employeeId) == false)
{
blException.addException("panNumber" , "pan Number "+panNumber+" already exists .");
}
}
if(aadharCardNumber!=null && aadharCardNumber.length() > 0)
{
ee = this.aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
if(ee!=null && ee.getEmployeeId().equalsIgnoreCase(employeeId) == false)
{
blException.addException("aadharCardNumber" , "aadharCardNumber "+aadharCardNumber+"already exists .");
}
}
if(blException.hasExceptions())
{
throw blException;
}
String oldPanNumber;
String oldAadharCardNumber;
EmployeeInterface dsEmployee = this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
oldPanNumber = dsEmployee.getPANNumber();
oldAadharCardNumber = dsEmployee.getAadharCardNumber();

try
{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
EmployeeDTOInterface dlEmployee;
dlEmployee = new EmployeeDTO();
dlEmployee.setEmployeeId(employeeId);
dlEmployee.setName(name);
dlEmployee.setDesignationCode(designation.getCode());
dlEmployee.setDateOfBirth(dateOfBirth);
if(gender == 'M')
dlEmployee.setGender(GENDER.MALE);
else 
dlEmployee.setGender(GENDER.FEMALE);
dlEmployee.setIsIndian(isIndian);
dlEmployee.setBasicSalary(basicSalary);
dlEmployee.setPANNumber(panNumber);
dlEmployee.setAadharCardNumber(aadharCardNumber);
employeeDAO.update(dlEmployee);
dsEmployee = new Employee();
dsEmployee.setEmployeeId(employeeId);
dsEmployee.setName(name);
dsEmployee.setDesignation(((DesignationManager)designationManager).getDSDesignationByCode(designation.getCode()));
dsEmployee.setDateOfBirth((Date)dateOfBirth.clone());
if(gender == 'M')
dsEmployee.setGender(GENDER.MALE);
else 
dsEmployee.setGender(GENDER.FEMALE);
dsEmployee.setIsIndian(isIndian);
dsEmployee.setBasicSalary(basicSalary);
dsEmployee.setPANNumber(panNumber);
dsEmployee.setAadharCardNumber(aadharCardNumber);
 // remove all DS

this.employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase());
this.panNumberWiseEmployeesMap.remove(oldPanNumber.toUpperCase());
this.aadharCardNumberWiseEmployeesMap.remove(oldAadharCardNumber.toUpperCase());
this.employeesSet.remove(dsEmployee);
// add new all DS
this.employeesSet.add(dsEmployee);
this.employeeIdWiseEmployeesMap.put(dsEmployee.getEmployeeId().toUpperCase() , dsEmployee);
this.panNumberWiseEmployeesMap.put(panNumber.toUpperCase() , dsEmployee);
this.aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase() , dsEmployee);
}
catch(DAOException daoException)
{
blException.setGenricException(daoException.getMessage());
throw blException;
}

}
public void removeEmployee(String employeeId) throws BLException
{
if(employeeId==null)
{
BLException blException = new BLException();
blException.addException("employeeId" , "employee Id is null");
throw blException;
}
else
{
employeeId = employeeId.trim();
if(employeeId.length()==0 || this.employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase()) == false)
{
BLException blException = new BLException();
blException.addException("employeeId" , "Invalid EmployeeId .");
throw blException;
}
}
String oldPanNumber;
String oldAadharCardNumber;
try
{
EmployeeInterface dsEmployee = this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
oldPanNumber = dsEmployee.getPANNumber();
oldAadharCardNumber = dsEmployee.getAadharCardNumber();
new EmployeeDAO().delete(employeeId);
 // remove all DS
this.employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase());
this.panNumberWiseEmployeesMap.remove(oldPanNumber.toUpperCase());
this.aadharCardNumberWiseEmployeesMap.remove(oldAadharCardNumber.toUpperCase());
this.employeesSet.remove(dsEmployee);
}
catch(DAOException daoException)
{
BLException blException = new BLException();
blException.setGenricException(daoException.getMessage());
throw blException;
}

}


public EmployeeInterface getEmployeeByEmployeeId(String employeeId) throws BLException
{
EmployeeInterface dsEmployee;
dsEmployee = this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
if(dsEmployee == null)
{
BLException blException = new BLException();
blException.addException("employeeId" , "Invalid EmployeeId .");
throw blException;
}
EmployeeInterface employee;
employee = new Employee();
employee.setEmployeeId(dsEmployee.getEmployeeId());
employee.setName(dsEmployee.getName());
DesignationInterface dsDesignation;
dsDesignation  = dsEmployee.getDesignation();
DesignationInterface designation;
designation = new Designation();
designation.setCode(dsDesignation.getCode());
designation.setTitle(dsDesignation.getTitle());
employee.setDesignation(designation);
employee.setDateOfBirth((Date)dsEmployee.getDateOfBirth().clone());
employee.setIsIndian(dsEmployee.getIsIndian());
employee.setGender((dsEmployee.getGender() =='M')?GENDER.MALE : GENDER.FEMALE);
employee.setBasicSalary(dsEmployee.getBasicSalary());
employee.setPANNumber(dsEmployee.getPANNumber());
employee.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return employee;
}

public EmployeeInterface getEmployeeByPANNumber(String panNumber) throws BLException
{
EmployeeInterface dsEmployee;
dsEmployee = this.panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
if(dsEmployee == null)
{
BLException blException = new BLException();
blException.addException("panNumber" , "Invalid panNumbr "+panNumber);
throw blException;
}
EmployeeInterface employee;
employee = new Employee();
employee.setEmployeeId(dsEmployee.getEmployeeId());
employee.setName(dsEmployee.getName());
DesignationInterface dsDesignation;
dsDesignation  = dsEmployee.getDesignation();
DesignationInterface designation;
designation = new Designation();
designation.setCode(dsDesignation.getCode());
designation.setTitle(dsDesignation.getTitle());
employee.setDesignation(designation);
employee.setDateOfBirth((Date)dsEmployee.getDateOfBirth().clone());
employee.setIsIndian(dsEmployee.getIsIndian());
employee.setGender((dsEmployee.getGender() =='M')?GENDER.MALE : GENDER.FEMALE);
employee.setBasicSalary(dsEmployee.getBasicSalary());
employee.setPANNumber(dsEmployee.getPANNumber());
employee.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return employee;
}

public EmployeeInterface getEmployeeByAadharCardNumber(String aadharCardNumber) throws BLException
{
EmployeeInterface dsEmployee;
dsEmployee = this.aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
if(dsEmployee == null)
{
BLException blException = new BLException();
blException.addException("aadharCardNumber" , "Invalid aadhar card Number "+aadharCardNumber);
throw blException;
}
EmployeeInterface employee;
employee = new Employee();
employee.setEmployeeId(dsEmployee.getEmployeeId());
employee.setName(dsEmployee.getName());
DesignationInterface dsDesignation;
dsDesignation  = dsEmployee.getDesignation();
DesignationInterface designation;
designation = new Designation();
designation.setCode(dsDesignation.getCode());
designation.setTitle(dsDesignation.getTitle());
employee.setDesignation(designation);
employee.setDateOfBirth((Date)dsEmployee.getDateOfBirth().clone());
employee.setIsIndian(dsEmployee.getIsIndian());
employee.setGender((dsEmployee.getGender() =='M')?GENDER.MALE : GENDER.FEMALE);
employee.setBasicSalary(dsEmployee.getBasicSalary());
employee.setPANNumber(dsEmployee.getPANNumber());
employee.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return employee;
}

public boolean employeeIdExists(String employeeId)
{
return employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase());
}
public boolean panNumberExists(String panNumber){
return panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase());
}
public boolean aadharCardNumberExists(String aadharCardNumber) {
return aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase());
}
public int getEmployeeCount()
{
return employeesSet.size();
}
public Set<EmployeeInterface> getEmployees() 
{
Set<EmployeeInterface> employees = new TreeSet<>();
EmployeeInterface employee;
DesignationInterface dsDesignation;
DesignationInterface designation;
for(EmployeeInterface dsEmployee : employeesSet)
{
employee = new Employee();
employee.setEmployeeId(dsEmployee.getEmployeeId());
employee.setName(dsEmployee.getName());
dsDesignation  = dsEmployee.getDesignation();
designation = new Designation();
designation.setCode(dsDesignation.getCode());
designation.setTitle(dsDesignation.getTitle());
employee.setDesignation(designation);
employee.setDateOfBirth((Date)dsEmployee.getDateOfBirth().clone());
employee.setIsIndian(dsEmployee.getIsIndian());
employee.setGender((dsEmployee.getGender() =='M')?GENDER.MALE : GENDER.FEMALE);
employee.setBasicSalary(dsEmployee.getBasicSalary());
employee.setPANNumber(dsEmployee.getPANNumber());
employee.setAadharCardNumber(dsEmployee.getAadharCardNumber());
employees.add(employee);
}
return employees;
}

public Set<EmployeeInterface> getEmployeesByDesignationCode(int designationCode) throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}

public boolean isDesignationAlloted(int designationCode)
{
// not yet implement

return false;
}

public int getEmployeeCountByDesignationCode(int designationCode) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implement");
throw blException;
}
}
