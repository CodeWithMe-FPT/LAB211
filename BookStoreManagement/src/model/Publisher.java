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
public class Publisher {
    private String id;
    private String name;
    private String phone;

    public Publisher() {
    }

    public Publisher(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public static Comparator compName = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Publisher p1 = (Publisher)o1;
            Publisher p2 = (Publisher)o2;
            return p1.getName().compareTo(p2.getName());
        }
    };
    
    

    @Override
    public String toString() {
        return id + '|' + name + '|' + phone;
    }
    
    
    
    
}
