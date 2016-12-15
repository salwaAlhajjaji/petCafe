/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Rahaf
 */
public class Employee {

  protected String employeeID;
  protected String employeeName;
  protected String emplyeeSSN;

    public Employee() {

    }

    public Employee(String id,String name, String SSN) {
        this.employeeID = id;
        this.employeeName = name;
        this.emplyeeSSN=SSN;
    }
        public Employee(String id,String name) {
        this.employeeID = id;
        this.employeeName = name;
        
    }
    public Employee(String id){
        this.employeeID=id;
    }

    public void setEmplyeeName(String name) {
        this.employeeName = name;
    }

    public void setEmployeeID(String ID) {
        this.employeeID = ID;
    }
    public void setEmplyeeSSN(String SSN) {
        this.emplyeeSSN = SSN;
    }


    public String getEmployeeName() {
        return this.employeeName;
    }

    public String getEmployeeID() {
        return this.employeeID;
    }
     public String getEmployeeSSN() {
        return this.emplyeeSSN;
    }

    public boolean checkSSN(String name) {
        boolean exist = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            con = DBManager.getConnection();
            String select = "SELECT `Employee_SSN` FROM `employee_info` WHERE `Employee_name`=?";
            ps = con.prepareStatement(select);
            ps.setString(1,name);
            
            result = ps.executeQuery();
            if (result.first()) {
              
                exist = true;
          
            } 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return exist;
    
    }
   

    public boolean passworedviled(String name,String pass) {
       
        boolean exite=false;
        if(checkSSN(name)){
            exite=true;
        pass.equals("ER"+this.emplyeeSSN);
    }
        else{
           exite=false; 
        }
        return exite;
    }

    public void saveEmployeeInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnection();
            String insert = "INSERT INTO `employee_info`"
                    + "(`Employee_ID`,`Employee_name`,`Employee_SSN`)VALUES(?,?,?)";
            ps = con.prepareStatement(insert);
            ps.setString(1, this.employeeID);
            ps.setString(2, this.employeeName);
            ps.setString(3, this.emplyeeSSN);
            int result = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public boolean isEmployeeExist(String id) {
        boolean exist = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            con = DBManager.getConnection();
            String select = "SELECT `Employee_name` FROM `employee_info` WHERE `Employee_ID`=? ";
            ps = con.prepareStatement(select);
            ps.setString(1,id);
            result = ps.executeQuery();
            if (result.first()) {
              
                exist = true;
            } 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return exist;
    }

    public void deletEmployee(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String delet = "delete from `employee_info` where `Employee_ID`=?";
            ps = con.prepareStatement(delet);
            ps.setString(1, id);
            int result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public void updateEmployyeInfo(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnection();
            if(isEmployeeExist(id)){
            String update = "update `employee_info` set `Employee_name`=? , `Employee_SSN`=?"
                    + "where `Employee_ID`=?";
            ps = con.prepareStatement(update);
            ps.setString(1, this.employeeName);
            ps.setString(2, this.emplyeeSSN);
            ps.setString(3, id);
            int result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*public Owner lode(Owner id){
            Connection con=null;
            PreparedStatement ps=null;
            ResultSet result=null;
            try{
                con=DBManager.getConnection();
                String select="SELECT`Employee_ID`,`Owner_name` FROM `employee_info` inner join `owner_info"+
                        "on `employee_info`.`Employee_ID`=`owner_info`.`Employee_ID` WHERE `Employee_ID`=?";
                ps=con.prepareStatement(select);
                ps.setString(1,""+id);
                result=ps.executeQuery();
                if(result.next()){
                    String employeeName=result.getString("Employee_name");
                    String emplyeeID =result.getString("Employee_ID");
                    Owner Owner=new Owner();
                    Employee emplyee1=new Employee();
                    
                    
                   
                }
                else{
                    return null;
                }
            }
            catch(Exception e){e.printStackTrace();}
            finally{
                 if(result != null)
                    try{ result.close();}
                    catch(Exception e){ e.printStackTrace(); 
                     return null; }
                 
                if(ps != null)
                    try{ ps.close();}
                    catch(Exception e){ e.printStackTrace();}
                   
                if(con != null)
                    try{ ps.close();}
                    catch(Exception e){ e.printStackTrace();}
            }
        }*/
      
         
    
    
}
