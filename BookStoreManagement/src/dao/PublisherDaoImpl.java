package dao;

import java.io.File;
import model.Publisher;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import validator.InputPublisherValidation;

public class PublisherDaoImpl implements PublisherDao {
    static final String PUBLISHER_FILE = "publisher.csv";
    public List<Publisher> publishers = null;
    public PublisherDaoImpl() throws IOException {
        this.publishers = load();
    }
    @Override
    public boolean add(Publisher publisher) throws IOException {
        this.publishers.add(publisher);
        try {
            this.flush();
        } catch (FileSystemException e) {
            System.out.println("Please close file publisher.csv or unmark read-only before run program!!!");
            this.publishers.remove(this.publishers.size() - 1);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        for(int i = 0; i < this.publishers.size(); i++){
            if(this.publishers.get(i).getId().equals(id)){
                Publisher p = this.publishers.get(i);
                this.publishers.remove(i);
                try {
                    this.flush();
                } catch (FileSystemException e) {
                    this.publishers.add(i, p);
                    throw new IOException("Please close file publisher.csv or unmark read-only before run program!!!");
                }
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean update(Publisher publisher) throws IOException {
        int index;
        String id = publisher.getId();
        Publisher p;
        for(index = 0; index< this.publishers.size(); index++){
            if(this.publishers.get(index).getId().equals(id)){
                p = this.publishers.get(index);
                String nOld = p.getName(), pOld = p.getPhone();
                if(!publisher.getName().equals("")){
                    p.setName(publisher.getName());
                }
                if(!publisher.getPhone().equals("")){
                    p.setPhone(publisher.getPhone());
                }
   
                try {
                    this.flush();
                } catch (FileSystemException e) {
                    p.setName(nOld);
                    p.setPhone(pOld);
                    throw new IOException("Please close file publisher.csv or unmark read-only before run program!!!");
                }
                
                
                return true;
            }
        }
        
        return false;
    }

    @Override
    public Publisher getById(String id) {
        for(Publisher p : this.publishers){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Publisher> getAll() {
        Collections.sort(this.publishers, Publisher.compName);
        return this.publishers;
    }

    public void flush() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Publisher p: this.publishers) {
            lines.add(p+"\n");
        }
        Files.write(Paths.get(PUBLISHER_FILE), "Id|Name|Phone\n".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        for (String line: lines) {
            Files.write(Paths.get(PUBLISHER_FILE), line.getBytes(), StandardOpenOption.APPEND);
        }
    }

    private List<Publisher> load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(PUBLISHER_FILE));
        List<Publisher> publishers = new ArrayList<>();
        for (int i=1; i< lines.size();++i) {
            String[] cs = lines.get(i).split("\\|");
            publishers.add(new Publisher(cs[0], cs[1], cs[2]));
        }
        return publishers;
    }
}
