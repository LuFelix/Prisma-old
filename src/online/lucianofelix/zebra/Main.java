package online.lucianofelix.zebra;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Main extends JPanel {

  JProgressBar pbar;

  static int min = 0;

  static int max = 100;

  public Main() {
	  setBounds(100, 100, 300, 150);
	  pbar = new JProgressBar();
	  pbar.setPreferredSize(new Dimension(500, 50));
	  pbar.setMinimum(min);
	  pbar.setMaximum(max);
	  add(pbar);
    
	  JFrame frame = new JFrame("Progress Bar Example");
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setContentPane(this);
	  frame.setAlwaysOnTop(true);
	  frame.setLocationRelativeTo(null);
	  frame.pack();
	  frame.setVisible(true);

	  for (int i = min; i <= max; i++) {
      final int percent = i;
      try {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            updateBar(percent);
          }
        });
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }
    }
  }

  public void updateBar(int newValue) {
    pbar.setValue(newValue);
  }

  public static void main(String args[]) {
    new Main();
  }
}

   