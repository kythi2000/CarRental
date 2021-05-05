/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.utilities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author HP 840 G2
 */
public class DBHelpers implements Serializable {

    public static Connection makeConnection() throws SQLException, NamingException {

        Context context = new InitialContext();
        Context tomCatContext = (Context) context.lookup("java:comp//env");
        DataSource ds = (DataSource) tomCatContext.lookup("SE1418");
        Connection con = ds.getConnection();

        return con;
//        //1. Load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create connection string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SinhVien";
//        //3. Open connectiong
//        Connection conn = DriverManager.getConnection(url, "sa", "123");
//        return conn;
    }
}
