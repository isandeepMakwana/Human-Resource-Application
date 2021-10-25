package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.enums.*;
import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;
public class EmployeeDAO implements EmployeeDAOInterface 
{
private final static String FILE_NAME  = "employees.data";
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("employeeDTO is null");
String employeeId;
String name = employeeDTO.getName();
if(name == null)
{
throw new DAOException("name is null");
}
if(name.length()==0)
{
throw new DAOException("name length is Zero !");
}
int designationCode = employeeDTO.getDesignationCode();
if(designationCode <0) throw new DAOException("Invalid Code : " +designationCode);
boolean isDesignationCodeValid = false;
DesignationDAO designationDAO = new DesignationDAO();
isDesignationCodeValid = designationDAO.codeExists(designationCode);
if(!isDesignationCodeValid)
{
throw new DAOException("Invalid Code : "+designationCode);
}
Date dateOfBirth = employeeDTO.getDateOfBirth();
if(dateOfBirth == null)
{
throw new DAOException("Date is null ");
}
char gender = employeeDTO.getGender();
boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary = employeeDTO.getBasicSalary();
if(basicSalary ==null)
{
throw new DAOException("basic Salary is null");
}
if(basicSalary.signum() == -1)
{
throw new DAOException("basic Salary is negative");
}
String panNumber = employeeDTO.getPANNumber();
if(panNumber == null)
{
throw new DAOException("panNumber is null ");
}
if(panNumber.length() == 0)
{
throw new DAOException("panNumber length is Zero !");
}
String aadharCardNumber = employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null)
{
throw new DAOException("aadhar card number is null");
}
if(aadharCardNumber.length()==0)
{
throw new DAOException("aadhar card number length is Zero !");
}
try
{
File file = new File(FILE_NAME);
RandomAccessFile randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile == null)
{
throw new DAOException("file is null");
}
int lastGenreatedEmployeeId =10000000; 
int recordCount = 0;
String lastGenreatedEmployeeIdString ="";
String recordCountString = "";
if(randomAccessFile.length() == 0)
{
lastGenreatedEmployeeIdString = String.format("%-10s" , "10000000");
randomAccessFile.writeBytes(lastGenreatedEmployeeIdString+"\n");
recordCountString = String.format("%-10s" ,"0");
randomAccessFile.writeBytes(recordCountString+"\n");
}
else
{
lastGenreatedEmployeeId = Integer.parseInt(randomAccessFile.readLine().trim());
recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
}
boolean panNumberExists = false; 
boolean aadharCardNumberExists = false;
String fPanNumber , fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int i=0;i<7;i++)
{
randomAccessFile.readLine();
}
fPanNumber = randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(panNumberExists==false && panNumber.equalsIgnoreCase(fPanNumber))
{
panNumberExists = true;
}
if(aadharCardNumberExists == false && aadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
aadharCardNumberExists =true;
}
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("pan Number  : "+panNumber+" and AadharCard Number : "+aadharCardNumber+" exists !");
}
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("pan Number  : "+panNumber+" exists !");
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("AadharCard Number : "+aadharCardNumber+" exists !");
}
lastGenreatedEmployeeId++;
employeeId = "A"+lastGenreatedEmployeeId;
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");

randomAccessFile.seek(0);
lastGenreatedEmployeeIdString = String.format("%-10d" , lastGenreatedEmployeeId);
recordCount++;
recordCountString = String.format("%-10d" , recordCount);
randomAccessFile.writeBytes(lastGenreatedEmployeeIdString+"\n");
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
throw new DAOException("NOT yet implemented");
}
public void delete(String employeeId) throws DAOException
{
throw new DAOException("NOT yet implemented");
}
public Set<EmployeeDTOInterface> getAll() throws DAOException
{

Set<EmployeeDTOInterface> employees = new TreeSet<>();
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return employees;
RandomAccessFile  randomAccessFile= new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0) 
{
randomAccessFile.close();
return employees;
}
EmployeeDTOInterface employeeDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
char fGender;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(randomAccessFile.readLine());
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}
catch(ParseException pe)
{
throw new DAOException(pe.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
employeeDTO.setGender((fGender=='M')?GENDER.MALE : GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException
{

DesignationDAOInterface designationDAO = new DesignationDAO();
if(designationDAO.codeExists(designationCode) == false) throw new DAOException("Invalid Code : "+designationCode);
Set<EmployeeDTOInterface> employees = new TreeSet<>();
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return employees;
RandomAccessFile  randomAccessFile= new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
return employees;
}
EmployeeDTOInterface employeeDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
String employeeId;
String name;
char fGender;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
employeeId = randomAccessFile.readLine();
name = randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode == designationCode)
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(fDesignationCode);
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}
catch(ParseException pe)
{
throw new DAOException(pe.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
employeeDTO.setGender((fGender=='M')?GENDER.MALE : GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
else
{
for(int i=0; i<6; i++) randomAccessFile.readLine();
}
}
randomAccessFile.close();
return employees;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean isDesignationAlloted(int designationCode) throws DAOException
{
DesignationDAOInterface designationDAO = new DesignationDAO();
if(designationDAO.codeExists(designationCode) == false) throw new DAOException("Invalid Code : "+designationCode);
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile  randomAccessFile= new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0) 
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode=0;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode == designationCode)
{
randomAccessFile.close();
return true;
}
else
{
for(int i=0; i<6; i++) randomAccessFile.readLine();
}
}
randomAccessFile.close();
return false;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
if(employeeId == null) throw new DAOException("Invalid employeeId : "+employeeId);
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException("Invalid employeeId : "+employeeId);
RandomAccessFile randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 )
{
randomAccessFile.close();
throw new DAOException("file length is Zero !");
}
String fEmployeeId;
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
EmployeeDTO employeeDTO;
char fGender;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine().trim()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}
catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
employeeDTO.setGender((fGender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
randomAccessFile.close();
return employeeDTO;
}
else
{
for(int i=0; i<8;i++) randomAccessFile.readLine();
}
}
randomAccessFile.close();
throw new DAOException("Invalid employee Id : "+employeeId);
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
if(panNumber == null) throw new DAOException("Invalid pan Number : "+panNumber);
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException("Invalid pan Number : "+panNumber);
RandomAccessFile randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 ){
randomAccessFile.close();
throw new DAOException("Invalid pan Number : " +panNumber);
}
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPanNumber;
String fAadharCardNumber;
EmployeeDTOInterface employeeDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
try
{
fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
}
catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
fBasicSalary = new BigDecimal(randomAccessFile.readLine());
fPanNumber = randomAccessFile.readLine();
fAadharCardNumber =randomAccessFile.readLine();
if(fPanNumber.equalsIgnoreCase(panNumber))
{
employeeDTO  = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setGender((fGender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setPANNumber(fPanNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid pan Number : "+panNumber);
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber == null) throw new DAOException("Invalid aadharCard Number : "+aadharCardNumber);
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException("Invalid aadharCard Number : "+aadharCardNumber);
RandomAccessFile randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 ){
randomAccessFile.close();
throw new DAOException("Invalid aadharCard Number : " +aadharCardNumber);
}
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPanNumber;
String fAadharCardNumber;
EmployeeDTOInterface employeeDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
try
{
fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
}
catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
fBasicSalary = new BigDecimal(randomAccessFile.readLine());
fPanNumber = randomAccessFile.readLine();
fAadharCardNumber =randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
employeeDTO  = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setGender((fGender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setPANNumber(fPanNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid aadharCard Number : "+aadharCardNumber);
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean employeeIdExists(String employeeId) throws DAOException
{
if(employeeId == null) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 )
{
randomAccessFile.close();
return false;
}
String fEmployeeId;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
randomAccessFile.close();
return true;
}
else
{
for(int i=0; i<8; i++) randomAccessFile.readLine();
}
}
randomAccessFile.close();
return false;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean panNumberExists(String panNumber) throws DAOException
{
if(panNumber == null) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 ){
randomAccessFile.close();
return false;
}
String fPanNumber;
String fAadharCardNumber;
EmployeeDTOInterface employeeDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
for(int i=0; i<7; i++) randomAccessFile.readLine();
fPanNumber = randomAccessFile.readLine();
fAadharCardNumber =randomAccessFile.readLine();
if(fPanNumber.equalsIgnoreCase(panNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber == null) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 ){
randomAccessFile.close();
return false;
}
String fAadharCardNumber;
EmployeeDTOInterface employeeDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
for(int i=0; i<8; i++) randomAccessFile.readLine();
fAadharCardNumber =randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public int getCount() throws DAOException
{
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return 0;
RandomAccessFile randomAccessFile= new RandomAccessFile(file , "rw");
if(randomAccessFile.length() ==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount; 
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public int getCountByDesignationCode(int designationCode) throws DAOException
{
DesignationDAOInterface designationDAO = new DesignationDAO();
if(designationDAO.codeExists(designationCode) == false) throw new DAOException("Invalid Code : "+designationCode);
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException("invalid desingnationCode : "+designationCode);
RandomAccessFile  randomAccessFile= new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
int count=0;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode == designationCode)
{
count++;
}
for(int i=0; i<6;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
return count;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}