package com.thinking.machines.hr.dl.interfaces.dto;
import java.util.*;
import java.math.*;
public interface EmployeeDTOInterface extends Comparable<EmployeeDTOInterface> , java.io.Serializable
{
public void setEmployeeId(String employeeId) ;
public String getEmployeeId() ;
public void setName(String name) ;
public String getName() ;
public void setDesignationCode(int designationCode);
public int getDesignationCode();
public void setDateOfBirth(Date date);
public Date getDateOfBirth();
public void setGender(char gender);
public char getGender();
public void setIsIndian(boolean isIndian);
public boolean getIsIndian();
public void setBasicSalary(BigDecimal basicSalary);
public BigDecimal getBasicSalary();
public void setPANNumber(String panNumber);
public String getPANNumber();
public void setAadharCardNumber(String aadharCardNumber);
public String getAadharCardNumber();
}