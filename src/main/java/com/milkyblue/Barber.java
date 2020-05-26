package com.milkyblue;

import com.github.tomaslanger.chalk.Chalk;

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
        System.out.println(
            "[" + Chalk.on("Barber").yellow() + "] cutting " + Chalk.on("C-" + customer.getId()).cyan() + "'s hair.");
        cutHair(customer);
      } else {
        System.out.println("[" + Chalk.on("Barber").yellow() + "] sleeping.");
      }
    }
  }

  private void cutHair(Customer customer) {
    try {
      Thread.sleep((int) Math.floor(Math.random() * 10000));
      System.out.println("[" + Chalk.on("C-" + customer.getId()).cyan() + "] Customer got its hair cut.");
      buffer.remove(customer);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

}