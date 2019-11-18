package common;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.PanePDV;

public class StartPDV {

	private static void createAndShowGUI() {
		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);
		JButton btn = new JButton("teste");
		// Create and set up the window.
		JFrame frame = new JFrame("FramePDV");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		// JComponent newContentPane = new PanePDV();
		// newContentPane.setOpaque(true); // content panes must be opaque

		// frame.setContentPane(new PanePDV());

		// Display the window.
		frame.setLocationRelativeTo(null);
		// frame.add(new PanePDV());
		frame.setContentPane(new PanePDV());
		frame.add(btn);
		frame.pack();
		frame.setVisible(true);
	}

	public StartPDV() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		createAndShowGUI();
		// javax.swing.SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// createAndShowGUI();
		// }
		// });

	}

}
