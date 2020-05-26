package com.milkyblue;

import com.github.tomaslanger.chalk.Chalk;

// Customer class. Models a customer, each one of them with a unique id and 
// a reference to a buffer.
public class Customer {

  private BarberShop buffer;
  private static int idCount = 0;
  private int id;

  // Class constructor.
  public Customer(BarberShop buffer) {
    this.buffer = buffer;
    this.id = ++idCount;
  }

  // Customer enters to the barber shop, if there is an available chair the
  // customer takes that chair and waits for his turn, otherwise, if there are no
  // available chairs the customer leaves the shop.
  public void enter() {
    if (buffer.availableChair()) {
      try {
        buffer.put(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("[" + Chalk.on("C-" + id).cyan() + "] Customer is waiting his turn.");
    } else {
      System.out.println("[" + Chalk.on("C-" + id).red() + "] No available chairs, customer leave.");
    }
  }

  // Returns the customer's id.
  public int getId() {
    return id;
  }

  // Returns a text representation of the customer.
  public String toString() {
    return "C-" + id;
  }

}