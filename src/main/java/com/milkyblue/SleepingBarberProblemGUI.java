package com.milkyblue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.tomaslanger.chalk.Chalk;

// Class SleepingBarberProblemGUI. Models the GUI.
public class SleepingBarberProblemGUI {

  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, centerPanel, bottomPanel;
  private JLabel lblAdvice, lblChairAmount;
  private JTextField txtChairAmount;
  private JButton btnExecute;

  // Class constructor.
  public SleepingBarberProblemGUI() {
    mainFrame = new JFrame("Sleeping Barber Problem");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    centerPanel = new JPanel();
    bottomPanel = new JPanel();
    lblAdvice = new JLabel("Input the required data");
    lblChairAmount = new JLabel("Chairs:");
    txtChairAmount = new JTextField(10);
    btnExecute = new JButton("Execute");

    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to elements in the class.
  private void addAttributes() {
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
  }

  // Adds listeners to elements in GUI.
  private void addListeners() {

    // When btnExecute is pressed a new BarberShop is created with the amount of
    // chairs specified in the GUI. Also a CustomerGenerator and a Barber Threads
    // are started with an ExecutorService.
    btnExecute.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        Chalk.setColorEnabled(true);

        try {
          BarberShop bShop = new BarberShop(Integer.parseInt(txtChairAmount.getText()));

          CustomerGenerator generator = new CustomerGenerator(bShop);
          Barber barber = new Barber(bShop);

          ExecutorService executor = Executors.newFixedThreadPool(2);

          executor.execute(generator);
          executor.execute(barber);

        } catch (Exception error) {
          JOptionPane.showMessageDialog(null,
              "<html><span style='font-weight: bold; color: red'>ERROR: </span>Type valid information.<html>", "Error",
              JOptionPane.PLAIN_MESSAGE);
        }

      }
    });
  }

  // Builds the GUI.
  private void build() {
    topPanel.add(lblAdvice);

    centerPanel.add(lblChairAmount);
    centerPanel.add(txtChairAmount);

    bottomPanel.add(btnExecute);

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    mainFrame.add(mainPanel);
  }

  // Launches the GUI by setting the mainFrame's visible value to true.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

}