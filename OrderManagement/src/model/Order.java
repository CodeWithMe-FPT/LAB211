/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;
import global.Global;

/**
 *
 * @author ManhComputer
 */
public class Order {
    private String orderId;
    private String customerID;
    private String productID;
    private int orderQuantity;
    private String orderDate;
    private boolean status;
    private String nameCustomer;
    public Order() {
        
    }

    public Order(String orderId, String customerID, String productID, int orderQuantity, String orderDate, boolean status) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }
    
    
    
    
     public static Comparator compNameCus = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Order or1 = (Order)o1;
            Order or2 = (Order)o2;
            return or1.getNameCustomer().compareTo(or2.getNameCustomer());
        }
    };

    @Override
    public String toString() {
        return orderId + " | " + customerID + " | " + productID + " | " + orderQuantity + " | " + orderDate + " | " + status;
    }
    
    public String toStringForFlush() {
        return orderId + "," + customerID + "," + productID + "," + orderQuantity + "," + orderDate + "," + status;
    }

    
    
}
