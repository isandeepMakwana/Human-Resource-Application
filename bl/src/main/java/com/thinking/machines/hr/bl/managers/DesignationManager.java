package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
public class DesignationManager implements DesignationManagerInterface
{
private Map<Integer , DesignationInterface> codeWiseDesignationMap;
private Map<String , DesignationInterface> titleWiseDesignationMap;
private Set<DesignationInterface> designationsSet;
private static DesignationManager designationManager = null;
private DesignationManager() throws BLException
{
populateDataStructures();
}

private void populateDataStructures() throws BLException
{
this.codeWiseDesignationMap = new HashMap<>();
this.titleWiseDesignationMap = new HashMap<>();
this.designationsSet = new TreeSet<>();
try
{
Set<DesignationDTOInterface> dlDesignations;
dlDesignations = new DesignationDAO().getAll();
DesignationInterface designation;
for(DesignationDTOInterface dlDesignation : dlDesignations)
{
designation = new Designation();
designation.setCode(dlDesignation.getCode());
designation.setTitle(dlDesignation.getTitle());
this.codeWiseDesignationMap.put(designation.getCode() , designation);
this.titleWiseDesignationMap.put(designation.getTitle().toUpperCase() , designation);
this.designationsSet.add(designation);
}
}
catch(DAOException daoException)
{
BLException blException = new BLException();
blException.setGenricException(daoException.getMessage());
throw blException;
}
}
public static DesignationManagerInterface getDesignationManager() throws BLException
{
if(designationManager == null) 
{
designationManager = new DesignationManager();
}
return designationManager;
}
public void addDesignation(DesignationInterface  designation) throws BLException
{
BLException blException = new BLException();
if(designation == null)
{
blException.setGenricException("designation is  null ");
throw blException;
}
int code  = designation.getCode();
String title = designation.getTitle();

if(code!=0)
{
blException.addException("code" , "code is should be a Zero");
}
if(title==null)
{
blException.addException("title" , "title is null");
title="";
}
else
{
title = title.trim();
if(title.length()==0)
{
blException.addException("title" , "title required !" );
}
}
if(this.titleWiseDesignationMap.containsKey(title.toUpperCase()))
{
blException.addException("title" , "Designation : "+title+" exists .");
}
if(blException.hasExceptions())
{
throw blException;
}

try
{
DesignationDTO designationDTO = new DesignationDTO();
DesignationDAO designationDAO = new DesignationDAO();
designationDTO.setTitle(title);
designationDAO.add(designationDTO);
code = designationDTO.getCode();
DesignationInterface dsDesignation = new Designation();
dsDesignation.setCode(code);
dsDesignation.setTitle(title);
this.codeWiseDesignationMap.put(code , dsDesignation);
this.titleWiseDesignationMap.put(title.toUpperCase() , dsDesignation);
this.designationsSet.add(dsDesignation);
designation.setCode(code);
}
catch(DAOException daoException)
{
blException.setGenricException(daoException.getMessage());
}
}

public void updateDesignation(DesignationInterface designation) throws BLException
{
BLException blException;
blException = new BLException();
if(designation == null)
{
blException.setGenricException("designation is  null ");
throw blException;
}
int code  = designation.getCode();
String title = designation.getTitle();

if(code<=0)
{
blException.addException("code" , "Invalid code : "+code);
throw blException;
}
if(code > 0)
{
if(this.codeWiseDesignationMap.containsKey(code)==false)
{
blException.addException("code" , "Invalid code : "+code);
throw blException;
}
}
if(title==null)
{
blException.addException("title" , "title is null");
title="";
}
else
{
title = title.trim();
if(title.length()==0)
{
blException.addException("title" , "title required !" );
}
}
if(title.length()>0)
{
DesignationInterface d;
d = titleWiseDesignationMap.get(title.toUpperCase());
if(d!=null && d.getCode() !=code)
{
blException.addException("title" , "Designation : "+title +" already exists .");
throw blException;
}
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
DesignationInterface dsDesignation;
dsDesignation = this.codeWiseDesignationMap.get(code);
DesignationDTOInterface dlDesignation = new DesignationDTO();
dlDesignation.setCode(code);
dlDesignation.setTitle(title);
new DesignationDAO().update(dlDesignation);
// remove the one from all ds
this.codeWiseDesignationMap.remove(code);
this.titleWiseDesignationMap.remove(dsDesignation.getTitle().toUpperCase());
this.designationsSet.remove(dsDesignation);
// update object 
dsDesignation.setTitle(title);
// update DS 
this.codeWiseDesignationMap.put(code , dsDesignation);
this.titleWiseDesignationMap.put(title.toUpperCase() , dsDesignation);
this.designationsSet.add(dsDesignation);
}
catch(DAOException daoException)
{
blException.setGenricException(daoException.getMessage());
throw blException;
}
}
public void removeDesignation(int code) throws BLException
{
BLException blException;
blException = new BLException();
if(code<=0)
{
blException.addException("code" , "Invalid code : "+code);
throw blException;
}
if(this.codeWiseDesignationMap.containsKey(code)==false)
{
blException.addException("code" , "Invalid code : "+code);
throw blException;
}
try
{
DesignationInterface dsDesignation;
dsDesignation = this.codeWiseDesignationMap.get(code);
new DesignationDAO().delete(code);
// remove the one from all ds
this.codeWiseDesignationMap.remove(code);
this.titleWiseDesignationMap.remove(dsDesignation.getTitle().toUpperCase());
this.designationsSet.remove(dsDesignation);
}
catch(DAOException daoException)
{
blException.setGenricException(daoException.getMessage());
throw blException;
}
}
public DesignationInterface getDesignationByCode(int code) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implemented");
throw blException;
}
public DesignationInterface getDesignationByTitle(String title) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implemented");
throw blException;
}
public boolean designationCodeExists(int code) throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implemented");
throw blException;
}
public boolean designationTitleExists(String title) throws BLException{
BLException blException = new BLException();
blException.setGenricException("not yet implemented");
throw blException;
}
public int getDesignationCount() throws BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implemented");
throw blException;
}
public Set<DesignationInterface> getDesignations() throws  BLException
{
BLException blException = new BLException();
blException.setGenricException("not yet implemented");
throw blException;
}
}