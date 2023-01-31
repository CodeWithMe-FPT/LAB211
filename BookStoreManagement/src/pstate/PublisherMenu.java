package pstate;

import global.Global;
import model.Publisher;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.Book;
import validator.InputPublisherValidation;

public class PublisherMenu implements Menu {
    @Override
    public Menu run() {
        System.out.println("--- Publisher Menu ---");
        System.out.println("1: Add a publisher");
        System.out.println("2. Delete a publisher");
        System.out.println("3. Update a publisher");
        System.out.println("4. Get a publisher by Id");
        System.out.println("5. Get all publishers");
        System.out.println("6. Go back");
        System.out.println("-----------------------");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (choice.equals("1")) {
            // TODO
            Publisher p = new Publisher();
            try {
            String id, name, phone;
            InputPublisherValidation validate = new InputPublisherValidation(Global.publisherDao.getAll());
            do {
                System.out.println("Input Id:");
                id = sc.nextLine();
                try {
                    if(!validate.checkIdPublishers(id)){
                        throw new Exception("Id already exists");
                    } else{
                        p.setId(id);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (true);

            do{
                System.out.println("Input name:");
                name = sc.nextLine();
                try {
                    if(validate.checkName(name)){
                        p.setName(name);
                        break;
                    } else{
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid name");
                }
            } while(true);

            do{
                System.out.println("Input phone:");
                phone = sc.nextLine();
                try {
                    if(validate.checkPhone(phone)){
                        p.setPhone(phone);
                        break;
                    } else{
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid phone");
                }
            } while(true);                
                
            if(Global.publisherDao.add(p)){
                System.out.println("Add publisher successfully"); 
            } else{
                System.out.println("Add publisher failed");
            }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Add publisher failed");
                // TODO
            }
        } else if (choice.equals("2")) { // TODO: handle other options
            String id = sc.nextLine();
            Publisher p = Global.publisherDao.getById(id);
            try {
                if(Global.publisherDao.delete(id)){
                    List <Book> bs = Global.bookDao.getBooksByIdPublisher(id);
                    try {
                        if(bs.size()>0){
                            for(Book b: bs){
                                Global.bookDao.update(new Book(b.getId(), "", 0, 0, "", null));
                            }
                        }
                    } catch (IOException e) {
                        Global.publisherDao.add(p);
                        System.out.println(e.getMessage());
                        System.out.println("Delete publisher failed");
                        return new MainMenu();
                    }
                    System.out.println("Delete publisher successfully");
                } else{
                    System.out.println("Publisher’s Id does not exist \nDelete publisher failed");
                }    
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Delete publisher failed"); 
            }
        } else if(choice.equals("3")) {
            Publisher p = new Publisher();
            String id, name, phone;
            InputPublisherValidation validate = new InputPublisherValidation(Global.publisherDao.getAll());
            do {
                System.out.println("Input Id:");
                id = sc.nextLine();
                try {
                    if(!validate.checkIdPublishers(id)){
                        p.setId(id);
                        break;
                    } else{
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Publisher’s Id does not exist");
                }
            } while (true);
            
            do{
                System.out.println("Input name:");
                name = sc.nextLine();
                try {
                    if(validate.checkName(name)){
                        p.setName(name);
                        break;
                    } else{
                        System.out.println("Invalid name");
                    }
                } catch (Exception e) {
                    p.setName("");
                    break;
                }
            } while(true);
            
            do{
                System.out.println("Input phone:");
                phone = sc.nextLine();
                try {
                    if(validate.checkPhone(phone)){
                        p.setPhone(phone);
                        break;
                    } else{
                        System.out.println("Invalid phone");
                    }
                } catch (Exception e) {
                    p.setPhone("");
                    break;
                }
            } while(true);
            

            try {
                if(Global.publisherDao.update(p)){
                    System.out.println("Update publisher successfully"); 
                } else{
                    System.out.println("Update publisher failed");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Update publisher failed");
            }
            
        } else if(choice.equals("4")){
            String id = sc.nextLine();
            Publisher p = Global.publisherDao.getById(id);
            if(p!= null){
                System.out.println(p);
            } else{
                System.out.println("Publisher’s Id does not exist");
            }
        } else if(choice.equals("5")){
            List <Publisher> ps = Global.publisherDao.getAll();
            for(Publisher p : ps ){
                System.out.println(p);
            }
        } else if(choice.equals("6")){
            return new MainMenu();
        }
        return this;
    }    
}


