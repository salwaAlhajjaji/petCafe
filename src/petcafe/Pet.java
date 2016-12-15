/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Rahaf
 */
public class Pet {

    protected String petID;
    protected String petName;
    protected String petType;
    protected String price;
    protected Owner ownerPet;

    public Pet() {

    }

    public Pet(String id, String name, String type, String price, Owner owner) {
        this.petID = id;
        this.petName = name;
        this.petType = type;
        this.price = price;
        this.ownerPet = owner;
    }

    public void setPetID(String id) {
        this.petID = id;
    }

    public void setPetName(String name) {
        this.petName = name;
    }

    public void setPetType(String type) {
        this.petType = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOwnerPet(Owner owner) {
        this.ownerPet = owner;
    }

    public String getPetID() {
        return this.petID;
    }

    public String getPetName() {
        return this.petName;
    }

    public String getPetType() {
        return this.petType;
    }

    public String getPrice() {
        return this.price;
    }

    public Owner getOwnerPet() {
        return this.ownerPet;
    }

    public void savePetInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnection();
            String insert = "INSERT INTO `pet_info` (`Pet_ID`,`Pet_name`,`Pet_type`,`price`)"
                    + "VALUES(?,?,?,?)";
            ps = con.prepareStatement(insert);
            ps.setString(1, this.petID);
            ps.setString(2, this.petName);
            ps.setString(3, this.petType);
            ps.setString(4, this.price);

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

    public void deletPet(String name, String mobile) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String delet = "DELET FROM `pet+99999+*_info` WHERE `Owner_name`=? and `Owner_mobile`=?";
            ps = con.prepareStatement(delet);
            ps.setString(1, name);
            ps.setString(2, mobile);
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

    public void updatePetInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnection();
            String update = "UPDATE `pet_info` set `Pet_type`=? and `Pet_name`=?"
                    + "where `Pet_ID`=?";
            ps = con.prepareStatement(update);
            ps.setString(1, this.petType);
            ps.setString(2, this.petName);
            ps.setString(3, this.petType);
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
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Vector<Pet> load(String mobile) {
        Vector<Pet> listOfPet = new Vector<Pet>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = DBManager.getConnection();
            String select = " SELECT `Owner_name`,`Pet_name`,`Pet_type`,`Price`"
                    + "FROM `owner_info` inner join `pet_info`"
                    + "on `owner_info`.`Owner_mobile`=`pet_info`.`Owner_mobile`"
                    + "WEHER `Owner_mobile`=?";
            stmt = con.prepareStatement(select);
            stmt.setString(1, mobile);
            result = stmt.executeQuery();
            while (result.next()) {
                Pet myPet = new Pet();
                myPet.setPetName(result.getString("Pet_name"));
                myPet.setPetType(result.getString("Pet_type"));
                myPet.setPrice(result.getString("Price"));
                Owner owner = new Owner();
                owner.setOwnerMobile(mobile);
                owner.setOwnerName(result.getString("Owner_name"));
                myPet.setOwnerPet(owner);
                listOfPet.add(myPet);
            }

        } catch (Exception e) {

            e.printStackTrace();
        } // closing all connection if they were opened 
        finally {

            if (stmt != null) {
                try {
                    stmt.close();
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
        return listOfPet;
    }

}
