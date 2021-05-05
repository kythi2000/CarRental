/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.RegistrationDTO;
import antdp.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author HP 840 G2
 */
public class RegistrationDAO implements Serializable {

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

    public RegistrationDTO checkLogin(String email, String password) throws Exception {
        RegistrationDTO dto = null;
        String sql = "select Role, Fullname, Phone, Address, VerifyCode, Status "
                + "from Registration "
                + "where Email = ? and Password = ?";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, email);
            preS.setString(2, password);
            rs = preS.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                int verifyCode = rs.getInt("VerifyCode");
                boolean role = rs.getBoolean("Role");
                String status = rs.getString("Status");
                dto = new RegistrationDTO(email, password, fullname, phone, address, status, role, verifyCode);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean create(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Registration(Email, Password, Fullname, Phone, Address, DateOfCreate, Role, Status, VerifyCode) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, dto.getEmail());
            preS.setString(2, dto.getPassword());
            preS.setString(3, dto.getFullname());
            preS.setString(4, dto.getPhone());
            preS.setString(5, dto.getAddress());
            preS.setTimestamp(6, new Timestamp(new Date().getTime()));
            preS.setBoolean(7, false);
            preS.setString(8, "New");
            preS.setInt(9, dto.getVerifyCode());
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateStatus(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update Registration set Status = ? "
                    + "where Email = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(2, dto.getEmail());
            preS.setString(1, "Active");
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

}
