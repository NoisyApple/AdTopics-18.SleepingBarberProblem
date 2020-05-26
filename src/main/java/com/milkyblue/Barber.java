package com.milkyblue;

import com.github.tomaslanger.chalk.Chalk;

// Barber class. Models a consumer based Thread, when there are no clients in the 
// buffer the Barber is sleeping, otherwise, when a client is in the buffer (sitting 
// on a chair) the Barber wakes and cuts his hair.
public class Barber implements Runnable {

  private BarberShop buffer;

  // Class constructor.
  public Barber(BarberShop buffer) {
    this.buffer = buffer;
  }

  // Runs when the Thread is started. Checks if there is a customer in the buffer
  // in intervals of 1 second.
  public void run() {
    while (true) {
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (!buffer.isEmpty()) {
        // BARBER CUTS CUSTOMER'S HAIR.
        Customer customer = buffer.getCustomer();
        System.out.println(
            "[" + Chalk.on("Barber").yellow() + "] cutting " + Chalk.on("C-" + customer.getId()).cyan() + "'s hair.");
        cutHair(customer);
      } else {
        // BARBER SLEEPS.
        System.out.println("[" + Chalk.on("Barber").yellow() + "] sleeping.");
      }
    }
  }

  // Cuts the hair of a customer passed as a parameter in a random amount of time
  // from 0 to 10 seconds. Then the customer is removed from the buffer.
  private void cutHair(Customer customer) {
    try {
      Thread.sleep((int) Math.floor(Math.random() * 10000));
      System.out.println("[" + Chalk.on("C-" + customer.getId()).cyan() + "] Customer got its hair cut.");
      buffer.remove(customer);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}