package main;

import dao.BookDao;
import dao.BookDaoImpl;
import dao.PublisherDao;
import dao.PublisherDaoImpl;
import global.Global;
import pstate.MainMenu;
import pstate.Menu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // init global objects
        PublisherDao pDao = null;
        BookDao bDao = null;
        try {
            pDao = new PublisherDaoImpl();
            bDao = new BookDaoImpl();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Global.publisherDao = pDao;
        Global.bookDao = bDao;

        // start program
        Menu menu = new MainMenu();
        while (menu != null) {
            menu = menu.run();
        }
    }
}
