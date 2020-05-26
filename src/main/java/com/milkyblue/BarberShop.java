package com.milkyblue;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

import com.github.tomaslanger.chalk.Chalk;

public class BarberShop {

  private ArrayBlockingQueue<Customer> buffer;
  private int nChairs;

  public BarberShop(int nChairs) {
    buffer = new ArrayBlockingQueue<Customer>(nChairs);
    this.nChairs = nChairs;
  }

  public void remove(Customer customer) {
    buffer.remove();
  }

  public Customer getCustomer() {
    return buffer.element();
  }

  public void put(Customer customer) throws InterruptedException {
    buffer.put(customer);
  }

  public boolean availableChair() {
    return (buffer.remainingCapacity() > 0);
  }

  public boolean isEmpty() {
    return buffer.isEmpty();
  }

  public String toString() {

    Chalk[] chairs = new Chalk[nChairs];

    for (int i = 0; i < chairs.length; i++)
      chairs[i] = Chalk.on("Empty").yellow();

    for (int i = 0; i < buffer.toArray().length; i++)
      chairs[i] = Chalk.on(buffer.toArray()[i].toString()).cyan();

    return Arrays.toString(chairs);
  }

}