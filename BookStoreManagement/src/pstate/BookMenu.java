package pstate;

import global.Global;
import java.io.IOException;
import model.Book;
import model.Publisher;

import java.util.List;
import java.util.Scanner;
import validator.InputBookValidation;
import validator.InputPublisherValidation;

public class BookMenu implements Menu {
    @Override
    public Menu run() {
        System.out.println("--- Book Menu ---");
        System.out.println("1: Add a book");
        System.out.println("2. Delete a book");
        System.out.println("3. Search books");
        System.out.println("4. Update book");
        System.out.println("5. Get all books");
        System.out.println("6. Go back");
        System.out.println("------------------");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (choice.equals("1")) {
            // TODO
            InputBookValidation validateB = new InputBookValidation(Global.bookDao.getAll());
            InputPublisherValidation validateP = new InputPublisherValidation(Global.publisherDao.getAll());
            String id, name, status, publisherId;
            double price;
            int quantity;
            do{
                System.out.println("Input Id book:");
                id = sc.nextLine();
                try {
                    if(!validateB.checkIdBook(id)){
                        throw new Exception("Id already exists");
                    } else {
                        break;              
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while(true);

            do{
                System.out.println("Input name book:");
                name = sc.nextLine();
                try {
                    if(validateB.checkName(name)){
                        break;
                    } else throw new Exception();
                } catch (Exception e) {
                    System.out.println("Invalid name");
                }
            } while(true);

            do{
                System.out.println("Input price:");

                try {
                    price = Double.parseDouble(sc.nextLine());
                    if(validateB.checkPrice(price)){
                        break;
                    } else throw new Exception();
                } catch (Exception e) {
                    System.out.println("Invalid price");
                }
            } while(true);

            do{
                System.out.println("Input quantity:");
                try {
                    quantity = Integer.parseInt(sc.nextLine());
                    if(validateB.checkPrice(quantity)){
                        break;
                    } else throw new Exception();
                } catch (Exception e) {
                    System.out.println("Invalid quantity");
                }
            } while(true);

            do{
                System.out.println("Input status:");
                status = sc.nextLine();
                try {
                    if(validateB.checkStatus(status)){
                        break;
                    } else throw new Exception();
                } catch (Exception e) {
                    System.out.println("Invalid status");
                }
            } while(true);

            do{
                System.out.println("Input Publisher's Id:");
                publisherId = sc.nextLine();
                try {
                    if(validateP.checkIdPublishers(publisherId)){
                        throw new Exception();
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Publisher’s Id is not found");
                }
            } while(true);
            
            try {
                if(Global.bookDao.add(new Book(id, name, price, quantity, status, publisherId))){
                    System.out.println("Add book successfully");
                } else{
                    System.out.println("Add book failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Add book failed");

            }

        } else if(choice.equals("2")) {
            String id = sc.nextLine();
            try {
                if(Global.bookDao.delete(id)){
                    System.out.println("Delete successfully");
                } else{
                    throw new Exception("Id does not exist");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Delete failed");
            }
        } else if(choice.equals("3")){
            System.out.println("Input name book:");
            String nBook = sc.nextLine();
            System.out.println("Input Publisher's Id:");
            String iPublisher = sc.nextLine();
            List<Book> bs = Global.bookDao.search(nBook, iPublisher);
            if(bs.size()>0){
                for(Book b : bs){
                    System.out.println(b);
                }
            } else {
                System.out.println("Have no any Book");
            }
        } else if(choice.equals("4")){
            InputBookValidation validateB = new InputBookValidation(Global.bookDao.getAll());
            InputPublisherValidation validateP = new InputPublisherValidation(Global.publisherDao.getAll());
            String id, name, status, publisherId;
            double price;
            int quantity;
            Book b = new Book();
            do{
                System.out.println("Input Id book:");
                id = sc.nextLine();
                try {
                    if(!validateB.checkIdBook(id)){
                        b.setId(id);
                        break;
                    } else {
                        throw new Exception();                
                    }
                } catch (Exception e) {
                    System.out.println("Book's Id does not exist");
                }
            } while(true);

            do{
                System.out.println("Input name book:");
                name = sc.nextLine();
                try {
                    if(validateB.checkName(name)){
                        b.setName(name);
                        break;
                    } else{
                        System.out.println("Invalid name");
                    }
                } catch (Exception e) {
                   b.setName("");
                   break;
                }
            } while(true);

            do{
                System.out.println("Input price:");

                try {
                    price = Double.parseDouble(sc.nextLine());
                    if(validateB.checkPrice(price)){
                        b.setPrice(price);
                        break;
                    } else System.out.println("Invalid price");
                } catch (Exception e) {
                    b.setPrice(0);
                    break;
                }
            } while(true);

            do{
                System.out.println("Input quantity:");
                try {
                    quantity = Integer.parseInt(sc.nextLine());
                    if(validateB.checkPrice(quantity)){
                        b.setQuantity(quantity);
                        break;
                    } else System.out.println("Invalid quantity");
                } catch (Exception e) {
                    b.setQuantity(0);
                    break;
                }
            } while(true);

            do{
                System.out.println("Input status:");
                status = sc.nextLine();
                try {
                    if(validateB.checkStatus(status)){
                        b.setStatus(status);
                        break;
                    } else System.out.println("Invalid status");
                } catch (Exception e) {
                    b.setStatus("");
                    break;
                }
            } while(true);

            do{
                System.out.println("Input Publisher's Id:");
                publisherId = sc.nextLine();
                try {
                    if(validateP.checkIdPublishers(publisherId)){
                        System.out.println("Publisher’s Id is not found");
                    } else {
                        b.setPublisherId(publisherId);
                        break;
                    }
                } catch (Exception e) {
                    if(publisherId.equals("")){
                        b.setPublisherId("");
                        break;
                    } else {
                        System.out.println("Publisher’s Id is not found");
                    }
                }
            } while(true);
            
            try {
                if(Global.bookDao.update(b)){
                    System.out.println("Update successfully");
                } else{
                    System.out.println("Update failed");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
 
        } else if(choice.equals("5")){
           List<Book> bs = Global.bookDao.getAll();
           for(Book b: bs){
                Publisher p = Global.publisherDao.getById(b.getPublisherId());
                if(p==null){
                    System.out.println(b.toStringWithGrid("null"));
                } else {
                    System.out.println(b.toStringWithGrid(p.getName()));
                }
               
           }
        } else if(choice.equals("6")){
            return new MainMenu();
        }
        return this;
    }
}
