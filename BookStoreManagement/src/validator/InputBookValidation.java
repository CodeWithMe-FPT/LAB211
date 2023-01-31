/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import model.Book;
import model.Publisher;


/**
 *
 * @author ManhComputer
 * @param <T>
 */
public class InputBookValidation {
    private List<Book> listBooks;
    public InputBookValidation(List<Book> listBooks){
        this.listBooks = listBooks;
    }
    
    public boolean checkIdBook(String id) throws Exception{
        boolean match = id.matches("(B)\\d{5}");
        if(match){
            for(Book x: this.listBooks){
                if(x.getId().equals(id)) return false;
            }
            
        } else{
            throw new Exception("Wrong format id");
        }
        return true;
    }

    public boolean checkName(String name) throws Exception{
        if(name.equals("")){
            throw new Exception();
        }
        
        return name.length()>=5 && name.length()<=30;
    }
    
    public boolean checkPrice(double price){
        return price>0;
    }
    
    public boolean checkQuantity(int quantity){
        return quantity>0;
    }
    
    public boolean checkStatus(String status) throws Exception{
        if(status.equals("")){
            throw new Exception();
        }
        status = status.toLowerCase();
        return status.equals("available") || status.equals("not available");
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