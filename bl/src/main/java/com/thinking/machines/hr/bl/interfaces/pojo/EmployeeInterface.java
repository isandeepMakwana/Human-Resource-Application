package com.thinking.machines.hr.bl.interfaces.pojo;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.enums.*;
import java.util.*;
import java.math.*;
public interface EmployeeInterface extends Comparable<EmployeeInterface> , java.io.Serializable
{
public void setEmployeeId(String employeeId) ;
public String getEmployeeId() ;
public void setName(String name) ;
public String getName() ;
public void setDesignation(DesignationInterface designation);
public DesignationInterface getDesignation();
public void setDateOfBirth(Date date);
public Date getDateOfBirth();
public void setGender(GENDER gender);
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