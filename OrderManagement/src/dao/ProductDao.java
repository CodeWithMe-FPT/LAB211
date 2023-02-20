package dao;

import model.Product;
import model.Customer;

import java.io.IOException;
import java.util.List;

public interface ProductDao {
    Product getById(String id);
    List<Product> getAll();
}
