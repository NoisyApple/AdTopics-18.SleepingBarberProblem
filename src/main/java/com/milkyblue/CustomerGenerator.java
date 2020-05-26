package com.milkyblue;

import com.github.tomaslanger.chalk.Chalk;

public class CustomerGenerator implements Runnable {

  BarberShop buffer;

  public CustomerGenerator(BarberShop buffer) {
    this.buffer = buffer;
  }

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
        // TODO: handle exception
      }

    }
  }

}