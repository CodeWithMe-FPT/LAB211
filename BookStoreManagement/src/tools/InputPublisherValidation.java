/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Publisher;

/**
 *
 * @author ManhComputer
 */
public class InputPublisherValidation {
    private List<Publisher> listPublishers = new ArrayList<>();
    public InputPublisherValidation(List<Publisher> listPublishers){
        this.listPublishers = listPublishers;
    }
    
    public boolean checkIdPublishers(String id) throws Exception{
        boolean match = id.matches("(P)\\d{5}");
        if(match){
            for(Publisher x: this.listPublishers){
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
    
}
