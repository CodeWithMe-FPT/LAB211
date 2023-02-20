/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import global.Global;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Order;

/**
 *
 * @author ManhComputer
 */
public class OrderDaoImpl implements OrderDao{
static final String ORDER_FILER = "order.txt";
    public List<Order> orders = null;
    public OrderDaoImpl() throws IOException {
        this.orders = load();
    }
    @Override
    public boolean add(Order o){
        this.orders.add(o);
        try {
            this.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Please close file order.txt or unmark read-only before run program!!!");
            this.orders.remove(this.orders.size()-1);
        }
        return false;
    }

    @Override
    public boolean delete(String id){
        for(int i = 0; i < this.orders.size(); i++){
            if(this.orders.get(i).getOrderId().equals(id)){
                Order o = this.orders.get(i);
                this.orders.remove(i);
                try {
                    this.flush();
                    return true;
                } catch (IOException e) {
                    System.out.println("Please close file order.txt or unmark read-only before run program!!!");
                    this.orders.add(i, o);
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public boolean update(Order order ,boolean status){
        boolean oStatus = order.getStatus();
        order.setStatus(status);
        try {
            this.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Please close file order.txt or unmark read-only before run program!!!");
            order.setStatus(oStatus);
        }
        return false;
    }

    @Override
    public Order getById(String id) {
        for(Order p : this.orders){
            if(p.getOrderId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    @Override
    public List<Order> getAllWithSort() {
        for(Order o : this.orders){
            o.setNameCustomer(Global.customerDao.getById(o.getCustomerID()).getCustomerName());
        }
        Collections.sort(this.orders, Order.compNameCus);
        return this.orders;
    }

    @Override
    public List<Order> getAllPending() {
        List<Order> result = new ArrayList<>();
        for(Order p : this.orders){
            if(!p.getStatus()){
                result.add(p);
            }
        }
        return result;
    }
    
    
    
    

    public void flush() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Order p: this.orders) {
            lines.add(p.toStringForFlush()+"\n");
        }
        Files.write(Paths.get(ORDER_FILER), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        for (String line: lines) {
            Files.write(Paths.get(ORDER_FILER), line.getBytes(), StandardOpenOption.APPEND);
        }
    }

    
    

    private List<Order> load() throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(ORDER_FILER));
        } catch (Exception e) {
            System.out.println("Please create file order.txt before run program");
            System.exit(0);
        }
        List<Order> orders = new ArrayList<>();
        for (int i=0; i< lines.size();++i) {
            String[] cs = lines.get(i).split("\\,");
            orders.add(new Order(cs[0], cs[1], cs[2], Integer.parseInt(cs[3]), cs[4], Boolean.valueOf(cs[5])));
        }
        return orders;
    }
}
