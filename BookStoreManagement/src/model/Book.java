/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 *
 * @author ManhComputer
 */
public class Book{
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String status;
    private String publisherId;

    public Book(String id, String name, double price, int quantity, String status, String publisherId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status.toLowerCase();
        this.publisherId = publisherId;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }
    
    
    public static Comparator compName = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Book b1 = (Book)o1;
            Book b2 = (Book)o2;
            return b1.getName().compareTo(b2.getName());
        }
    };
    
    public static Comparator compQandP = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Book b1 = (Book)o1;
            Book b2 = (Book)o2;
            if(b1.getQuantity() == b2.getQuantity()){
                if(b1.getPrice()>b2.getPrice()){
                    return -1;
                } else if(b1.getPrice()==b2.getPrice()) {
                   return 0;
                } else return 1;
            } else if(b1.getQuantity()>b2.getQuantity()) {
                return -1;
            } else {
                return 1;
            } 
        }
    };
    
    
    @Override
    public String toString() {
        return id + '|' + name + '|' + price + '|' + quantity + '|' + status +  '|' + publisherId;
    }
    
    public String toStringWithGrid(String publisherName){
        return id + '|' + name + '|' + price + '|' + quantity + '|' + price*quantity +  '|' + publisherName  +  '|' + status; 
    }
    
    
}
