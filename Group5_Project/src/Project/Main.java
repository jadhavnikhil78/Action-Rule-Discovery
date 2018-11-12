package Project;

/* Created by
 * Group 5
 * Bala Guna Teja Karlapudi
 * Nikhil Jadhav
 * Phyllis Jones
 * Saketh Kumar Kappala
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;

import javax.swing.*;

public class Main {
  public static void main(String[] args) throws FileNotFoundException, IOException {
	  
	  UI ui=new UI();
      JFrame jframe = new JFrame();
      jframe.setSize(850, 580);
      jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      jframe.setVisible(true);         
      jframe.setTitle("ACTION RULES");
      jframe.getContentPane().add(ui).setBackground(Color.WHITE);
  }
}

