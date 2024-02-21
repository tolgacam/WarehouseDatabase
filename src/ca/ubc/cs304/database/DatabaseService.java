package ca.ubc.cs304.database;

import ca.ubc.cs304.entities.ItemEntity;
import ca.ubc.cs304.entities.WarehouseEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class DatabaseService {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";

    private Connection connection = null;

    public DatabaseService(String username, String password) {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println("Exception:" + " " + e.getMessage());
        }
    }

    public boolean establishConnection(String username, String password) {
        try {
            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            System.out.println("\nConnected to Oracle!");
        } catch (SQLException e) {
            System.out.println("Exception:" + " " + e.getMessage());
            return false;
        }
        return true;
    }

    public void test() {
        try {
            String query = "CREATE TABLE test1 (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ps.close();
            System.out.println("worked");
        } catch (SQLException e) {
            System.out.println("Exception:" + " " + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + " " + e.getMessage());
        }
    }

    public WarehouseEntity[] getWarehouses() {
        ArrayList<WarehouseEntity> result = new ArrayList<WarehouseEntity>();

        try {
            System.out.println("A");
            String query = "SELECT Country, Name, BuildingNumber,Postal, Capacity FROM " + "WAREHOUSE";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                WarehouseEntity warehouse = new WarehouseEntity(rs.getInt("WarehouseID"),
                        rs.getString("Country"),
                        rs.getInt("Capacity"),
                        rs.getInt("BuildingNumber"),
                        rs.getString("Name"),
                        rs.getString("Postal")
                        //rs.getString("Street"),
                );
                result.add(warehouse);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Exception:" + " " + e.getMessage());
        }
        return result.toArray(new WarehouseEntity[result.size()]);
    }

    public ItemEntity[] getItemInfo() {
        ArrayList<ItemEntity> result = new ArrayList<ItemEntity>();

        try {
            String query = "SELECT * FROM ITEMS NATURAL JOIN ITEMSTATUS";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemEntity item = new ItemEntity(rs.getInt("ITEMID"),
                        rs.getString("STATUS"),
                        rs.getDate("RECEIVEDATE"),
                        rs.getString("SECTOR"),
                        rs.getInt("FRAGILE"),
                        rs.getInt("DELIVERYP_ID"),
                        rs.getInt("WAREHOUSEID"),
                        rs.getInt("CUSTOMERID"),
                        rs.getInt("MISSING")
                );
                result.add(item);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Exception:" + " " + e.getMessage());
        }
        for (ItemEntity item : result) {
            System.out.println(item.getItemID() + " " + item.getStatus() + " " + item.getRecieveDate()
                    + " " + item.getSector() + " " + item.getFragile() + " " + item.getDeliveryP_ID()
                    + " " + item.getWarehouseID() + " " + item.getCustomerID() + " " + item.getMissing());
        }
        return result.toArray(new ItemEntity[result.size()]);
    }

    public void insertItem(ItemEntity item) {
        try {
            String query = "INSERT INTO ITEMS VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, item.getItemID());
            ps.setString(2, item.getSector());
            ps.setString(3, item.getStatus());
            ps.setDate(4, item.getRecieveDate());
            ps.setInt(5, item.getFragile());
            ps.setInt(6, item.getDeliveryP_ID());
            ps.setInt(7, item.getWarehouseID());
            ps.setInt(8, item.getCustomerID());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteItem(int itemID) {
        try {
            String query = "DELETE FROM ITEMS WHERE ITEMID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, itemID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("WARNING: " + " Branch " + itemID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateItem(int itemID, String sector, String status, Date recieveDate,
                           int fragile, int deliverp_ID, int warehouseID, int customerID){
        try {
            String query = "UPDATE ITEMS SET SECTOR = ?, STATUS = ?, RECEIVEDATE = ?, FRAGILE = ?, DELIVERYP_ID = ?, " +
                    "WAREHOUSEID = ?, CUSTOMERID = ? WHERE ITEMID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sector);
            ps.setString(2, status);
            ps.setDate(3, recieveDate);
            ps.setInt(4, fragile);
            ps.setInt(5, deliverp_ID);
            ps.setInt(6, warehouseID);
            ps.setInt(7, customerID);
            ps.setInt(8, itemID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("WARNING: " + " Branch " + itemID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + " " + e.getMessage());
            rollbackConnection();
        }
    }
    


}