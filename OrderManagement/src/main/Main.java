package main;

import dao.ProductDaoImpl;
import dao.CustomerDaoImpl;
import global.Global;
import pstate.MainMenu;
import pstate.Menu;

import java.io.IOException;
import dao.ProductDao;
import dao.CustomerDao;
import dao.OrderDaoImpl;

public class Main {
    public static void main(String[] args) {
        // init global objects
        CustomerDao cDao = null;
        ProductDao pDao = null;
        OrderDaoImpl oDao = null;
        try {
            pDao = new ProductDaoImpl();
            cDao = new CustomerDaoImpl();
            oDao = new OrderDaoImpl();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Global.productDao = pDao;
        Global.customerDao = cDao;
        Global.orderDao = oDao;

        // start program
        Menu menu = new MainMenu();
        while (menu != null) {
            menu = menu.run();
        }
    }
}
