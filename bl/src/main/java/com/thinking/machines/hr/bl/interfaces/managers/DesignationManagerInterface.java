package com.thinking.machines.hr.bl.interfaces.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;

public interface DesignationManagerInterface
{

public void addDesignation(DesignationInterface  designation) throws BLException;
public void updateDesignation(DesignationInterface designation) throws BLException;
public void removeDesignation(int code) throws BLException;

public DesignationInterface getDesignationByCode(int code) throws BLException;
public DesignationInterface getDesignationByTitle(String title) throws BLException;

public boolean designationCodeExists(int code);
public boolean designationTitleExists(String title);

public int getDesignationCount();
public Set<DesignationInterface> getDesignations();
}