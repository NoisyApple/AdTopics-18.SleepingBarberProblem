# [Advanced Programming Topics] #18 Sleeping Barber Problem

This is the **18th** project part of my Advanced Programming Topics course at Leon Institute of Thechnology :mortar_board::lion:.

## :dart:GOAL

Create a representation of the **Sleeping Barber Problem** in java and show its behaviour.

## :eyeglasses:BRIEF EXPLANATION

The **Sleeping Barber Problem** is based in an hypothetical barber shop with **one barber** and **n chairs** for customers. The problem follows the next rules:

- If there are **no customers** in the shop the barber will **sleep**.
- If a customer arrives and there is an **available chair** to sit, the customer will sit on it and **wait his turn**.
- If a customer arrives and there is **no available chair** to sit, the customer will **leave the shop**.
- When customers are waiting while they are sitting on a chair, the barber will **wake up** and **cut customers hair**.
- When a customer get its hair cut they will **leave the shop** and therefore the chair they were sitting on.

---

The GUI takes the number of chairs for the barber shop, when the **"Execute"** button is pressed the program will begin with that amount of chairs.

<p align="center">
<img src="./snapshots/Annotation 2020-05-26 031623.png"/>
</p>

The program works with 4 main elements based on the **Producer/Consumer model**, a `BarberShop` object which will work as a **Buffer** (Chairs in the Barber Shop) and will store `Customer` objects in to simulate when a Customer sits on a chair, a `CustomerGenerator` object will work as a **producer** which will be creating `Customer` objects in random intervals of time between 0 and 10 seconds, finally a `Barber` object will work as a **consumer** that watches the buffer each second and **"consume"** (cut a customer's hair) if there are customers sitting on the chairs, otherwise the barber will remain asleep.

The **"Execute"** button's listener initializes the mentioned elements and execute the corresponding threads:

```java
btnExecute.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent e) {

    // Try block catches Integer to String parsing exeptions.
    try {
      // BarberShop initialized with value from GUI.
      BarberShop bShop = new BarberShop(Integer.parseInt(txtChairAmount.getText()));

      // CustomerGenerator (Producer) and Barber (Consumer) Threads initialization passing bShop as a parameter.
      CustomerGenerator generator = new CustomerGenerator(bShop);
      Barber barber = new Barber(bShop);

      // ExecutorService initialization.
      ExecutorService executor = Executors.newFixedThreadPool(2);

      // Threads execution.
      executor.execute(generator);
      executor.execute(barber);

    } catch (Exception error) {
      /*
      ** Exception handling.
      */
    }

  }
});
```

---

Now lets see the program's behaviour with an example of a **Barber Shop with 3 chairs**:

<p align="center">
<img src="./snapshots/Annotation 2020-05-26 031642.png"/>
</p>

When we press the **"Execute"** button the program begins on the console:

<p align="center">
<img src="./snapshots/Annotation 2020-05-26 031307.png"/>
</p>

Since the Barber Shop starts with all its chairs empty the **Barber** will stay **sleeping**, until a customer arrives (**C-1** shown in green color). **C-1** sits on an empty chair and waits his turn (**C-1** shown in blue color), now one of the three chairs is in use (**Chairs** shown in purple color). **Barber** will wake and cut the hair of the first customer in the Chairs, in this case **C-1** and take a random amount of time between 0 and 10 seconds until the haircut is done. Then the customer leaves the shop.

<p align="center">
<img src="./snapshots/Annotation 2020-05-26 031528.png"/>
</p>

When all of the three chairs are **empty** again, the **Barber** will **sleep** again (**Barber** shown in yellow at bottom lines).

<p align="center">
<img src="./snapshots/Annotation 2020-05-26 031447.png"/>
</p>

Finally, if all of the three chairs are **in use** and a new customer arrives, then that customer will **leave** the **Barber Shop** (**C-17** and **C-18** shown in red color).
