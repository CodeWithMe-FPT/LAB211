package dao;

import java.io.File;
import model.Customer;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao {
    static final String CUSTOMER_FILE = "customer.txt";
    public List<Customer> customers = null;
    public CustomerDaoImpl() throws IOException {
        this.customers = load();
    }
    @Override
    public boolean add(Customer customer){
        this.customers.add(customer);
        try {
            this.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Please close file customer.txt or unmark read-only before run program!!!");
            this.customers.remove(this.customers.size()-1);
        }
        return false;
    }

    @Override
    public boolean update(Customer customer){
        int index;
        String id = customer.getCustomerID();
        String olName, olAddress, olPhone;
        Customer c;
        for(index = 0; index< this.customers.size(); index++){
            if(this.customers.get(index).getCustomerID().equals(id)){       
                c = this.customers.get(index);
                olName = c.getCustomerName();
                olAddress = c.getCustomerAddress();
                olPhone = c.getCustomerPhone();
                if(!customer.getCustomerName().equals("")){
                    c.setCustomerName(customer.getCustomerName());
                }
                if(!customer.getCustomerAddress().equals("")){
                    c.setCustomerAddress(customer.getCustomerAddress());
                }
                
                if(!customer.getCustomerAddress().equals("")){
                    c.setCustomerPhone(customer.getCustomerPhone());
                }
                
                try {
                    this.flush();
                    return true;
                } catch (Exception e) {
                    System.out.println("Please close file customer.txt or unmark read-only before run program!!!");
                    c.setCustomerName(olName);
                    c.setCustomerAddress(olAddress);
                    c.setCustomerPhone(olPhone);
                    break;
                }
            }
        }
        
        return false;
    }

    @Override
    public Customer getById(String id) {
        for(Customer p : this.customers){
            if(p.getCustomerID().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return this.customers;
    }

    public void flush() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Customer p: this.customers) {
            lines.add(p.toStringForFlush()+"\n");
        }
        Files.write(Paths.get(CUSTOMER_FILE), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        for (String line: lines) {
            Files.write(Paths.get(CUSTOMER_FILE), line.getBytes(), StandardOpenOption.APPEND);
        }
    }

    @Override
    public Customer search(String id) {
        return this.getById(id);
    }
    
    

    private List<Customer> load() throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(CUSTOMER_FILE));
        } catch (Exception e) {
            System.out.println("Please create file customer.csv before run program");
            System.exit(0);
        }
        List<Customer> customers = new ArrayList<>();
        for (int i=0; i< lines.size();++i) {
            String[] cs = lines.get(i).split("\\,");
            customers.add(new Customer(cs[0], cs[1], cs[2], cs[3]));
        }
        return customers;
    }
}
