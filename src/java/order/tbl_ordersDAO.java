/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DBHelpers;

/**
 *
 * @author USER
 */
public class tbl_ordersDAO implements Serializable{
    public boolean createHistory(tbl_ordersDTO dto) throws ClassNotFoundException, SQLException{
        Connection con =null;
        PreparedStatement stm =null;
        try {
            //1. make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Insert Into tbl_orders(email,carid,name,carname,price,quantity) "
                        + "Values(?,?,?,?,?,?)";
                //3. Tạo Statement
                stm = con.prepareStatement(sql);
                stm.setString(1,dto.getEmail());
                stm.setString(2,dto.getCarid());
                stm.setString(3,dto.getName());
                stm.setString(4,dto.getCarname());
                stm.setFloat(5,dto.getPrice());  
                stm.setInt(6,dto.getQuantity());
          
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
