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

public class SleepingBarberProblemGUI {

  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, centerPanel, bottomPanel;
  private JLabel lblAdvice, lblChairAmount;
  private JTextField txtChairAmount;
  private JButton btnExecute;

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

  private void addAttributes() {
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
  }

  private void addListeners() {
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

  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

}