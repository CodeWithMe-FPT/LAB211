package pstate;

import java.util.Scanner;
import global.Global;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Customer;
import model.Order;
import model.Product;
import validator.InputCustomerValidation;
import validator.InputOrderValidation;
import validator.InputProductValidation;

public class MainMenu implements Menu {
    @Override
    public Menu run() {
        System.out.println("--- Main Menu ---");
        System.out.println("1: List all products");
        System.out.println("2. List all customers");
        System.out.println("3. Search a Customer based on his/her ID");
        System.out.println("4. Add a Customer");
        System.out.println("5. Update a Customer");
        System.out.println("6. List all Orders in ascending order of Customer name");
        System.out.println("7. List all pending Orders");
        System.out.println("8. Add an Order");
        System.out.println("9. Update an Order based on its ID");
        System.out.println("10. Delete an Order based on its ID");
        System.out.println("Other. Quit");
        System.out.println("-----------------");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (choice.equals("1")) {
            List<Product> ps = Global.productDao.getAll();
            for(Product p : ps){
                System.out.println(p);
            }
        } else if (choice.equals("2")){
            List<Customer> cs = Global.customerDao.getAll();
            for(Customer c : cs){
                System.out.println(c);
            }
        } else if(choice.equals("3")) {
            System.out.println("Input Id customer:");
            Customer c = Global.customerDao.search(sc.nextLine());
            if(c!=null){
                System.out.println(c);
            } else {
                System.out.println("This customer does not exist");
            }
        } else if(choice.equals("4")){
            InputCustomerValidation validateC = new InputCustomerValidation(Global.customerDao.getAll());
            
            String id, name, address, phone;
          
            do {                
                System.out.println("Input Id customer:");
                id = sc.nextLine();
                try {
                    if(validateC.checkIdCustomer(id)){
                        break;
                    } else {
                        throw new Exception("Id already exists");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
            
            
            do {                
                System.out.println("Input name:");
                name = sc.nextLine();
                if(!validateC.checkName(name)){
                    break;
                } else {
                    System.out.println("Name must be different from null");
                }
            } while (true);
            
            do {                
                System.out.println("Input address:");
                address = sc.nextLine();
                if(!validateC.checkAddress(address)){
                    break;
                } else {
                    System.out.println("address must be different from null");
                }
            } while (true);
            
            do {                
                System.out.println("Input phone:");
                phone = sc.nextLine();
                try {
                    if(validateC.checkPhone(phone)){
                        break;
                    } else {
                        System.out.println("Phone length from 10 to 12");
                    }
                } catch (Exception e) {
                    System.out.println("phone must be different from null");
                }
            } while (true);
            
            if(Global.customerDao.add(new Customer(id, name, address, phone))){
                System.out.println("Add successfully");
            } else {
                System.out.println("Add failed");
            }
            
            
        } else if(choice.equals("5")){
            InputCustomerValidation validateC = new InputCustomerValidation(Global.customerDao.getAll());
            String id, name, address, phone;
            do {                
                System.out.println("Input Id customer:");
                id = sc.nextLine();
                try {
                    if(!validateC.checkIdCustomer(id)){
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Id does not exists");
                }
            } while (true);
            
            System.out.println("Input name:");
            name = sc.nextLine();
            
            System.out.println("Input address");
            address = sc.nextLine();
            
            do {                
                System.out.println("Input phone");
                phone = sc.nextLine();
                try {
                    if(validateC.checkPhone(phone)){
                        break;
                    } else {
                        System.out.println("Invalid phone");
                    }
                } catch (Exception e) {
                    break;
                }
            } while (true);
            
            if(Global.customerDao.update(new Customer(id, name, address, phone))){
                System.out.println("Update successfully");
            } else {
                System.out.println("Update failed");
            }
        } else if(choice.equals("6")){
            List<Order> os = Global.orderDao.getAllWithSort();
            for(Order o : os){
                System.out.println(o);
            }
        } else if(choice.equals("7")){
            List<Order> os = Global.orderDao.getAllPending();
            for(Order o : os){
                System.out.println(o);
            }
        } else if(choice.equals("8")){
            InputOrderValidation validateO = new InputOrderValidation(Global.orderDao.getAll());
            String pId, cId, oId, date;
            int quantity;
            boolean status;
            String choose;
          
            do {                
                System.out.println("Input Id order:");
                oId = sc.nextLine();
                try {
                    if(validateO.checkIdOrder(oId)){
                        break;
                    } else {
                        throw new Exception("Id already exists");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
            
            do {                
                System.out.println("-- List Customer --");
                List<Customer> cs = Global.customerDao.getAll();
                for(int i = 1; i <= cs.size(); i++){
                    System.out.println(i + ". " + cs.get(i-1));
                } 
                System.out.println("Choose customer:");
                choose = sc.nextLine();
                try {
                    int c = Integer.parseInt(choose);
                    if(c <= cs.size()){
                        cId = cs.get(c-1).getCustomerID();
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Choice invalid");
                }
            } while (true);

            do {                
                System.out.println("-- List Product --");
                List<Product> cs = Global.productDao.getAll();
                for(int i = 1; i <= cs.size(); i++){
                    System.out.println(i + ". " + cs.get(i-1));
                } 
                System.out.println("Choose product:");
                choose = sc.nextLine();
                try {
                    int c = Integer.parseInt(choose);
                    if(c <= cs.size()){
                        pId = cs.get(c-1).getProductID();
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Choice invalid");
                }
            } while (true);
            
            do {                
                System.out.println("Input quantity:");
                try {
                    quantity = Integer.parseInt(sc.nextLine());
                    if(validateO.checkQuantity(quantity)){
                        break;
                    } else {
                        System.out.println("quantity must be >0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid quantity");
                }
                
            } while (true);
            
            do {                
                System.out.println("Input date:");
                date = sc.nextLine();
                if(!validateO.checkDate(date)){
                    break;
                } else {
                    Date now = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    date = sdf.format(now);
                    break;
                }
            } while (true);
            
            do {                
                System.out.println("Input status:");
                try {
                    status = validateO.checkStatus(sc.nextLine());
                    break;
                    
                } catch (Exception e) {
                    System.out.println("Invalid status");
                }
            } while (true);
            
            if(Global.orderDao.add(new Order(oId, cId, pId, quantity, date, status))){
                System.out.println("Add successfully");
            } else {
                System.out.println("Add failed");
            }
        } else if(choice.equals("9")){
            InputOrderValidation validateO = new InputOrderValidation(Global.orderDao.getAll());
            String id;
            boolean status;
            Order o = new Order();
            do {                
                System.out.println("Input Id order:");
                id = sc.nextLine();
                try {
                    if(!validateO.checkIdOrder(id)){
                        o = Global.orderDao.getById(id);
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Order does not exists");                    
                } 
            } while (true);
            
            do {                
                System.out.println("Input status:");
                
                try {
                    if(validateO.checkStatus(sc.nextLine())){
                        status = true;
                    } else {
                        status = false;
                    }
                    break;
                    
                } catch (Exception e) {
                    if(!e.getMessage().equals("invalid")){
                        status = o.getStatus();
                        break;
                    }
                }  
            } while (true);
            
            if(Global.orderDao.update(o, status)){
                    System.out.println("Update successfully");
                } else {
                    System.out.println("Update failed");
            }
            
            
        } else if(choice.equals("10")){
                InputOrderValidation validateO = new InputOrderValidation(Global.orderDao.getAll());
                System.out.println("Input Id order:");
                String id = sc.nextLine();
                try {
                    if(validateO.checkIdOrder(id)){
                        throw new Exception();
                    }
                    if(Global.orderDao.delete(id)){
                        System.out.println("Delete successfully");
                    } else {
                        System.out.println("Delete failed");
                    }
                    
                } catch (Exception e) {
                    System.out.println("Order does not exists");
                    System.out.println("Delete failed");
                } 
        } else{
            return null;
        }
        
        

        
        
        return this;
    }
}
