/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Order;

/**
 *
 * @author ManhComputer
 */
public interface OrderDao {
    boolean add(Order o);
    boolean delete(String id);
    boolean update (Order order ,boolean status);
    Order getById(String id);
    List<Order> getAll();
    List<Order> getAllWithSort();
    List<Order> getAllPending();

}
