import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;

public class DesignationAddTestCase {
    public static void main(String gg[]) {
        String title = gg[0];
        try {
            DesignationDTOInterface designationDTO = new DesignationDTO();
            designationDTO.setTitle(title);
            DesignationDAOInterface designationDAO = new DesignationDAO();
            designationDAO.add(designationDTO);
            System.out.println("Designation " + title + "  code : " + designationDTO.getCode());
        } catch (DAOException daoException) {
            System.out.println(daoException.getMessage());
        }
    }

}