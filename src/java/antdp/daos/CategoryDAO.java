/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

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
public class CategoryDAO implements Serializable {

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

    public List<String> getAllCategory() throws Exception {
        List<String> list = null;
        try {
            String sql = "select Category from Category";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString("Category"));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

}
