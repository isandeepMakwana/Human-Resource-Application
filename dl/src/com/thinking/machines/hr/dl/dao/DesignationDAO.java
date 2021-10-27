package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.io.*;

public class DesignationDAO implements DesignationDAOInterface
{
private final static String FILE_NAME  = "designation.data";
public void add(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO == null) throw new DAOException("Designation is null");
String title; 
title = designationDTO.getTitle();
if(title == null) throw new DAOException("Title is null");
title =title.trim();
if(title.length() ==0)
{
throw new DAOException("title is not valid please enter a valid title !");
}
try
{
File file  = new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file ,"rw");

String lastGenreatedCodeString ="";
String recordCountString = "";

int lastGenreatedCode  = 0;
int recordCount = 0;

if(randomAccessFile.length() ==0)
{
lastGenreatedCodeString = String.valueOf(lastGenreatedCode);
while(lastGenreatedCodeString.length()<10) lastGenreatedCodeString+=" ";
recordCountString = String.valueOf(recordCount);
while(recordCountString.length()<10) recordCountString+=" ";
randomAccessFile.writeBytes(lastGenreatedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
}
else
{
lastGenreatedCode  = Integer.parseInt(randomAccessFile.readLine().trim());
recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine().trim());
fTitle = randomAccessFile.readLine().trim();
if(title.equalsIgnoreCase(fTitle))
{
throw new DAOException( "Designation "+title +" already Exists ");
}
}
}

int code = lastGenreatedCode+1;
randomAccessFile.writeBytes(String.valueOf(code));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(title);
randomAccessFile.writeBytes("\n");

randomAccessFile.seek(0);
designationDTO.setCode(code);
lastGenreatedCode++;
recordCount++;
lastGenreatedCodeString = String.valueOf(lastGenreatedCode);
while(lastGenreatedCodeString.length()<10) lastGenreatedCodeString+=" ";
recordCountString = String.valueOf(recordCount);
while(recordCountString.length()<10) recordCountString+=" "; 
randomAccessFile.writeBytes(lastGenreatedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");

randomAccessFile.close();
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public void update(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO == null) throw new DAOException("Designation is null");
int code;
code = designationDTO.getCode();
if(code <= 0) 
throw new DAOException("Invalid Code : "+ code);
String title; 
title = designationDTO.getTitle();
if(title == null) throw new DAOException("title is null");
title =title.trim();
if(title.length() ==0 || title.length() == 2)
{
throw new DAOException("title is not valid please enter a valid title !");
}
try
{
File file  = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException("Invalid code "+code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 || randomAccessFile.length()==2)
{
randomAccessFile.close();
throw new DAOException("Invalid Code : "+ code);
}

int fCode;
String fTitle;
randomAccessFile.readLine();
randomAccessFile.readLine();
boolean found = false;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine().trim());
if(fCode == code)
{
found =true;
}
randomAccessFile.readLine();
}
if(found ==false) {
randomAccessFile.close();
throw new DAOException("Invalid Code "+ code);
}

randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine().trim());
fTitle = randomAccessFile.readLine();
if(fCode!=code && fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException("title : "+title +" already exists");
}
}
File tmpfile = new File("tmp.data");
if(tmpfile.exists()) tmpfile.delete();
RandomAccessFile tmpRandomAccessFile = new RandomAccessFile(tmpfile , "rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fCode  = Integer.parseInt(randomAccessFile.readLine().trim());
fTitle = randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(code));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(title);
tmpRandomAccessFile.writeBytes("\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(int code) throws DAOException
{
if(code <= 0) throw new DAOException("Invalid Code : "+ code);
try
{
File file  = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException("Invalid code "+code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length() ==0 || randomAccessFile.length()==2)
{
randomAccessFile.close();
throw new DAOException("Invalid Code : "+ code);
}

int fCode;
String fTitle="";
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
boolean found = false;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine().trim());
fTitle = randomAccessFile.readLine();
if(fCode == code)
{
found =true;
}
}
if(found ==false) {
randomAccessFile.close();
throw new DAOException("Invalid Code "+ code);
}
if(new EmployeeDAO().isDesignationAlloted(code))
{
randomAccessFile.close();
throw new DAOException("Designation ( "+fTitle+" ) can not be delete , becouse it's allocated to employee . ");
}
File tmpfile = new File("tmp.data");
if(tmpfile.exists()) tmpfile.delete();
RandomAccessFile tmpRandomAccessFile = new RandomAccessFile(tmpfile , "rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fCode  = Integer.parseInt(randomAccessFile.readLine().trim());
fTitle = randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
recordCount = Integer.parseInt(tmpRandomAccessFile.readLine().trim());
String recordCountString = String.valueOf(recordCount-1);
while(recordCountString.length()<10) recordCountString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");

while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public Set<DesignationDTOInterface> getAll() throws DAOException
{
Set<DesignationDTOInterface> designations = new TreeSet<>();
try
{
File file = new File(FILE_NAME);
if(!file.exists()) return designations;
RandomAccessFile randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length()==0 || randomAccessFile.length() == 2)
{
randomAccessFile.close();
return designations;
}
DesignationDTOInterface designationDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
designationDTO = new DesignationDTO();
designationDTO.setCode(Integer.parseInt(randomAccessFile.readLine().trim()));
designationDTO.setTitle(randomAccessFile.readLine());
designations.add(designationDTO);
}
randomAccessFile.close();
return designations;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public DesignationDTOInterface getByCode(int code) throws DAOException
{
if(code<1) throw new DAOException("Invalid Code : "+ code);
try
{
File file  = new File(FILE_NAME);
if(file.exists() == false)  throw new DAOException("Invalid Code : "+ code);
RandomAccessFile randomAccessFile  = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0 || randomAccessFile.length() ==2)
{
randomAccessFile.close();
throw new DAOException("Invalid Code : "+ code);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
int fTitle;
while(randomAccessFile.getFilePointer()< randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine().trim());
if(fCode == code)
{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(randomAccessFile.readLine().trim());
randomAccessFile.close();
return designationDTO;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
throw new DAOException("Invalid Code : "+code);
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public DesignationDTOInterface getByTitle(String title) throws DAOException
{
if(title == null) throw new DAOException("title is NULL " );
if(title.trim().length() == 0) throw new DAOException("title length is Zero ");
try
{
File file  = new File(FILE_NAME);
if(file.exists() == false)  throw new DAOException(title +" NOT Found !");
RandomAccessFile randomAccessFile  = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0 || randomAccessFile.length() ==2)
{
randomAccessFile.close();
throw new DAOException("NO Records Avilable ");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()< randomAccessFile.length())
{
fCode =Integer.parseInt(randomAccessFile.readLine().trim());
fTitle = randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setCode(fCode);
designationDTO.setTitle(fTitle);
randomAccessFile.close();
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException(title +" Not Found !");
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean codeExists(int code) throws DAOException
{
if(code<1) return false;
try
{
File file  = new File(FILE_NAME);
if(file.exists() == false)  return false;
RandomAccessFile randomAccessFile  = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0 || randomAccessFile.length() ==2)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
while(randomAccessFile.getFilePointer()< randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine().trim());
if(fCode == code)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean titleExists(String title) throws DAOException
{
if(title == null || title.trim().length() ==0) return false;
try
{
File file  = new File(FILE_NAME);
if(file.exists() == false)  return false;
RandomAccessFile randomAccessFile  = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0 || randomAccessFile.length() ==2)
{
randomAccessFile.close();
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fTitle;
while(randomAccessFile.getFilePointer()< randomAccessFile.length())
{
randomAccessFile.readLine();
fTitle = randomAccessFile.readLine().trim();
if(fTitle.equalsIgnoreCase(title))
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
File file  = new File(FILE_NAME);
if(file.exists() == false)
{
return 0;
}
RandomAccessFile randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() ==0 || randomAccessFile.length() ==2)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int recordCount;
recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}