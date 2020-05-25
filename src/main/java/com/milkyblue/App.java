package com.milkyblue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        BarberShop bShop = new BarberShop(3);

        CustomerGenerator generator = new CustomerGenerator(bShop);
        Barber barber = new Barber(bShop);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(generator);
        executor.execute(barber);

    }
}
