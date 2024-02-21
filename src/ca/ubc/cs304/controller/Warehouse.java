package ca.ubc.cs304.controller;

import ca.ubc.cs304.UI.WelcomeUI;
import ca.ubc.cs304.database.DatabaseService;
import ca.ubc.cs304.delegates.Login;
import ca.ubc.cs304.entities.ItemEntity;
import ca.ubc.cs304.entities.WarehouseEntity;
import java.sql.Date;


public class Warehouse implements Login {

    private DatabaseService databaseService;
    private WelcomeUI welcomeUI;

    private String Username;
    private String Password;

    public Warehouse(String username, String password) {
        Username = username;
        Password = password;
        databaseService = new DatabaseService(username, password);
        boolean connected = databaseService.establishConnection(username, password);
        init();
    }

    private void init() {
        welcomeUI = new WelcomeUI(this, Username, Password);
    }

    @Override
    public void login(String username, String password) {
        boolean connected = databaseService.establishConnection(username, password);

//        if (connected) {
//            loginWindow.dispose();
//            TerminalTransactions transaction = new TerminalTransactions();
//            transaction.setupDatabase(this);
//            transaction.showMainMenu(this);
//        } else {
//            loginWindow.handleLoginFailed();
//
//            if (loginWindow.hasReachedMaxLoginAttempts()) {
//                loginWindow.dispose();
//                System.out.println("You have exceeded your number of allowed attempts");
//                System.exit(-1);
//            }
//        }
    }

    public void showAllItems() {
        ItemEntity[] items = databaseService.getItemInfo();

        for (int i = 0; i < items.length; i++) {
            ItemEntity item = items[i];

            // simplified output formatting; truncation may occur
            System.out.printf("%-10.10s", item.getItemID());
            System.out.printf("%-20.20s", item.getStatus());
            System.out.printf("%-20.20s", item.getRecieveDate());
            System.out.printf("%-20.20s", item.getSector());
            System.out.printf("%-10.10s", item.getFragile());
            System.out.printf("%-10.10s", item.getDeliveryP_ID());
            System.out.printf("%-10.10s", item.getWarehouseID());
            System.out.printf("%-10.10s", item.getCustomerID());

            System.out.println();
        }
    }

    public void insertItem(ItemEntity item){
        databaseService.insertItem(item);
    }

    public void deleteItem(int itemID) {
        databaseService.deleteItem(itemID);
    }

    public void updateItem(int itemID, String sector, String status, Date recieveDate,
                           int fragile, int deliverp_ID, int warehouseID, int customerID) {
        databaseService.updateItem(itemID, sector, status, recieveDate, fragile, deliverp_ID,
                warehouseID, customerID);
    }

    public void showAllWarehouses() {
        WarehouseEntity[] warehouses = databaseService.getWarehouses();
        int c;
        for (int i = 0; i < warehouses.length; i++) {
            WarehouseEntity warehouse = warehouses[i];

            System.out.printf("%-10.10s", warehouse.getWarehouseID());
            System.out.printf("%-20.20s", warehouse.getCountry());
            System.out.printf("%-20.20s", warehouse.getName());
            System.out.printf("%-20.20s", warehouse.getPostal());
            System.out.printf("%-10.10s", warehouse.getBuildingNumber());
            System.out.printf("%-10.10s", warehouse.getCapacity());
            System.out.printf("%-10.20s", warehouse.getStreet());

            System.out.println();
        }
    }
}