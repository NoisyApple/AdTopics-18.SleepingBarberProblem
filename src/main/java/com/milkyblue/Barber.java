package com.milkyblue;

// Barber class. Consumer Based.
public class Barber implements Runnable {

  private BarberShop buffer;

  public Barber(BarberShop buffer) {
    this.buffer = buffer;
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        // TODO: handle exception
      }
      if (!buffer.isEmpty()) {
        Customer customer = buffer.getCustomer();
        System.out.println("[Barber] cutting C-" + customer.getId() + "'s hair.");
        cutHair(customer);
      } else {
        System.out.println("[Barber] sleeping.");
      }
    }
  }

  private void cutHair(Customer customer) {
    try {
      Thread.sleep((int) Math.floor(Math.random() * 15000));
      System.out.println("[C-" + customer.getId() + "] Customer got its hair cut.");
      buffer.remove(customer);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

}