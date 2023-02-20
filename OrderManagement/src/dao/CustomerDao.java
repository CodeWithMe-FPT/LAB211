package dao;

import model.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerDao {
    boolean add(Customer publisher);
    Customer search(String id);
    boolean update(Customer publisher);
    Customer getById(String id);
    List<Customer> getAll();
}
