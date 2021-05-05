/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.RatingDTO;
import antdp.utilities.DBHelpers;
import java.io.Serializable;
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
public class RatingDAO implements Serializable {

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

    public boolean insert(RatingDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Rating(CarID, Email, Rating, Comment, DateOfCreate) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, dto.getCarID());
            preS.setString(2, dto.getEmail());
            preS.setInt(3, dto.getRating());
            preS.setString(4, dto.getComment());
            preS.setTimestamp(5, new Timestamp(new Date().getTime()));
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(RatingDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update Rating set Rating = ?, Comment = ? "
                    + "where CarID = ? and Email = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setInt(1, dto.getRating());
            preS.setString(2, dto.getComment());
            preS.setString(3, dto.getCarID());
            preS.setString(4, dto.getEmail());
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public float getRatingCar(String carID) throws Exception {
        float rating = 0;
        try {
            String sql = "select AVG(Rating) "
                    + "from Rating "
                    + "where CarID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, carID);
            rs = preS.executeQuery();
            if (rs.next()) {
                rating = rs.getFloat(1);
            }
        } finally {
            closeConnection();
        }
        return rating;
    }

    public List<RatingDTO> getAllFeedbackByCarID(String carID) throws Exception {
        List<RatingDTO> list = null;
        try {
            String sql = "select Email, Comment, DateOfCreate "
                    + "from Rating "
                    + "where CarID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, carID);
            list = new ArrayList<>();
            rs = preS.executeQuery();
            while (rs.next()) {
                String email = rs.getString("Email");
                String comment = rs.getString("Comment");
                Date date = rs.getTimestamp("DateOfCreate");
                RatingDTO dto = new RatingDTO(email, comment, date);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
