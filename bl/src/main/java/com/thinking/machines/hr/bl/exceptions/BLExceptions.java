package com.thinking.machines.hr.bl.exceptions;
import java.util.*;

public class BLexception extends Exception
{
private Map<String , String> exceptions;
private String genricException;
public BLException()
{
genricException = null;
exceptions = new HashMap();
}
public void setGenricException(String genric)
{
this.genricException = genricException;
}
public String getGenricException()
{
if(this.genricException == null) return ="";
return this.genricException;
}

public void addException(String property , String exception)
{
this.exceptions.put(property , exception);
}
public String getException(String property)
{
return this.exceptions.get(property);
}
public void removeException(String property)
{
this.exceptions.remove(property);
}

public int ExceptionCout()
{
if(this.genricException!=null) return this.exceptions.size()+1;
return this.exceptions.size();
}

public boolean hasException(String property)
{
return this.exceptions.containsKey(property);
}
public boolean hasGenricException()
{
return this.genricException!=null;
}

public boolean hasExceptions()
{
return this.exceptions.size()>0;
}

public List<String> getProperties()
{
List <String> properties = new ArrayList<>();
this.exception.forEach((k ,v) ->{properties.add(k);});
return properties;
}

public String getMessage()
{
if(this.genricException==null) return "";
return this.genricException;
}
}