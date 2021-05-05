/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.RentalBillDetailDTO;
import antdp.dtos.RentalDTO;
import antdp.utilities.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class RentalBillDAO {

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

    public String getLastID(String email) throws Exception {
        String lastID = null;
        try {
            String sql = "select RentalID from Rental "
                    + "where DateOfCreate = (select MAX(DateOfCreate) "
                    + "from Rental where Email = ?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, email);
            rs = preS.executeQuery();
            if (rs.next()) {
                lastID = rs.getString("RentalID");
            }
        } finally {
            closeConnection();
        }
        return lastID;
    }

    public boolean createRentalBill(RentalDTO rental) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Rental(RentalID, Email, Total, DateOfCreate, Status) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, rental.getRentalID());
            preS.setString(2, rental.getEmail());
            preS.setFloat(3, rental.getTotal());
            preS.setTimestamp(4, new Timestamp(new Date().getTime()));
            preS.setBoolean(5, true);
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean createRentalBillDetail(RentalBillDetailDTO rentalDetail) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into RentalDetail(RentalDetailID, RentalID, Car_ID, Quantity, Price, RentalDate, ReturnDate) "
                    + "values(?,?,?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, rentalDetail.getRentalDetailID());
            preS.setString(2, rentalDetail.getRentalID());
            preS.setString(3, rentalDetail.getCarID());
            preS.setInt(4, rentalDetail.getQuantity());
            preS.setFloat(5, rentalDetail.getPrice());
            preS.setString(6, rentalDetail.getRentalDate());
            preS.setString(7, rentalDetail.getReturnDate());
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<RentalDTO> getRentalByUsername(String email) throws Exception {
        List<RentalDTO> list = null;
        try {
            String sql = "select RentalID, Total, DateOfCreate "
                    + "from Rental "
                    + "where Email = ? and Status = 'true'";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, email);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String rentalID = rs.getString("RentalID");
                float total = rs.getFloat("Total");
                Date date = rs.getTimestamp("DateOfCreate");
                RentalDTO rentalDTO = new RentalDTO(rentalID, email, date, total, true);
                list.add(rentalDTO);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<RentalBillDetailDTO> getRentalDetailByRentalID(String rentalID) throws Exception {
        List<RentalBillDetailDTO> list = null;
        try {
            String sql = "select RentalDetailID, Car_ID, Quantity, Price, RentalDate, ReturnDate "
                    + "from RentalDetail "
                    + "where RentalID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, rentalID);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String rentalDetailID = rs.getString("RentalDetailID");
                String carID = rs.getString("Car_ID");
                int quantity = rs.getInt("Quantity");
                float price = rs.getFloat("Price");
                String rentalDate = rs.getString("RentalDate");
                String returnDate = rs.getString("ReturnDate");
                RentalBillDetailDTO dto = new RentalBillDetailDTO(rentalDetailID, rentalID, carID, quantity, price, rentalDate, returnDate);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean delete(String rentalID) throws Exception {
        boolean check = false;
        try {
            String sql = "update Rental set Status = 'false' "
                    + "where RentalID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, rentalID);
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
