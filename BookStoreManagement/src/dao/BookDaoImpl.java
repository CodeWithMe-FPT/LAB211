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
import model.Book;
import model.Publisher;
import validator.InputBookValidation;
import validator.InputPublisherValidation;

/**
 *
 * @author ManhComputer
 */
public class BookDaoImpl implements BookDao{
    
    static final String BOOK_FILE = "book.csv";
    private List<Book> books = null;
    public BookDaoImpl() throws IOException{
        this.books = this.load();
    }
    
    
    @Override
    public boolean add(Book book) throws IOException {
        this.books.add(book);
        try {
            this.flush();
        } catch (FileSystemException e) {
            this.books.remove(this.books.size() - 1);
            System.out.println("Please close file book.csv or unmark read-only before run program!!!");
            return false;
        }
       
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        for(int i = 0; i<this.books.size(); i++){
            if(this.books.get(i).getId().equals(id)){
                Book b = this.books.get(i);
                this.books.remove(i);
                try {
                    this.flush();
                    return true;
                } catch (FileSystemException e) {
                    this.books.add(i, b);
                    throw new IOException("Please close file book.csv or unmark read-only before run program!!!");
                }
            }
        }
        return false;
    }

    @Override
    public List<Book> getAll() {
        Collections.sort(this.books, Book.compQandP);
        return this.books;
    }

    @Override
    public Book getById(String id) {
        for(Book b : this.books){
            if(b.getId().equals(id)){
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean update(Book book) throws IOException {
        int index;
        String id = book.getId();
        Book b;
        for(index = 0; index< this.books.size(); index++){
            if(this.books.get(index).getId().equals(id)){
                b = this.books.get(index);
                String nOld = b.getName(), sOld = b.getStatus(), IdPublisherOld = b.getPublisherId();
                double pOld = b.getPrice();
                int qOld = b.getQuantity();
                if(!book.getName().equals("")){
                    b.setName(book.getName());
                }
                
                if(!book.getStatus().equals("")){
                    b.setStatus(book.getStatus().toLowerCase());
                }
                
                if(book.getPublisherId() != null){
                    if(!book.getPublisherId().equals("")){
                        b.setPublisherId(book.getPublisherId());
                    }
                } else {
                    b.setPublisherId(null);
                }
                
                if(book.getPrice() != 0){
                    b.setPrice(book.getPrice());
                }
                
                if(book.getQuantity() != 0){
                    b.setQuantity(book.getQuantity());
                }
   
                try {
                    this.flush();
                } catch (FileSystemException e) {
                    b.setName(nOld);
                    b.setPrice(pOld);
                    b.setPublisherId(IdPublisherOld);
                    b.setQuantity(qOld);
                    b.setStatus(sOld);
                    throw new IOException("Please close file book.csv or unmark read-only before run program!!!");
                }
                return true;
            }
        }
        
        return false;
    }

    @Override
    public List<Book> getBooksByIdPublisher(String id) {
        List <Book> bs = new ArrayList<>();
        for(Book b: this.books){
            if(b.getPublisherId().equals(id)){
                bs.add(b);
            }
        }
        return bs;
    }

    @Override
    public List<Book> search(String nameBook, String idPublisher) {
        List <Book> bs = new ArrayList<>();
        if(nameBook.equals("") && idPublisher.equals("")){
            return bs;
        }
        

        
        if(nameBook.equals("") && !idPublisher.equals("")){
            for(Book b : this.books){
                if(b.getPublisherId()!=null){
                    if(b.getPublisherId().equals(idPublisher)){
                        bs.add(b);
                    }
                }
            }
        } else if(!nameBook.equals("") && !idPublisher.equals("")){
            for(Book b : this.books){
                if(b.getPublisherId()!=null){
                    if(b.getName().toLowerCase().contains(nameBook.toLowerCase()) && b.getPublisherId().equals(idPublisher)){
                        bs.add(b);
                    }
                }
            }
        } else {
            for(Book b : this.books){
                if(b.getPublisherId()!=null){
                    if(b.getName().toLowerCase().contains(nameBook.toLowerCase()) || b.getPublisherId().equals(idPublisher)){
                        bs.add(b);
                    }
                } else {
                    if(b.getName().toLowerCase().contains(nameBook.toLowerCase())){
                        bs.add(b);
                    }
                }
            }
        }
        Collections.sort(bs, Book.compName);
        return bs;
    }
    
    
    
    
    
    public void flush() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Book p: this.books) {
            lines.add(p+"\n");
        }
        Files.write(Paths.get(BOOK_FILE), "Id|Name|Price|quantity|status|publisherId\n".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        for (String line: lines) {
            Files.write(Paths.get(BOOK_FILE), line.getBytes(), StandardOpenOption.APPEND);
        }
    }

    private List<Book> load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(BOOK_FILE));
        List<Book> books = new ArrayList<>();
        for (int i=1; i< lines.size();++i) {
            String[] cs = lines.get(i).split("\\|");
            books.add(new Book(cs[0], cs[1], Double.parseDouble(cs[2]), Integer.parseInt(cs[3]), cs[4], cs[5]));
        }
        return books;
    }
    
}
