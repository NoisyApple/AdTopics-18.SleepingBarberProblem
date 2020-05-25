package com.milkyblue;

public class CustomerGenerator implements Runnable {

  BarberShop buffer;

  public CustomerGenerator(BarberShop buffer) {
    this.buffer = buffer;
  }

  public void run() {
    while (true) {
      try {
        int time = (int) Math.floor(Math.random() * 15000);
        Thread.sleep(time);
        Customer newCustomer = new Customer(buffer);

        System.out.println("[C-" + newCustomer.getId() + "] New customer generated after: " + time + " miliseconds");
        newCustomer.enter();
        System.out.println("[Chairs] => " + buffer.toString());
      } catch (Exception e) {
        // TODO: handle exception
      }

    }
  }

}