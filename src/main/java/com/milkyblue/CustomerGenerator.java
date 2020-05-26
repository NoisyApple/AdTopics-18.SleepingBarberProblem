package com.milkyblue;

import com.github.tomaslanger.chalk.Chalk;

// CustomerGenerator class. Models a producer based thread, 
// generates a new customer.
public class CustomerGenerator implements Runnable {

  BarberShop buffer;

  // Class constructor.
  public CustomerGenerator(BarberShop buffer) {
    this.buffer = buffer;
  }

  // Runs when the thread is started. Generates the new customer in random
  // intervals of time between 0 and 10 seconds.
  public void run() {
    while (true) {
      try {
        int time = (int) Math.floor(Math.random() * 10000);
        Thread.sleep(time);
        Customer newCustomer = new Customer(buffer);

        System.out.println("[" + Chalk.on("C-" + newCustomer.getId()).green() + "] New customer arrived after: " + time
            + " miliseconds.");
        newCustomer.enter();
        System.out.println("[" + Chalk.on("Chairs").magenta() + "] => " + buffer.toString());
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

}