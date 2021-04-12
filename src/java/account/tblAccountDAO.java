/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBHelpers;

/**
 *
 * @author USER
 */
public class tblAccountDAO implements Serializable {

    public boolean checkLogin(String email, String password)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Select email "
                        + "from tbl_accounts "
                        + "where email =? and password =?";
                //3. Tạo Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }

            } // end if con is not initialized
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public tblAccountDTO findUsername(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            tblAccountDTO dto = new tblAccountDTO();
            //1. Make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2 Create connection String
                String sql = "Select email, password, phone, name, address, createdate, status "
                        + "from tbl_accounts "
                        + "where email = ?";
                //3 Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                //4. Execute Query
                rs = stm.executeQuery();

                if (rs.next()) {
                    dto.setEmail(rs.getString("email"));
                    dto.setPassword(rs.getString("password"));
                    dto.setPhone(rs.getString("phone"));
                    dto.setName(rs.getString("name"));
                    dto.setAddress(rs.getString("address"));
                    dto.setDate(rs.getDate("createdate"));
                    dto.setStatus(rs.getBoolean("status"));
                    return dto;
                }
            }// end if con is not initialized

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    public boolean createAccount(String email, String password,String phone,String name,String address) throws ClassNotFoundException, SQLException{
        Connection con =null;
        PreparedStatement stm =null;
        try {
            //1. make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Insert Into tbl_accounts(email,password,phone,name,address,createdate,status) "
                        + "Values(?,?,?,?,?,?,?)";
                //3. Tạo Statement
                stm = con.prepareStatement(sql);
                stm.setString(1,email);
                stm.setString(2,password);
                stm.setString(3,phone);
                stm.setString(4,name);
                stm.setString(5,address);
                long millis=System.currentTimeMillis();  
                Date date=new java.sql.Date(millis); 
                stm.setDate(6,date);
                stm.setBoolean(7, true);
                //4. Execute Query
                int row = stm.executeUpdate();
                if (row>0){
                    return true;
                }        
            } 
        } // end if con is not initialized
        finally{
            if (stm != null){
                stm.close();
            }
            if (con!=null){
                con.close();
            }
        }
        return false;
    }
}
