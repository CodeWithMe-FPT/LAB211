package dao;

import model.Publisher;

import java.io.IOException;
import java.util.List;

public interface PublisherDao {
    boolean add(Publisher publisher) throws IOException;
    boolean delete(String id) throws IOException;
    boolean update(Publisher publisher) throws IOException;
    Publisher getById(String id);
    List<Publisher> getAll();
}
