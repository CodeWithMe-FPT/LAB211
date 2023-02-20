/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.List;
import model.Product;

/**
 *
 * @author ManhComputer
 */
public class InputProductValidation {
    private List<Product> listProducts;
    public InputProductValidation(List<Product> listProducts){
        this.listProducts = listProducts;
    }
    
    public boolean checkIdProduct(String id) throws Exception {
        boolean match = id.matches("(P)\\d{3}");
        if(match){
            for(Product x: this.listProducts){
                if(x.getProductID().equals(id)) return false;
            }
        } else {
            throw new Exception("Wrong format id");
        }
        return true;
    }
}
