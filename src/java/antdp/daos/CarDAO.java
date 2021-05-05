/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.CarDTO;
import antdp.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class CarDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preS = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preS != null) {
            preS.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<CarDTO> getAllCar(int index) throws Exception {
        List<CarDTO> list = null;
        CarDTO dto = null;
        String id, name, year, color, category;
        float price;
        int quantity;
        try {
            String sql = "with listSearch as(select ROW_NUMBER() over (order by CarID asc) as r, * "
                    + "from Car where Status = 'true')"
                    + "select CarID, Name, Color, Year, Category, Price, Quantity "
                    + "from listSearch "
                    + "where Status = 'true' and r between ?*3 - 2 and ?*3";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setInt(1, index);
            preS.setInt(2, index);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("CarID");
                name = rs.getString("Name");
                color = rs.getString("Color");
                year = rs.getString("Year");
                category = rs.getString("Category");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Quantity");
                dto = new CarDTO(id, name, color, year, category, price, quantity);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countAllCar() throws Exception {
        int count = 0;
        String sql = "select count(*) from Car where Status = 'true'";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public CarDTO getCarbyID(String carID) throws Exception {
        String name, color, year, cate;
        int quantity;
        float price;
        CarDTO dto = null;
        String sql = "select Name, Color, Year, Price, Quantity, Category "
                + "from Car "
                + "where Status = 'true' and CarID = ?";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, carID);
            rs = preS.executeQuery();
            while (rs.next()) {
                name = rs.getString("Name");
                color = rs.getString("Color");
                quantity = rs.getInt("Quantity");
                price = rs.getFloat("Price");
                year = rs.getString("Year");
                cate = rs.getString("Category");
                dto = new CarDTO(carID, name, color, year, cate, price, quantity);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean update(CarDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update Car set Name = ?, Color = ?, Quantity = ?, "
                    + "Price = ?, Category = ?, Status = ? "
                    + "where CarID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);

            preS.setString(1, dto.getName());
            preS.setString(2, dto.getColor());
            preS.setInt(3, dto.getQuantity());
            preS.setFloat(4, dto.getPrice());
            preS.setString(5, dto.getCategory());
            preS.setBoolean(6, dto.isStatus());
            preS.setString(7, dto.getCarID());
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<CarDTO> search(String rentalDate, String returnDate, String searchName, String searchCategory, int index, int limit) throws Exception {
        List<CarDTO> list = null;
        String id, name, color, year, cate;
        int quantity;
        float price;
        CarDTO dto = null;

        try {
            String sql = "exec getAvailableCar ?, ?, ?, ?, ?, ?;";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, rentalDate);
            preS.setString(2, returnDate);
            preS.setString(3, "%" + searchName + "%");
            preS.setString(4, "%" + searchCategory + "%");
            preS.setInt(5, index);
            preS.setInt(6, limit);
            list = new ArrayList<>();
            rs = preS.executeQuery();
            while (rs.next()) {
                id = rs.getString("CarID");
                name = rs.getString("Name");
                cate = rs.getString("Category");
                color = rs.getString("Color");
                year = rs.getString("Year");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Available");
                dto = new CarDTO(id, name, color, year, cate, price, quantity);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countPage(String rentalDate, String returnDate, String searchName, String searchCategory) throws Exception {
        int count = 0;
        try {
            String sql = "exec countPage ?, ?, ?, ?;";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, rentalDate);
            preS.setString(2, returnDate);
            preS.setString(3, "%" + searchName + "%");
            preS.setString(4, "%" + searchCategory + "%");
            rs = preS.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }

}
