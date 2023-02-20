/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import model.Customer;
import model.Customer;


/**
 *
 * @author ManhComputer
 * @param <T>
 */
public class InputCustomerValidation {
    private List<Customer> listCustomers;
    public InputCustomerValidation(List<Customer> listCustomers){
        this.listCustomers = listCustomers;
    }
    
    public boolean checkIdCustomer(String id) throws Exception {
        boolean match = id.matches("(C)\\d{3}");
        if(match){
            for(Customer x: this.listCustomers){
                if(x.getCustomerID().equals(id)) return false;
            }
        } else {
            throw new Exception("Wrong format id");
        }
        return true;
    }

    public boolean checkName(String name) {
        return name.equals("");
    }
    
    public boolean checkAddress(String address){
        try {
            for(int i = 0; i < address.length(); i++){
                Integer.parseInt(Character.toString(address.charAt(i)));
            }
        } catch (Exception e) {
            return false;
        }
        return address.equals("");
    }
    
    public boolean checkPhone(String phone) throws Exception {
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
    
}    

    
//    public boolean checkPName(String name) throws Exception{
//        if(name.equals("")){
//            throw new Exception();
//        }
//        boolean match = name.matches("^[A-Za-z0-9 _-]{10,25}$");
//        return match;
//    }
//    
//    public boolean checkPDuration(String duration) throws Exception{
//        if(duration.equals("")){
//            throw new Exception();
//        }
//        try {
//            int num = Integer.parseInt(duration);
//            if(num>=6 && num<=36){
//                return true;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }
//    
//    public boolean checkPQuantity(String quantity)throws Exception{
//        if(quantity.equals("")){
//            throw new Exception();
//        }
//        try {
//            int num = Integer.parseInt(quantity);
//            if(num<0){
//                return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
//    public boolean checkPUnitPrice(String unitPrice) throws Exception{
//        if(unitPrice.equals("")){
//            throw new Exception();
//        }
//        try {
//            int num = Integer.parseInt(unitPrice);
//            if(num>0 && num<=100){
//                return true;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }