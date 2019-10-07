package online.lucianofelix.visao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FrameImagens extends JFrame {
	static Image imagem;
	File arquivo;
	String localArquivo;
	public FrameImagens() {
		super();

	}
	public FrameImagens(File arquivo) {
		super();
		this.arquivo = arquivo;
		initComponents();

	}
	public FrameImagens(String localArquivo) {
		super();
		this.localArquivo = localArquivo;
		initComponents();

	}

	public void paint(Graphics graphics) {
		graphics.drawImage(imagem, 0, 0, null);
	}

	public void ExibirImagem() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		imagem = toolkit.getImage(arquivo.getAbsolutePath());
		MediaTracker mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(imagem, 0);

		try {
			mediaTracker.waitForID(0);

		} catch (InterruptedException ie) {
			System.err.println(ie);
			System.exit(1);
		}

		setResizable(false);
		setSize(imagem.getWidth(null), imagem.getHeight(null));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setName(arquivo.getName());
		setLocationRelativeTo(null);
		setVisible(true);

	}
	public void ExibirImagem(String localArquivo) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		imagem = toolkit.getImage(localArquivo);
		MediaTracker mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(imagem, 0);

		try {
			mediaTracker.waitForID(0);
		} catch (InterruptedException ie) {
			System.err.println(ie);
			System.exit(1);
		}
		setSize(imagem.getWidth(null), imagem.getHeight(null));
		setVisible(true);
	}

	public void initComponents() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				ExibirImagem();

			}
		});

	}
}
