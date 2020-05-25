package com.milkyblue;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class BarberShop {

  private ArrayBlockingQueue<Customer> buffer;

  public BarberShop(int nChairs) {
    buffer = new ArrayBlockingQueue<Customer>(nChairs);
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
    return Arrays.toString(buffer.toArray());
  }

}