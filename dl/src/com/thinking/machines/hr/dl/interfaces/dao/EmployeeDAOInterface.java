package com.thinking.machines.hr.dl.interfaces.dao;
import java.util.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
public interface EmployeeDAOInterface 
{
public void add(EmployeeDTOInterface employeeDTO) throws DAOException;
public void update(EmployeeDTOInterface employeeDTO) throws DAOException;
public void delete(String employeeId) throws DAOException;
public Set<EmployeeDTOInterface> getAll() throws DAOException;
public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException;
public boolean isDesignationAlloted(int designationCode) throws DAOException;
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException;
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException;
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException;

public boolean employeeIdExists(String employeeId) throws DAOException;
public boolean panNumberExists(String panNumber) throws DAOException;
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException;
public int getCount() throws DAOException;
public int getCountByDesignationCode(int designationCode) throws DAOException;
}
