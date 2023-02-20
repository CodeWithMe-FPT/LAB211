/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.IIOException;
import model.Product;
import model.Customer;
import validator.InputCustomerValidation;
import validator.InputOrderValidation;

/**
 *
 * @author ManhComputer
 */
public class ProductDaoImpl implements ProductDao{
    
    static final String PRODUCT_FILE = "product.txt";
    private List<Product> products = null;
    public ProductDaoImpl() throws IOException{
        this.products = this.load();
    }
    
    

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public Product getById(String id) {
        for(Product b : this.products){
            if(b.getProductID().equals(id)){
                return b;
            }
        }
        return null;
    }

    private List<Product> load() throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(PRODUCT_FILE));
        } catch (Exception e) {
            System.out.println("Please create file book.csv before run program");
            System.exit(0);
        }
        List<Product> products = new ArrayList<>();
        for (int i=0; i< lines.size();++i) {
            String[] cs = lines.get(i).split("\\,");
            products.add(new Product(cs[0], cs[1], cs[2], cs[3], Double.parseDouble(cs[4])));
        }
        return products;
    }
    
}
