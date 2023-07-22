package sampleproblems;

import java.sql.*;

public class Problem1 {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        runQuery();
    }
    public static void runQuery() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sqlSelectAllPersons = "SELECT * FROM actor";
        String connectionUrl = "jdbc:mysql://localhost:3306/sakila";
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "Praveen#2001");
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
             ResultSet resultSet = ps.executeQuery()) {

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue + " ");
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            // handle the exception
            System.out.println("Error occured" + e);
        }
    }


}
