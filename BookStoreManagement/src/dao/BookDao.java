package dao;

import model.Book;
import model.Publisher;

import java.io.IOException;
import java.util.List;

public interface BookDao {
    boolean add(Book book) throws IOException;
    boolean delete(String id) throws IOException;
    boolean update(Book book) throws IOException;
    Book getById(String id);
    List<Book> getBooksByIdPublisher(String id);
    List<Book> search(String nameBook, String idPublisher);
    List<Book> getAll();
}
