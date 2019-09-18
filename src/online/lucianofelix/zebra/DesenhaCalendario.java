package online.lucianofelix.zebra;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class DesenhaCalendario extends JPanel {
//  SimpleDateFormat month = new SimpleDateFormat("MMMM");
//  SimpleDateFormat year = new SimpleDateFormat("yyyy");
//  SimpleDateFormat day = new SimpleDateFormat("d");
//  Date date = new Date();
//
//  public void setDate(Date date) {
//    this.date = date;
//  }
//  public void paintComponent(Graphics g) {
//    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//    g.fillRect(0, 0, getWidth(), getHeight());
//    g.setColor(Color.black);
//    g.drawString(month.format(date), 34, 36);
//    g.setColor(Color.white);
//    g.drawString(year.format(date), 235, 36);
//
//    Calendar today = Calendar.getInstance();
//    today.setTime(date);
//    Calendar cal = Calendar.getInstance();
//    cal.setTime(date);
//    cal.set(Calendar.DATE, 1);
//    cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) + 1);
//    for (int week = 0; week < 6; week++) {
//      for (int d = 0; d < 7; d++) {
//        Color col = Color.black;
//          g.drawString(day.format(cal.getTime()), d * 30 + 46 + 4,
//              week * 29 + 81 + 20);
//        cal.add(Calendar.DATE, +1);
//      }
//    }
//  }
//
//  public static void main(String[] args) {
//    JFrame frame = new JFrame();
//    frame.setPreferredSize(new Dimension(300, 280));
//
//    DesenhaCalendario ch = new DesenhaCalendario();
//    ch.setDate(new Date());
//    frame.getContentPane().add(ch);
//    frame.setUndecorated(true);
//
//    frame.pack();
//    frame.setVisible(true);
//  }
//}
