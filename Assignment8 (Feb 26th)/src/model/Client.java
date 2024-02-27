package model;

import java.util.Date;

public class Client extends User implements Runnable{

    public Client(String userName, String password) {
        super(userName, password);
    }

    @Override
    public void run() {
        System.out.println("Client run method");
    }
}
