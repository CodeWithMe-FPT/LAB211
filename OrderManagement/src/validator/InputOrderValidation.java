/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Order;

/**
 *
 * @author ManhComputer
 */
public class InputOrderValidation {
    private List<Order> listOrders = new ArrayList<>();
    public InputOrderValidation(List<Order> listOrders){
        this.listOrders = listOrders;
    }
    
    public boolean checkIdOrder(String id) throws Exception {
        boolean match = id.matches("(D)\\d{3}");
        if(match){
            for(Order o: this.listOrders){
                if(o.getOrderId().equals(id)) return false;
            }
        } else {
            throw new Exception("Wrong format id");
        }
        return true;
    }
    
    public boolean checkName(String name) throws Exception{
        if(name.equals("")){
            throw new Exception();
        } else{
            return name.length()>=5 && name.length()<=30;
        }
        
    }
    
    public boolean checkPhone(String phone) throws Exception{
        if(phone.equals("")){
            throw new Exception();
        } else{
            for(int i = 0; i<phone.length(); i++){
                try {
                    Integer.parseInt(Character.toString(phone.charAt(i)));
                } catch (Exception e) {
                    return false;
                }
            }
            return phone.length()>=10 && phone.length()<=12;
        }       
    }
    
    public boolean checkQuantity(int quantity){
        return quantity>0;
    }
    
    public boolean checkDate(String date){
        return date.equals("");
    }
    
    public boolean checkStatus(String status) throws Exception{
        if(status.equals("")) throw new Exception("null");
        if(status.toLowerCase().equals("true") || status.toLowerCase().equals("false")){
            return status.toLowerCase().equals("true");
        } else throw new Exception("invalid");
        
    }
    
}
