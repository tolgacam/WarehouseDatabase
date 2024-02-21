package ca.ubc.cs304;

import ca.ubc.cs304.controller.Warehouse;
import ca.ubc.cs304.database.DatabaseService;

public class main {
    public static void main(String[] args) {
//        DatabaseService d = new DatabaseService();
//        d.establishConnection(args[0], args[1]);
//        d.test();
        Warehouse warehouseController = new Warehouse(args[0], args[1]);
        warehouseController.showAllWarehouses();
    }
}
