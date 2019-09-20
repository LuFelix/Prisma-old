package online.lucianofelix.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class CapturaQR extends JFrame implements Runnable, ThreadFactory {

	private static final long serialVersionUID = 6441489157408381878L;

	private Executor executor = Executors.newSingleThreadExecutor(this);

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private JTextArea textarea = null;
	private JButton btnFoto;
	private JButton btnSalva;
	private JPanel pnlControle;

	public CapturaQR() {
		super();

		setLayout(new BorderLayout());
		setTitle("Captura de Imagem");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Component comp = SwingUtilities.getRoot(this);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				fecharCamera();
				((CapturaQR) comp).dispose();

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// Foto produto
		Dimension size = WebcamResolution.QVGA.getSize();
		// Foto Perfil
		// Dimension size = new Dimension(176, 144);

		if (Webcam.getWebcams().get(0).equals(null)) {
			JOptionPane.showMessageDialog(null,
					"Câmera não detectada, favor verificar as conexõaes");

		} else {

		}
		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);

		panel = new WebcamPanel(webcam);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);

		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setPreferredSize(size);

		btnFoto = new JButton("Captura");
		btnFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(webcam.getImage(), "PNG",
							new File("C:\\SIMPRO\\foto.png"));
				} catch (IOException e1) {
					webcam.close();
					e1.printStackTrace();
				}

			}
		});

		btnSalva = new JButton("Salvar");

		pnlControle = new JPanel(new BorderLayout());
		pnlControle.add(btnFoto, BorderLayout.PAGE_START);
		pnlControle.add(btnSalva, BorderLayout.PAGE_END);

		add(panel, BorderLayout.BEFORE_LINE_BEGINS);
		add(textarea, BorderLayout.CENTER);
		add(pnlControle, BorderLayout.PAGE_END);
		pack();
		setVisible(true);

		executor.execute(this);
	}

	@Override
	public void run() {

		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Result result = null;
			BufferedImage image = null;

			if (webcam.isOpen()) {

				if ((image = webcam.getImage()) == null) {
					continue;
				}

				LuminanceSource source = new BufferedImageLuminanceSource(
						image);
				BinaryBitmap bitmap = new BinaryBitmap(
						new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e) {
					// fall thru, it means there is no QR code in image
				}
			}

			if (result != null) {
				textarea.setText(result.getText());
			}

		} while (true);
	}

	public void fecharCamera() {
		webcam.close();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "example-runner");
		t.setDaemon(true);
		return t;
	}

	public static void main(String[] args) {
		new CapturaQR();
	}

}
