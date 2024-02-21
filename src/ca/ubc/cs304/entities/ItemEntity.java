package ca.ubc.cs304.entities;

import java.sql.Date;

public class ItemEntity {

    private final int ItemID;
    private final String Status;
    private final Date ReceiveDate;
    private final String Sector;
    private final int Fragile;
    private final int DeliveryP_ID;
    private final int WarehouseID;
    private final int CustomerID;
    private final int Missing;

    public ItemEntity(int itemID, String status, Date receiveDate,
                      String sector, int fragile, int deliveryP_ID, int warehouseID, int customerID, int missing) {
        this.ItemID = itemID;
        this.Status = status;
        this.ReceiveDate = receiveDate;
        this.Sector = sector;
        this.Fragile = fragile;
        this.DeliveryP_ID = deliveryP_ID;
        this.WarehouseID = warehouseID;
        this.CustomerID = customerID;
        this.Missing = missing;
    }

    public int getItemID() {
        return ItemID;
    }

    public String getStatus() {
        return Status;
    }

    public Date getRecieveDate() {
        return ReceiveDate;
    }

    public String getSector() {
        return Sector;
    }

    public int getFragile() {
        return Fragile;
    }

    public int getDeliveryP_ID() {
        return DeliveryP_ID;
    }

    public int getWarehouseID() {
        return WarehouseID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getMissing() { return Missing;}





}