package com.milkyblue;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

import com.github.tomaslanger.chalk.Chalk;

// BarberShop class. Models a buffer based object, keeps track of the amount 
// of chairs in the shop.
public class BarberShop {

  private ArrayBlockingQueue<Customer> buffer;
  private int nChairs;

  // Class constructor.
  public BarberShop(int nChairs) {
    buffer = new ArrayBlockingQueue<Customer>(nChairs);
    this.nChairs = nChairs;
  }

  // Removes the customer passed as a parameter from the buffer.
  public void remove(Customer customer) {
    buffer.remove();
  }

  // Gets a copy of the first customer from the buffer.
  public Customer getCustomer() {
    return buffer.element();
  }

  // Adds a customer passed as a parameter to the buffer.
  public void put(Customer customer) throws InterruptedException {
    buffer.put(customer);
  }

  // Returns whether there is an available chair or not.
  public boolean availableChair() {
    return (buffer.remainingCapacity() > 0);
  }

  // Returns whether the buffer is empty or not.
  public boolean isEmpty() {
    return buffer.isEmpty();
  }

  // Returns a string of the actual state of the chairs.
  public String toString() {

    Chalk[] chairs = new Chalk[nChairs];

    for (int i = 0; i < chairs.length; i++)
      chairs[i] = Chalk.on("Empty").yellow();

    for (int i = 0; i < buffer.toArray().length; i++)
      chairs[i] = Chalk.on(buffer.toArray()[i].toString()).cyan();

    return Arrays.toString(chairs);
  }

}