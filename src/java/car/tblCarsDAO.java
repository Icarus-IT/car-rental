/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBHelpers;

/**
 *
 * @author USER
 */
public class tblCarsDAO implements Serializable {

    public List<tblCarsDTO> getAllCar() throws SQLException, ClassNotFoundException {
        List<tblCarsDTO> CarList = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select carid, carname, color, year, category, price, quantity, image "
                    + "from tbl_cars  order by year DESC";
            con = DBHelpers.makeConnection();
            if (null != con) {
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    if (null == CarList) {
                        CarList = new ArrayList<>();
                    }
                    tblCarsDTO dto = new tblCarsDTO();
                    dto.setCarid(rs.getString("carid"));
                    dto.setCarname(rs.getString("carname"));
                    dto.setColor(rs.getString("color"));
                    dto.setYear(rs.getString("year"));
                    dto.setCategory(rs.getString("category"));
                    dto.setPrice(rs.getInt("price"));
                    dto.setQuantiy(rs.getInt("quantity"));
                    dto.setImage(rs.getString("image"));
                    CarList.add(dto);
                }

            }
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
        return CarList;
    }

    public List<tblCarsDTO> searchCar(String searchValue) throws SQLException, ClassNotFoundException {
        List<tblCarsDTO> CarList = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select carid, carname, color, year, category, price, quantity "
                    + "from tbl_cars where carname like ? order by year DESC";
            con = DBHelpers.makeConnection();
            if (null != con) {
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (null == CarList) {
                        CarList = new ArrayList<>();
                    }
                    tblCarsDTO dto = new tblCarsDTO();
                    dto.setCarid(rs.getString("carid"));
                    dto.setCarname(rs.getString("carname"));
                    dto.setColor(rs.getString("color"));
                    dto.setYear(rs.getString("year"));
                    dto.setCategory(rs.getString("category"));
                    dto.setPrice(rs.getInt("price"));
                    dto.setQuantiy(rs.getInt("quantity"));

                    CarList.add(dto);
                }

            }
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
        return CarList;
    }

    public List<tblCarsDTO> searchCategory(String searchCategory) throws SQLException, ClassNotFoundException {
        List<tblCarsDTO> CarList = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select carid, carname, color, year, category, price, quantity "
                    + "from tbl_cars where category like ? order by year DESC";
            con = DBHelpers.makeConnection();
            if (null != con) {
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchCategory + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (null == CarList) {
                        CarList = new ArrayList<>();
                    }
                    tblCarsDTO dto = new tblCarsDTO();
                    dto.setCarid(rs.getString("carid"));
                    dto.setCarname(rs.getString("carname"));
                    dto.setColor(rs.getString("color"));
                    dto.setYear(rs.getString("year"));
                    dto.setCategory(rs.getString("category"));
                    dto.setPrice(rs.getInt("price"));
                    dto.setQuantiy(rs.getInt("quantity"));

                    CarList.add(dto);

                }

            }
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
        return CarList;
    }

    public List<tblCarsDTO> searchYear(String year) throws SQLException, ClassNotFoundException {
        List<tblCarsDTO> CarList = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select carid, carname, color, year, category, price, quantity "
                    + "from tbl_cars where year like ? order by year DESC";
            con = DBHelpers.makeConnection();
            if (null != con) {
                stm = con.prepareStatement(sql);
                stm.setString(1, year);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (null == CarList) {
                        CarList = new ArrayList<>();
                    }
                    tblCarsDTO dto = new tblCarsDTO();
                    dto.setCarid(rs.getString("carid"));
                    dto.setCarname(rs.getString("carname"));
                    dto.setColor(rs.getString("color"));
                    dto.setYear(rs.getString("year"));
                    dto.setCategory(rs.getString("category"));
                    dto.setPrice(rs.getInt("price"));
                    dto.setQuantiy(rs.getInt("quantity"));

                    CarList.add(dto);

                }

            }
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
        return CarList;
    }
    
    public List<String> searchAllCategory() throws SQLException, ClassNotFoundException {
        List<String> CarList = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct(category) "
                    + "from tbl_cars ";
            con = DBHelpers.makeConnection();
            if (null != con) {
                stm = con.prepareStatement(sql);
              
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (null == CarList) {
                        CarList = new ArrayList<>();
                    }
                    
                    String dto = (rs.getString("category"));
                    
                    CarList.add(dto);

                }

            }
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
        return CarList;
    }

    public List<String> getAllYear() throws SQLException, ClassNotFoundException {
        List<String> YearList = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct(year) "
                    + "from tbl_cars";
            con = DBHelpers.makeConnection();
            if (null != con) {
                stm = con.prepareStatement(sql);
               
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (null == YearList) {
                        YearList = new ArrayList<>();
                    }
                    String dto = (rs.getString("year"));

                    YearList.add(dto);

                }
               

            }
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
        return YearList;
    }
}
